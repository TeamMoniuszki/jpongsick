package com.jpongsick.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.jpongsick.game.Entities.Player;
import com.jpongsick.game.Entities.PlayerManager;
import com.jpongsick.game.Logic.Event;


public abstract class Input {
    private static boolean isInitialized = false;
    private static JPongSick game;

    public static void initialize(JPongSick g) {
        if (isInitialized) return;
        game = g;

        isInitialized = true;
    }

    public static void update() {
        if (!isInitialized) return;
        switch (game.getState()) {
            case MENU: {
                if (Gdx.input.isKeyPressed(Keys.ENTER)) {
                    Logic.isAIGame = game.getMainMenuScreen().getAiCheckbox().isChecked();
                    Logic.handle(Logic.Event.NEW_GAME);
                }
                if(Gdx.input.isKeyJustPressed(Keys.GRAVE)){
                    Logic.handle(Event.OPENED_DEV_CONSOLE);
                }
                PlayerManager.updateMovement();

                break;
            }

            case PLAYING: {
                PlayerManager.updateMovement();
                if(Gdx.input.isKeyJustPressed(Keys.GRAVE)){
                    Logic.handle(Event.OPENED_DEV_CONSOLE);
                }
                if (Gdx.input.isKeyPressed(Keys.ESCAPE) || (Gdx.input.isKeyPressed(Keys.BACK) && Config.applicationType == Application.ApplicationType.Android)) {
                    Logic.handle(Event.EXITED_TO_MAIN_MENU);
                }
                break;
            }

            case PAUSE: {
                if (Gdx.input.isKeyPressed(Keys.SPACE) || (Gdx.input.isTouched() && Config.applicationType == Application.ApplicationType.Android)) {
                    Logic.handle(Event.GAME_RESUMED);
                }
                break;
            }

            case ROUND_OVER: {
                if(Gdx.input.isKeyJustPressed(Keys.GRAVE)){
                    Logic.handle(Event.OPENED_DEV_CONSOLE);
                }
                if (Gdx.input.isKeyPressed(Keys.SPACE) || (Gdx.input.isTouched() && Config.applicationType == Application.ApplicationType.Android)) {
                    Logic.handle(Event.GAME_RESUMED);
                }
                if (Gdx.input.isKeyPressed(Keys.ESCAPE) || (Gdx.input.isKeyPressed(Keys.BACK) && Config.applicationType == Application.ApplicationType.Android)) {
                    Logic.handle(Event.EXITED_TO_MAIN_MENU);
                }
                break;
            }

            case GAME_OVER: {
                if (Gdx.input.isKeyPressed(Keys.ESCAPE) || (Gdx.input.isKeyPressed(Keys.BACK) && Config.applicationType == Application.ApplicationType.Android)) {
                    Logic.handle(Event.EXITED_TO_MAIN_MENU);
                }
                break;
            }

            case DEV_CONSOLE: {
                if(Gdx.input.isKeyJustPressed(Keys.GRAVE)){
                    Logic.handle(Event.CLOSED_DEV_CONSOLE);
                }
                if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
                    game.getDevConsoleScreen().executeCommand();
                }
            }
        }
    }


    public static int getMovement(Player player) {
        int movement = 0;

        switch (Config.applicationType) {
            case Desktop: {
                if (player == game.getGameScreen().getPlayer1()) {
                    movement = Gdx.input.isKeyPressed(Keys.W) ? 1 : Gdx.input.isKeyPressed(Keys.S) ? -1 : 0;
                } else movement = Gdx.input.isKeyPressed(Keys.UP) ? 1 : Gdx.input.isKeyPressed(Keys.DOWN) ? -1 : 0;
                break;
            }
            case Android: {
                if (player == game.getGameScreen().getPlayer1()) {
                    if (Gdx.input.isTouched()) {
                        if ((Gdx.input.getY() < Config.halfHeight) && (Gdx.input.getX() < Config.halfWidth))
                            movement = 1;
                        else if ((Gdx.input.getY() > Config.halfHeight) && (Gdx.input.getX() < Config.halfWidth))
                            movement = -1;
                    } else movement = 0;
                } else {
                    if (Gdx.input.isTouched()) {
                        if ((Gdx.input.getY() < Config.halfHeight) && (Gdx.input.getX() > Config.halfWidth))
                            movement = 1;
                        else if ((Gdx.input.getY() > Config.halfHeight) && (Gdx.input.getX() > Config.halfWidth))
                            movement = -1;
                    }
                    else movement = 0;
                }
                break;
            }
        }
        return movement;
    }
}

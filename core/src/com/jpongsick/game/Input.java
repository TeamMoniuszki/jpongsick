package com.jpongsick.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;
import com.jpongsick.game.Entities.AI;
import com.jpongsick.game.Logic.Event;


public abstract class Input {
    private static boolean isInitialized = false;
    private static JPongSick game;
    public static int leftP;
    public static int rightP;
//    public static Vector3 touch = new Vector3();
    public static void initialize(JPongSick g){
        if (isInitialized) return;
        leftP = 0;
        rightP = 0;
        game = g;

        isInitialized = true;
    }
    public static void update(){
        if (!isInitialized) return;


        switch (Config.applicationType) {
            case Desktop:

                switch (game.getState()) {
                    case MENU: {
                        if (Gdx.input.isKeyPressed(Keys.ENTER)) {
                            Logic.isAIGame = game.getMainMenuScreen().getAiCheckbox().isChecked();
                            Logic.handle(Logic.Event.NEW_GAME);
                        }
                        break;
                    }

                    case PLAYING: {
                        leftP = Gdx.input.isKeyPressed(Keys.W) ? 1 : Gdx.input.isKeyPressed(Keys.S) ? -1 : 0;
                        if (Logic.isAIGame) {
                            rightP = AI.getMovement();
                        } else {
                            rightP = Gdx.input.isKeyPressed(Keys.UP) ? 1 : Gdx.input.isKeyPressed(Keys.DOWN) ? -1 : 0;
                        }

                        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
                            Logic.handle(Event.EXITED_TO_MAIN_MENU);
                        }
                        break;
                    }

                    case PAUSE: {
                        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
                            Logic.handle(Event.GAME_RESUMED);
                        }
                        break;
                    }

                    case ROUND_OVER: {
                        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
                            Logic.handle(Event.GAME_RESUMED);
                        }
                        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
                            Logic.handle(Event.EXITED_TO_MAIN_MENU);
                        }
                        break;
                    }

                    case GAME_OVER: {
                        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
                            Logic.handle(Event.EXITED_TO_MAIN_MENU);
                        }
                        break;
                    }
                }
                break;

            //TODO: Make this work properly on android - Vector3 and translating coordinates using camera,
            //TODO: Change the way to start a round (not touch)

            case Android:{
                switch (game.getState()) {
                    case MENU: {
                        break;
                    }

                    case PLAYING: {
                        if (Gdx.input.isTouched()) {
//                            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//                            game.getGameScreen().getCamera().unproject(touch);
//                            System.out.println(touch.toString());
                            if ((Gdx.input.getY() < Config.halfHeight) && (Gdx.input.getX() < Config.halfWidth))
                                leftP = 1;
                            else if ((Gdx.input.getY() > Config.halfHeight) && (Gdx.input.getX() < Config.halfWidth))
                                leftP = -1;
                        }
                        else leftP = 0;

                        if(Logic.isAIGame) {
                            rightP = AI.getMovement();
                        }
                        else {
                            if (Gdx.input.isTouched()) {
                                if ((Gdx.input.getY() < Config.halfHeight) && (Gdx.input.getX() > Config.halfWidth))
                                    rightP = 1;
                                else if ((Gdx.input.getY() > Config.halfHeight) && (Gdx.input.getX() > Config.halfWidth))
                                    rightP = -1;
                            } else rightP = 0;
                        }

                        if (Gdx.input.isKeyPressed(Keys.BACK)) {
                            Logic.handle(Event.EXITED_TO_MAIN_MENU);
                        }
                        break;
                    }

                    case PAUSE: {
                        if (Gdx.input.isTouched()) {
                            Logic.handle(Event.GAME_RESUMED);
                        }
                        break;
                    }

                    case ROUND_OVER: {
                        if (Gdx.input.isTouched()) {
                            Logic.handle(Event.GAME_RESUMED);
                        }
                        if (Gdx.input.isKeyPressed(Keys.BACK)) {
                            Logic.handle(Event.EXITED_TO_MAIN_MENU);
                        }
                        break;
                    }

                    case GAME_OVER: {
                        if (Gdx.input.isKeyPressed(Keys.BACK)) {
                            Logic.handle(Event.EXITED_TO_MAIN_MENU);
                        }
                        break;
                    }
                }
                break;
            }
        }
    }

}

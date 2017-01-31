package com.jpongsick.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.jpongsick.game.Entities.AI;
import com.jpongsick.game.Logic.Event;


public abstract class Input {
    private static boolean isInitialized = false;
    private static JPongSick game;
    public static int leftP;
    public static int rightP;

    public static void initialize(JPongSick g){
        if (isInitialized) return;
        leftP = 0;
        rightP = 0;
        game = g;

        isInitialized = true;
    }
    public static void update(){
        if (!isInitialized) return;

        //TODO: Make this work on android
        switch (game.getState()) {
            case MENU: {

                break;
            }
            case PLAYING: {
                leftP = Gdx.input.isKeyPressed(Keys.W) ? 1 : Gdx.input.isKeyPressed(Keys.S) ? -1 : 0;
                if(Logic.isAIGame) {
                    rightP = AI.getMovement();
                } else {
                    rightP = Gdx.input.isKeyPressed(Keys.UP) ? 1 : Gdx.input.isKeyPressed(Keys.DOWN) ? -1 : 0;
                }

                if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
                    Logic.handle(Event.EXITED_TO_MAIN_MENU);
                }
                break;
            }
            case PAUSE: {
                if(Gdx.input.isKeyPressed(Keys.SPACE)){
                    Logic.handle(Event.GAME_RESUMED);
                }
                break;
            }
            case ROUND_OVER: {
                if(Gdx.input.isKeyPressed(Keys.SPACE)){
                    Logic.handle(Event.GAME_RESUMED);
                }
                if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
                    Logic.handle(Event.EXITED_TO_MAIN_MENU);
                }
                break;
            }
            case GAME_OVER: {
                if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
                    Logic.handle(Event.EXITED_TO_MAIN_MENU);
                }
                break;
            }
        }
    }

}

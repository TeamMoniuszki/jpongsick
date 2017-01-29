package com.jpongsick.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.jpongsick.game.Util.State;


public abstract class Input {
    private static boolean isInitialized = false;
    public static int leftP;
    public static int rightP;
//    public static boolean resume;
    private static JPongSick game;

    public static void initialize(JPongSick g){
        if (isInitialized) return;
        leftP = 0;
        rightP = 0;
        game = g;
//        resume = false;


        isInitialized = true;
    }
    public static void update(){
        if (!isInitialized) return;

        switch (game.getState()) {
            case MENU: {

                break;
            }
            case PLAYING: {
                rightP = Gdx.input.isKeyPressed(Keys.UP) ? 1 : Gdx.input.isKeyPressed(Keys.DOWN) ? -1 : 0;
                leftP = Gdx.input.isKeyPressed(Keys.W) ? 1 : Gdx.input.isKeyPressed(Keys.S) ? -1 : 0;
                break;
            }

            case AI_GAME: {
                leftP = Gdx.input.isKeyPressed(Keys.W) ? 1 : Gdx.input.isKeyPressed(Keys.S) ? -1 : 0;
                break;
            }

            case PAUSE: {
                if(Gdx.input.isKeyPressed(Keys.SPACE)){
                    FacadeObserver.notify(FacadeObserver.Event.RESTART_ROUND);
                    FacadeObserver.notify(FacadeObserver.Event.RESUME_GAME);
                }
                if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
                    FacadeObserver.notify(FacadeObserver.Event.EXIT_TO_MAIN_MENU);
                }
                break;
            }
            case GAME_OVER: {
                if(Gdx.input.isKeyPressed(Keys.SPACE)){
                    FacadeObserver.notify(FacadeObserver.Event.RESTART_ROUND);
                    FacadeObserver.notify(FacadeObserver.Event.RESUME_GAME);
                }
                break;
            }
        }
    }

}

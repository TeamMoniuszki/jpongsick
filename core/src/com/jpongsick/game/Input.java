package com.jpongsick.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.jpongsick.game.Util.State;


public abstract class Input {
    private static boolean isInitialized = false;
    public static int leftP;
    public static int rightP;
    public static boolean resume;
    private static JPongSick game;

    public static void initialize(JPongSick g){
        if (isInitialized) return;
        leftP = 0;
        rightP = 0;
        game = g;
        resume = false;


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
            case PAUSE: {
                if(Gdx.input.isTouched()||Gdx.input.isKeyPressed(Keys.ANY_KEY)){
                    game.setState(State.PLAYING);
                }
                break;
            }
        }
    }

}

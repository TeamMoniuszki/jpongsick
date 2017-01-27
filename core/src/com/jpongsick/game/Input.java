package com.jpongsick.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 * Created by CalychasLaptop on 27.01.2017.
 */
public abstract class Input {
    public static int leftP;
    public static int rightP;
    private static JPongSick game;

    public static void initialize(JPongSick g){
        leftP = 0;
        rightP = 0;
        game = g;
    }
    public static void update(){
        if (game.getState() == State.PLAYING) {
            if(Gdx.input.isKeyPressed(Keys.UP)){
                rightP = 1;
            }
            else if(Gdx.input.isKeyPressed(Keys.DOWN)){
                rightP = -1;
            }
            else{
                rightP = 0;
            }

            if(Gdx.input.isKeyPressed(Keys.W)){
                leftP = 1;
            }
            else if(Gdx.input.isKeyPressed(Keys.S)){
                leftP = -1;
            }
            else{
                leftP = 0;
            }
        }
    }

}

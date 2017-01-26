package com.jpongsick.game;

import com.badlogic.gdx.math.Rectangle;
/**
 * Created by Igor on 16.01.2017.
 */
public class Platform extends Rectangle{
    static final int velocity = 6;

    public Platform(float x, float y, float width, float height){
        super(x,y, width, height);
    }

}

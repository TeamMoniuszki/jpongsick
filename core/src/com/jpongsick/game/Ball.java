package com.jpongsick.game;


import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Igor on 16.01.2017.
 */
public class Ball extends Circle {
    int velocity;
    double angle;

    public Ball(float x, float y, float radius, int velocity, Vector2 angle) {
        super(x, y, radius);
        this.velocity = velocity;
//        this.angle = calculateRadians(angle);
    }


}

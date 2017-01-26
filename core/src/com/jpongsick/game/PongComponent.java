package com.jpongsick.game;

/**
 * Created by Igor on 16.01.2017.
 */
public abstract class PongComponent{
    double x;
    double y;

    public PongComponent() {

    }

    public PongComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveComponent(double x, double y) {
        this.x += x;
        this.y += y;
    }

}

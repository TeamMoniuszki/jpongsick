package com.jpongsick.game;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Igor on 16.01.2017.
 */
public class Ball extends Circle {
    public static int len = 700;
    public Vector2 speed;
    public Pixmap pixmap;
    public Texture texture;

    public Ball(float x, float y, float radius) {
        super(x, y, radius);
        speed = new Vector2(len, 0);

        pixmap = new Pixmap((int) radius * 2 + 1, (int) radius * 2 + 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fillCircle((int) radius, (int) radius, (int) radius);
        texture = new Texture(pixmap);
    }

    public float getCenterX() {
        return this.x + this.radius;
    }

    public float getCenterY() {
        return this.y + this.radius;
    }

    public void setCenter(float x, float y) {
        this.x = x - this.radius;
        this.y = y - this.radius;
    }

}

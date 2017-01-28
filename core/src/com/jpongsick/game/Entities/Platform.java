package com.jpongsick.game.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jpongsick.game.Config;


public class Platform extends Rectangle{
    public static int speed = 400;
    public Pixmap pixmap;
    public Texture texture;

    public Platform(float x, float y, float width, float height) {
        super(x, y, width, height);

        pixmap = new Pixmap((int) width, (int) height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fillRectangle(0, 0, (int) width, (int) height);
        texture = new Texture(pixmap);
    }

    public boolean overlaps(Ball b) {
        return (Math.abs(b.getCenterX() - getCenterX()) <= b.radius + width / 2) && (Math.abs(b.getCenterY() - getCenterY()) <= b.radius + height / 2);
    }

    public Vector2 getCenter() {
        return new Vector2(x + width / 2, y + height / 2);
    }

    public float getCenterX() {
        return x + width / 2;
    }

    public float getCenterY() {
        return y + height / 2;
    }

    public void restart(){
        setCenter(this.getCenterX(), Config.halfHeight);
    }

}

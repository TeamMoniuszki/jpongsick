package com.jpongsick.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.jpongsick.game.Config;
import com.jpongsick.game.Util.UIManager;


public class Ball extends Circle {
    public static int len = (int) (Config.width * 1.2f);
    public Vector2 speed;
    public Pixmap pixmap;
    public Texture texture;

    public Ball(float x, float y, float radius) {
        super(x, y, radius);
        speed = new Vector2(1,1);
        resetPos();

        pixmap = new Pixmap((int) radius * 2 + 1, (int) radius * 2 + 1 , UIManager.ball.getFormat() /*Pixmap.Format.RGBA8888*/);
        pixmap.drawPixmap(UIManager.ball, 0, 0, UIManager.ball.getWidth(), UIManager.ball.getHeight(), 0, 0, pixmap.getWidth(), pixmap.getHeight() //FIXME: I DON'T KNOW HOW TO WORK OUT THE VALUES CORRECTLY
        );
        /*pixmap.setColor(Color.WHITE);
        pixmap.fillCircle((int) radius, (int) radius, (int) radius);*/
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

    public void randomizeAngle() {
        this.speed.setAngle(MathUtils.random(0, 7));
        this.speed.setLength(len);
        int rotation = MathUtils.random(1,4);
        switch(rotation){
            case 1:
                break;
            case 2:
                this.speed.x *= -1;
                break;
            case 3:
                this.speed.x *= -1;
                this.speed.y *= -1;
                break;
            case 4:
                this.speed.y *= -1;
                break;
        }
    }

    public void resetPos(){
        setCenter(Gdx.app.getGraphics().getWidth()/2, Gdx.app.getGraphics().getHeight()/2);
        this.randomizeAngle();
    }

}

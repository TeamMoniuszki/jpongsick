package com.jpongsick.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jpongsick.game.Entities.Ball;
import com.jpongsick.game.Entities.Platform;
import com.jpongsick.game.Util.State;
import com.jpongsick.game.FacadeObserver.Event;


public abstract class Physics {
    private static Ball ball;
    private static Platform platform1;
    private static Platform platform2;
    private static JPongSick game;

    public static void initialize(Ball b, Platform p1, Platform p2, JPongSick g) {
        ball = b;
        platform1 = p1;
        platform2 = p2;
        game = g;
    }

    private static void integrate() {
        platform1.y += Input.leftP * Platform.speed * Gdx.graphics.getDeltaTime();
        platform2.y += Input.rightP * Platform.speed * Gdx.graphics.getDeltaTime();
        ball.x += ball.speed.x * Gdx.graphics.getDeltaTime();
        ball.y += ball.speed.y * Gdx.graphics.getDeltaTime();
    }

    private static void ballWallCollisions() {
        //DOWN, UP
        if (ball.getCenterY() - ball.radius <= 0){
            ball.y = 0;
            ball.speed.y *= -1;
        }
        else if (ball.getCenterY()+ball.radius >= Config.height){
            ball.y = Config.height - 2 * ball.radius;
            ball.speed.y *= -1;
        }


        //LEFT, RIGHT
        if(ball.x <= 0) {
            FacadeObserver.notify(Event.RIGHT_PLAYER_SCORED);
        } else if (ball.x + ball.radius*2 >= Config.width) {
            FacadeObserver.notify(Event.LEFT_PLAYER_SCORED);
        }
    }

    private static void platformBallCollisions(Platform platform) {
        if (platform.overlaps(ball)){
            Vector2 newVector = new Vector2(ball.getCenterX() - platform.getCenterX(), ball.getCenterY() - platform.getCenterY());
            newVector.y /= 3;
            newVector.setLength(Ball.len);

            ball.speed = newVector;
        }
    }


    private static void platformWallCollisions(Platform platform) {
        if(platform.getY() + platform.getHeight() >= Config.height){
            platform.setY(Config.height - platform.getHeight());
        }
        if(platform.getY() <= 0){
            platform.setY(0);
        }
    }

    private static void checkCollisions() {
        ballWallCollisions();
        platformBallCollisions(platform1);
        platformBallCollisions(platform2);
        platformWallCollisions(platform1);
        platformWallCollisions(platform2);
    }

    public static void update() {
        integrate();
        checkCollisions();
    }


}
package com.jpongsick.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.jpongsick.game.Entities.Ball;
import com.jpongsick.game.Entities.Platform;
import com.jpongsick.game.Entities.PlayerManager;
import com.jpongsick.game.Logic.Event;

public abstract class Physics {
    private static Ball ball;
    public static Ball ghostBall;
    private static Platform platform1;
    private static Platform platform2;
    private static JPongSick game;

    public static void initialize(Ball b, Platform p1, Platform p2, JPongSick g) {
        ball = b;
        ghostBall=  new Ball(0, 0, ball.radius);
        platform1 = p1;
        platform2 = p2;
        game = g;
    }

    private static void integrate(Ball ball) {
        ball.x += ball.speed.x * Gdx.graphics.getDeltaTime();
        ball.y += ball.speed.y * Gdx.graphics.getDeltaTime();

        if(ball == ghostBall) return;

        platform1.y += Input.leftP * Platform.speed * Gdx.graphics.getDeltaTime();
        platform2.y += Input.rightP * Platform.speed * Gdx.graphics.getDeltaTime();
    }


    private static void ballWallCollisions(Ball ball) {
        //DOWN, UP
        if (ball.getCenterY() - ball.radius <= 0){
            ball.y = 0;
            ball.speed.y *= -1;
        }
        else if (ball.getCenterY() + ball.radius >= Config.height){
            ball.y = Config.height - 2 * ball.radius;
            ball.speed.y *= -1;
        }

        if(ball == ghostBall) return;

        //LEFT, RIGHT
        if(ball.x <= 0) {
            Logic.handle(Event.RIGHT_PLAYER_SCORED);
        } else if (ball.x + ball.radius*2 >= Config.width) {
            Logic.handle(Event.LEFT_PLAYER_SCORED);
        }
    }

    private static void platformBallCollisions(Platform platform) {
        if (platform.overlaps(ball)){
            Vector2 newVector = new Vector2(ball.getCenterX() - platform.getCenterX(), ball.getCenterY() - platform.getCenterY());
            newVector.y /= 3;
            newVector.setLength(Ball.len);

            ball.speed = newVector;

            //for trajectory
            ghostBall.x = ball.x;
            ghostBall.y = ball.y;
            ghostBall.speed.set(ball.speed);
            Logic.handle(platform == platform1 ? Event.LEFT_PLATFORM_HIT : Event.RIGHT_PLATFORM_HIT);
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
        ballWallCollisions(ball);
        platformBallCollisions(platform1);
        platformBallCollisions(platform2);
        platformWallCollisions(platform1);
        platformWallCollisions(platform2);
    }

    public static void calculateTrajectory() {
        integrate(ghostBall);
        ballWallCollisions(ghostBall);
    }

    public static void update() {
        integrate(ball);
        checkCollisions();
    }


}
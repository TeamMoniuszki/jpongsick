package com.jpongsick.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.jpongsick.game.Entities.Ball;
import com.jpongsick.game.Entities.Platform;
import com.jpongsick.game.Entities.Player;
import com.jpongsick.game.Entities.PlayerManager;
import com.jpongsick.game.Logic.Event;
import com.jpongsick.game.Util.State;

public abstract class Physics {
    private static Ball ball;
    public static Ball ghostBall;
    private static Player player1;
    private static Player player2;
    private static JPongSick game;

    public static void initialize(Ball b, Player p1, Player p2, JPongSick g) {
        ball = b;
        ghostBall=  new Ball(0, 0, ball.radius);
        player1 = p1;
        player2 = p2;
        game = g;
    }

    private static void integrate(Ball ball) {
        ball.x += ball.speed.x * Gdx.graphics.getDeltaTime();
        ball.y += ball.speed.y * Gdx.graphics.getDeltaTime();

        if(ball == ghostBall) return;

        player1.getPlatform().y += player1.getMovement() * Platform.speed * Gdx.graphics.getDeltaTime();
        player2.getPlatform().y += player2.getMovement() * Platform.speed * Gdx.graphics.getDeltaTime();
    }


    private static void ballWallCollisions(Ball ball) {
        //DOWN, UP
        if (ball.getCenterY() - ball.radius <= 0){
            ball.y = 0;
            ball.speed.y *= -1;
//            Config.hit.play();
        }
        else if (ball.getCenterY() + ball.radius >= Config.height){
            ball.y = Config.height - 2 * ball.radius;
            ball.speed.y *= -1;
//            Config.hit.play();
        }

        if(ball == ghostBall) return;

        //LEFT, RIGHT
        if(ball.x <= 0) {
            if(game.getState()== State.MENU){
                Physics.resetPhysics();
                return;
            }
            Logic.handle(Event.RIGHT_PLAYER_SCORED);
        } else if (ball.x + ball.radius*2 >= Config.width) {
            if(game.getState()== State.MENU){
                Physics.resetPhysics();
                return;
            }
            Logic.handle(Event.LEFT_PLAYER_SCORED);
        }
    }

    //FIXME: platform - ball collisions on screens with height greater than 800
    //FIXME: ball speed too high to properly calculate collisions
    private static void platformBallCollisions(Platform platform) {
        if (platform.overlaps(ball)) {
            Vector2 newVector = new Vector2(ball.getCenterX() - platform.getCenterX(), ball.getCenterY() - platform.getCenterY());
            newVector.y /= 3;
            newVector.setLength(Ball.len);
            ball.speed = newVector;



            //TODO: HOW TO MAKE IT WORK IF THERE ARE SO MANY POSSIBILITIES
//            while(platform.overlaps(ball)){
//                integrate2(ball);
//            }

//                Config.hit.play();

                //for trajectory
                ghostBall.x = ball.x;
                ghostBall.y = ball.y;
                ghostBall.speed.set(ball.speed);
                Logic.handle(platform == player1.getPlatform() ? Event.LEFT_PLATFORM_HIT : Event.RIGHT_PLATFORM_HIT);
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
        platformBallCollisions(player1.getPlatform());
        platformBallCollisions(player2.getPlatform());
        platformWallCollisions(player1.getPlatform());
        platformWallCollisions(player2.getPlatform());
    }

    public static void calculateTrajectory(Ball ball) {
        integrate(ball);
        ballWallCollisions(ball);
    }


    public static void update() {
        integrate(ball);
        checkCollisions();
    }


    public static void resetPhysics(){
        ball.resetPos();
        ghostBall.resetPos();
        player1.getPlatform().resetPos();
        player2.getPlatform().resetPos();
        game.getGameScreen().setHitPlatformLast(null);
    }

}
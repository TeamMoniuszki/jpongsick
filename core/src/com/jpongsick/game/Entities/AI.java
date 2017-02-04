package com.jpongsick.game.Entities;

import com.badlogic.gdx.math.MathUtils;
import com.jpongsick.game.Config;
import com.jpongsick.game.JPongSick;
import com.jpongsick.game.Logic;
import com.jpongsick.game.Physics;

import static com.jpongsick.game.Entities.AI.Difficulty.*;


public abstract class AI {
    private static boolean isInitialized = false;
    private static JPongSick game;
    private static String[] names;
    private static int movement;
    public enum Difficulty {
        EASY, MEDIUM, HARD, SHOWOFF, PLAYER
    }
    public static Difficulty[] difficulties;


    public static void initialize(JPongSick g){
        if(isInitialized) return;
        game = g;
        movement = 0;
        names = new String[]
                {"Albert", "Allen", "Bert", "Bob", "Cecil",
                "Clarence", "Elliot", "Elmer", "Ernie",
                "Eugene", "Fergus", "Ferris", "Frank",
                "Frasier", "Fred", "George", "Graham",
                "Harvey", "Irwin", "Larry", "Lester",
                "Marvin", "Neil", "Niles", "Oliver",
                "Opie", "Ryan", "Toby", "Ulric", "Ulysses",
                "Uri", "Waldo", "Wally", "Walt", "Wesley",
                "Yanni", "Yogi", "Yuri"};

        difficulties = new Difficulty[] {EASY, MEDIUM, HARD, SHOWOFF};

        isInitialized = true;
    }


    public static int getMovement(Player player){
        if(!isInitialized) return 2;

        switch(player.getAiType()){
            case EASY: {
                if(game.getGameScreen().getBall().getCenterY() - player.getPlatform().getCenterY() > 50){
                    movement = 1;
                }
                else if (game.getGameScreen().getBall().getCenterY() - player.getPlatform().getCenterY() < -50){
                    movement = -1;
                }
                else movement = 0;

                break;
            }

            case MEDIUM: {
                if(game.getGameScreen().getBall().getCenterY() > player.getPlatform().getCenterY()){
                    movement = 1;
                }
                else if (game.getGameScreen().getBall().getCenterY() < player.getPlatform().getCenterY()){
                    movement = -1;
                }
                else movement = 0;
                break;
            }

            case HARD: {
                if(player == game.getGameScreen().getHitPlatformLast()){
                    if(Config.halfHeight - player.getPlatform().getCenterY() > 15){
                        movement = 1;
                    }

                    else if(Config.halfHeight - player.getPlatform().getCenterY() < -15){
                        movement = -1;
                    }

                    else movement = 0;
                }
                else{
                    if(Physics.ghostBall.getCenterY() - player.getPlatform().getCenterY() > 15){
                        movement = 1;
                    }
                    else if (Physics.ghostBall.getCenterY() - player.getPlatform().getCenterY() < -15){
                        movement = -1;
                    }
                    else movement = 0;
                }
                break;
            }
        }
        return movement;
    }

    public static void handleEvent(Logic.Event e) {
        switch(e) {
            case LEFT_PLATFORM_HIT: {
                game.getGameScreen().setHitPlatformLast(game.getGameScreen().getPlayer1());
                while((Physics.ghostBall.getCenterX() + Physics.ghostBall.radius) <
                        (PlayerManager.getPlayers().get(1).getPlatform().getCenterX() - PlayerManager.getPlayers().get(1).getPlatform().getWidth()/2)){
                    if(Physics.ghostBall.speed.x <= 0) break;
                    Physics.calculateTrajectory(Physics.ghostBall);
                }

                break;
            }
            case RIGHT_PLATFORM_HIT: {
                game.getGameScreen().setHitPlatformLast(game.getGameScreen().getPlayer2());
                while((Physics.ghostBall.getCenterX() - Physics.ghostBall.radius) >
                        (PlayerManager.getPlayers().get(0).getPlatform().getCenterX() - PlayerManager.getPlayers().get(0).getPlatform().getWidth()/2)){
                    if(Physics.ghostBall.speed.x >= 0) break;
                    Physics.calculateTrajectory(Physics.ghostBall);
                }
                break;
            }

        }

    }


    public static String getRandomNickname() {
        return "BOT " + names[MathUtils.random(0, names.length - 1)];
    }
}

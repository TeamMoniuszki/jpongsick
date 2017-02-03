package com.jpongsick.game.Entities;

import com.badlogic.gdx.math.MathUtils;
import com.jpongsick.game.Config;
import com.jpongsick.game.JPongSick;
import com.jpongsick.game.Logic;
import com.jpongsick.game.Physics;


public abstract class AI {
    private static boolean isInitialized = false;
    private static JPongSick game;
    private static String[] names;
    private static int movement;
    private static int movement2;
    public static Difficulty difficulty;
    public enum Difficulty {
        EASY, MEDIUM, HARD, SHOWOFF
    }


    public static void initialize(JPongSick g){
        if(isInitialized) return;
        game = g;
        movement = 0;
        movement2 = 0;
        difficulty = Difficulty.SHOWOFF;
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

        isInitialized = true;
    }

    public static Difficulty getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(Difficulty difficulty) {
        AI.difficulty = difficulty;
    }

    public static int getMovement() {
        if(!isInitialized) return 2;

        switch (difficulty) {
            case EASY: {
                if(game.getGameScreen().getBall().getCenterY() - game.getGameScreen().getPlayer2().getPlatform().getCenterY() > 50){
                    movement = 1;
                }
                else if (game.getGameScreen().getBall().getCenterY() - game.getGameScreen().getPlayer2().getPlatform().getCenterY() < -50){
                    movement = -1;
                }
                else movement = 0;

                break;
            }
            case MEDIUM: {
                if(game.getGameScreen().getBall().getCenterY() > game.getGameScreen().getPlayer2().getPlatform().getCenterY()){
                    movement = 1;
                }
                else if (game.getGameScreen().getBall().getCenterY() < game.getGameScreen().getPlayer2().getPlatform().getCenterY()){
                    movement = -1;
                }
                else movement = 0;
                break;
            }
            case HARD: {
                if(Physics.ghostBall.getCenterY() - game.getGameScreen().getPlayer2().getPlatform().getCenterY() > 15){
                    movement = 1;
                }
                else if (Physics.ghostBall.getCenterY() - game.getGameScreen().getPlayer2().getPlatform().getCenterY() < -15){
                    movement = -1;
                }
                else movement = 0;

                break;
            }

            case SHOWOFF:{

                if(Physics.ghostBall.getCenterY() - game.getGameScreen().getPlayer2().getPlatform().getCenterY() > 15){
                    movement = 1;
                }
                else if (Physics.ghostBall.getCenterY() - game.getGameScreen().getPlayer2().getPlatform().getCenterY() < -15){
                    movement = -1;
                }
                else movement = 0;

                break;
            }
        }

        return movement;
    }

    public static int getMovement2(){
        if(Physics.ghostBall2.getCenterY() - game.getGameScreen().getPlayer1().getPlatform().getCenterY() > 15){
            movement2 = 1;
        }
        else if (Physics.ghostBall2.getCenterY() - game.getGameScreen().getPlayer1().getPlatform().getCenterY() < -15){
            movement2 = -1;
        }
        else movement2 = 0;

        return movement2;
    }

    public static void handleEvent(Logic.Event e) {
        switch(e) {
            case LEFT_PLATFORM_HIT: {
                while((Physics.ghostBall.getCenterX() + Physics.ghostBall.radius) <
                        (PlayerManager.getPlayers().get(1).getPlatform().getCenterX() - PlayerManager.getPlayers().get(1).getPlatform().getWidth()/2)){
                    if(Physics.ghostBall.speed.x <= 0) break;
                    Physics.calculateTrajectory(Physics.ghostBall);
                }
                Physics.ghostBall2.resetPos();

                break;
            }
            case RIGHT_PLATFORM_HIT: {
                while((Physics.ghostBall2.getCenterX() - Physics.ghostBall2.radius) >
                        (PlayerManager.getPlayers().get(0).getPlatform().getCenterX() - PlayerManager.getPlayers().get(0).getPlatform().getWidth()/2)){
                    if(Physics.ghostBall2.speed.x >= 0) break;
                    Physics.calculateTrajectory(Physics.ghostBall2);
                }
                Physics.ghostBall.resetPos();
                break;
            }

        }

    }


    public static String getRandomNickname() {
        return "BOT " + names[MathUtils.random(0, names.length - 1)];
    }
}

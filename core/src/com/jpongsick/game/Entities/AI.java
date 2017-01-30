package com.jpongsick.game.Entities;

import com.badlogic.gdx.math.MathUtils;
import com.jpongsick.game.JPongSick;


public abstract class AI {
    private static boolean isInitialized = false;
    private static JPongSick game;
    private static String[] names;
    private static int movement;
    public static Difficulty difficulty;
    public enum Difficulty {
        EASY, MEDIUM, HARD
    }


    public static void initialize(JPongSick g){
        if(isInitialized) return;
        game = g;
        movement = 0;
        difficulty = Difficulty.EASY;
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

                break;
            }
            case HARD: {

                break;
            }
        }
        return movement;
    }


    public static String getRandomNickname() {
        return "BOT " + names[MathUtils.random(0, names.length - 1)];
    }
}

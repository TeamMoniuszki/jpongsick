package com.jpongsick.game.Entities;

import com.jpongsick.game.JPongSick;
import com.jpongsick.game.Util.BotUtils;

/**
 * Created by Szymon on 28.01.2017.
 */
public abstract class AI {
    private static boolean isInitialized = false;

    private static JPongSick game;
    public static int movement;
    public static BotUtils.Difficulty botDifficulty = BotUtils.Difficulty.EASY;
    public static void initialize(JPongSick g){
        if(isInitialized) return;
        game = g;
        movement = 0;
        isInitialized = true;
    }

    public static void update(){
        if(!isInitialized) return;
        if(game.getGameScreen().getBall().getCenterY() - game.getGameScreen().getPlayer2().getPlatform().getCenterY() > 50){
            movement = 1;
        }
        else if (game.getGameScreen().getBall().getCenterY() - game.getGameScreen().getPlayer2().getPlatform().getCenterY() < -50){
            movement = -1;
        }
        else movement = 0;
    }


}

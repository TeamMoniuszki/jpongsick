package com.jpongsick.game.Entities;

import com.badlogic.gdx.utils.Array;


public abstract class PlayerManager {
    private static Array<Player> players;
    private static boolean isInitialized = false;

    public static void initialize() {
        if (isInitialized) return;
        players = new Array<Player>();
        isInitialized = true;
    }

    public static Player createPlayer(Platform platform, Score score, String nickname){
        if (!isInitialized) return null;
        Player player = Player.createPlayer(platform, score, nickname);
        players.add(player);
        return player;
    }

    public static Array<Player> getPlayers() {
        return players;
    }
}

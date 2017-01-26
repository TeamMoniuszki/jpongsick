package com.jpongsick.game;


import com.badlogic.gdx.utils.Array;

/**
 * Created by Szymon on 22.01.2017.
 */
public abstract class PlayerManager {

    private static Array players;
    private static boolean isInitialized = false;

    public void initialize() {
        if (isInitialized) return;
        players = new Array();
        isInitialized = true;
    }
}

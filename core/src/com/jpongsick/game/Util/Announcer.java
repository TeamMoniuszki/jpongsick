package com.jpongsick.game.Util;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jpongsick.game.Config;
import com.jpongsick.game.JPongSick;

/**
 * Created by Szymon on 28.01.2017.
 */
public abstract class Announcer {
    private static JPongSick game;
    private static boolean isInitialized = false;
    private static Label message;

    public static void initialize(JPongSick g){
        if(isInitialized) return;

        game = g;
        message = UIManager.createLabel("", UIManager.skin, Config.halfWidth, 2*Config.height/3f, Align.center, false);

        game.getStage().addActor(message);
        isInitialized = true;
    }

    public static void setText(String text){
        if(!isInitialized) return;
        message.setText(text);
        message.setAlignment(Align.center);
    }

    public static void showLabel(){
        if(!isInitialized) return;
        message.setVisible(true);
    }

    public static void hideLabel(){
        if(!isInitialized) return;
        message.setVisible(false);
    }
}

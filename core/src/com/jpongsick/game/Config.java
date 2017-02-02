package com.jpongsick.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

public abstract class Config {

    public static int width = Gdx.app.getGraphics().getWidth();
    public static int height = Gdx.app.getGraphics().getHeight();
    public static float halfWidth = width / 2f;
    public static float halfHeight = height / 2f;
    public static int maxGoals = 9;
    public static Application.ApplicationType applicationType = Gdx.app.getType();

}

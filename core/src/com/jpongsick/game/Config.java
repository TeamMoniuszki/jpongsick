package com.jpongsick.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;


public abstract class Config{
    public static boolean isInitialized = false;
    public static int width = Gdx.app.getGraphics().getWidth();
    public static int height = Gdx.app.getGraphics().getHeight();
    public static float halfWidth = width / 2f;
    public static float halfHeight = height / 2f;
    public static int maxGoals = 9;

    public static Skin skin = new Skin();
    public static Pixmap pixmap = new Pixmap(150, 50, Pixmap.Format.RGBA8888);
    public static TextButtonStyle textButtonStyle = new TextButtonStyle();
    public static BitmapFont font = new BitmapFont();
    public static Label.LabelStyle labelStyle = new Label.LabelStyle(Config.font, Color.WHITE);

    public static void initialize(){
        if (isInitialized) return;
        pixmap.setColor(Color.RED);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));
        skin.add("default", font);
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = font;
        skin.add("default", textButtonStyle);

        isInitialized = true;
    }

    public static void dispose() {
        pixmap.dispose();
        skin.dispose();
        font.dispose();
    }




}

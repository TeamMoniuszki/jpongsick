package com.jpongsick.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by CalychasLaptop on 26.01.2017.
 */
public abstract class Config{

    public static int width = 800;
    public static int height = 600;

    public static Skin skin = new Skin();
    public static Pixmap pixmap = new Pixmap(150, 50, Pixmap.Format.RGBA8888);
    public static TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
    public static BitmapFont font = new BitmapFont();

    public static void initialize(){

        pixmap.setColor(Color.RED);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));
        skin.add("default", font);

        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = font;
        skin.add("default", textButtonStyle);

    }




}

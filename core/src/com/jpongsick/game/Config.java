package com.jpongsick.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;


public abstract class Config{
    public static boolean isInitialized = false;
    public static int width = Gdx.app.getGraphics().getWidth();
    public static int height = Gdx.app.getGraphics().getHeight();
    public static float halfWidth = width / 2f;
    public static float halfHeight = height / 2f;
    public static int maxGoals = 9;

    public static Skin skin = new Skin();

    public static void initialize(){
        if (isInitialized) return;


        skin.add("default", new BitmapFont());

        Pixmap pixmap = new Pixmap(150, 50, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        skin.add("red", new Texture(pixmap));
        pixmap.dispose();

        pixmap = new Pixmap(150, 30, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        pixmap.dispose();

        pixmap = new Pixmap(2, 25, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        skin.add("cursor", new Texture(pixmap));
        pixmap.dispose();

        pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("checkBoxOff", new Texture(pixmap));
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        skin.add("checkBoxOn", new Texture(pixmap));
        pixmap.dispose();



        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("red", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("red", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("red", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        Label.LabelStyle labelStyle = new Label.LabelStyle(skin.getFont("default"), Color.WHITE);
        skin.add("default", labelStyle);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = skin.getFont("default");
        textFieldStyle.fontColor = Color.BLACK;
        textFieldStyle.background = skin.newDrawable("white", Color.LIGHT_GRAY);
        textFieldStyle.focusedBackground = skin.newDrawable("white");
        textFieldStyle.disabledBackground = skin.newDrawable("white", Color.DARK_GRAY);
        textFieldStyle.cursor = skin.newDrawable("cursor");
        textFieldStyle.selection = skin.newDrawable("white", 0.5f, 0.5f, 0.5f, 0.5f);
        skin.add("default", textFieldStyle);

        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
        checkBoxStyle.font = skin.getFont("default");
        checkBoxStyle.checkboxOff = skin.getDrawable("checkBoxOff");
        checkBoxStyle.checkboxOn = skin.getDrawable("checkBoxOn");
        skin.add("default", checkBoxStyle);





        isInitialized = true;
    }

    public static void dispose() {
        skin.dispose();
    }




}

package com.jpongsick.game.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.*;

/**
 * Created by Szymon on 02.02.2017.
 */
public abstract class UIManager {
    private static boolean isInitialized = false;
    public static Skin skin = new Skin();

    public static void initialize() {
        if(isInitialized) return;
        skin.add("default", new BitmapFont());
        skin.add("red", new Texture(Gdx.files.internal("red.png")));
        skin.add("white", new Texture(Gdx.files.internal("white.png")));
        skin.add("cursor", new Texture(Gdx.files.internal("black.png")));
        skin.add("checkBoxOff", new Texture(Gdx.files.internal("checkBoxOff.png")));
        skin.add("checkBoxOn", new Texture(Gdx.files.internal("checkBoxOn.png")));

        // TEXT BUTTON STYLE
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("red", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("red", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("red", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        // LABEL STYLE
        skin.add("default", new Label.LabelStyle(skin.getFont("default"), Color.WHITE));

        // TEXT FIELD STYLE
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = skin.getFont("default");
        textFieldStyle.fontColor = Color.BLACK;
        textFieldStyle.background = skin.newDrawable("white", Color.LIGHT_GRAY);
        textFieldStyle.focusedBackground = skin.newDrawable("white");
        textFieldStyle.disabledBackground = skin.newDrawable("white", Color.DARK_GRAY);
        textFieldStyle.cursor = skin.newDrawable("cursor");
        textFieldStyle.selection = skin.newDrawable("white", 0.5f, 0.5f, 0.5f, 0.5f);
        skin.add("default", textFieldStyle);

        // CHECK BOX STYLE
        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
        checkBoxStyle.font = skin.getFont("default");
        checkBoxStyle.checkboxOff = skin.getDrawable("checkBoxOff");
        checkBoxStyle.checkboxOn = skin.getDrawable("checkBoxOn");
        skin.add("default", checkBoxStyle);

        // SELECT BOX STYLE
        SelectBox.SelectBoxStyle selectBoxStyle = new SelectBox.SelectBoxStyle(skin.getFont("default"), Color.BLACK,
                skin.newDrawable("white"), new ScrollPane.ScrollPaneStyle(), new List.ListStyle(skin.getFont("default"),
                Color.BLACK, Color.LIGHT_GRAY, skin.newDrawable("white", Color.LIGHT_GRAY)));
        selectBoxStyle.font = skin.getFont("default");
        selectBoxStyle.background = skin.newDrawable("white");
        skin.add("default", selectBoxStyle);
    }

    public static TextButton createTextButton(String text, Skin skin, float width, float height, float x, float y, int align){
        TextButton textButton = new TextButton(text, skin);
        textButton.setSize(width, height);
        textButton.setPosition(x, y, align);
        return textButton;
    }

    public static TextField createTextField(String text, Skin skin, float width, float height, float x, float y, int align, int maxlength){
        TextField textField = new TextField(text, skin);
        textField.setSize(width, height);
        textField.setPosition(x, y, align);
        textField.setMaxLength(maxlength);
        return textField;
    }

    public static CheckBox createCheckBox(String text, Skin skin, float x, float y, int align){
        CheckBox checkBox = new CheckBox(text, skin);
        checkBox.setPosition(x, y, align);
        return checkBox;
    }

    public static SelectBox createSelectBox(Skin skin, String text, float width, float height, int maxListCount, float x,
                                            float y, int align, Object[] items, boolean isDisabled, boolean isVisible){
        SelectBox selectBox = new SelectBox(skin);
        selectBox.setName(text);
        selectBox.setSize(width, height);
        selectBox.setMaxListCount(maxListCount);
        selectBox.setPosition(x, y, align);
        selectBox.setItems(items);
        selectBox.setDisabled(isDisabled);
        selectBox.setVisible(isVisible);
        return selectBox;
    }

    public static Label createLabel(String text, Skin skin, float x, float y, int align, boolean visible){
        Label label = new Label(text, skin);
        label.setPosition(x, y, align);
        label.setVisible(visible);
        return label;
    }

    public static void dispose(){
        skin.dispose();
    }
}

package com.jpongsick.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

/**
 * Created by CalychasLaptop on 26.01.2017.
 */
public class MainMenuScreen implements Screen {
    private final JPongSick game;
    private OrthographicCamera camera;
    private Button buttonStart;
    private boolean isVisible;

    public MainMenuScreen(final JPongSick game, boolean isVisible) {
        this.game = game;
        this.isVisible = isVisible;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.width, Config.height);

        buttonStart = new TextButton("START GAME", Config.textButtonStyle);
        buttonStart.setPosition(Gdx.app.getGraphics().getWidth()/2, Gdx.app.getGraphics().getHeight()/2, Align.center);
        game.getStage().addActor(buttonStart);
    }

    @Override
    public void render(float delta) {
        if(game.getState() != State.MENU) return;
        Gdx.gl.glClearColor(0, 0.3f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
//        Config.font.draw(game.getBatch(), "Welcome to JPongSick!!! ", buttonStart.getWidth(), 150);
//        Config.font.draw(game.getBatch(), "Tap anywhere to begin!", 100, 100);
        game.getBatch().end();

        if (buttonStart.isPressed()) {
            hide();
            game.getGameScreen().show();
            game.setScreen(game.getGameScreen());
        }


    }

    @Override
    public void show() {
        this.isVisible = true;
        game.setState(State.MENU);
        buttonStart.setVisible(true);
    }

    @Override
    public void hide() {
        this.isVisible = false;
        buttonStart.setVisible(false);


    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }
}

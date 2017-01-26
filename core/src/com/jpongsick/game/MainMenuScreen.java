package com.jpongsick.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by CalychasLaptop on 26.01.2017.
 */
public class MainMenuScreen implements Screen {
    private final JPongSick game;
    private OrthographicCamera camera;

    public MainMenuScreen(final JPongSick game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "Welcome to Drop!!! ", 100, 150);
        game.getFont().draw(game.getBatch(), "Tap anywhere to begin!", 100, 100);
        game.getBatch().end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

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

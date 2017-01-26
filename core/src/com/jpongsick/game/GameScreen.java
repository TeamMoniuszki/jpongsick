package com.jpongsick.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by CalychasLaptop on 26.01.2017.
 */
public class GameScreen implements Screen {
    private final JPongSick game;
    private OrthographicCamera camera;

    public GameScreen(final JPongSick game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.width, Config.height);
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);


        game.getBatch().begin();


        game.getBatch().end();
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

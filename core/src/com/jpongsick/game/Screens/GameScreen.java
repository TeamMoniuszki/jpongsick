package com.jpongsick.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.jpongsick.game.Config;
import com.jpongsick.game.Entities.*;
import com.jpongsick.game.JPongSick;
import com.jpongsick.game.Physics;
import com.jpongsick.game.Util.State;


public class GameScreen implements Screen {
    private final JPongSick game;
    private OrthographicCamera camera;
    private boolean isVisible;
    private Ball ball;
    private Player player1;
    private Player player2;

    public GameScreen(final JPongSick game,  boolean isVisible) {
        this.game = game;
        this.isVisible = isVisible;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.width, Config.height);

        PlayerManager.initialize();

        this.ball = new Ball(0, 0, 15);
        this.player1 = PlayerManager.createPlayer(new Platform(0, 0, 10, 100), new Score(), "Waldek");
        this.player2 = PlayerManager.createPlayer(new Platform(0, 0, 10, 100), new Score(), "Zdzichu");

        ball.setCenter(Gdx.app.getGraphics().getWidth() / 2, Gdx.app.getGraphics().getHeight() / 2);
        player1.getPlatform().setCenter(15, Gdx.app.getGraphics().getHeight() / 2);
        player2.getPlatform().setCenter(Gdx.app.getGraphics().getWidth() - 15, Gdx.app.getGraphics().getHeight() / 2);

        Physics.initialize(this.ball, this.player1.getPlatform(), this.player2.getPlatform());
    }



    public void draw() {
        game.getBatch().begin();

        game.getBatch().draw(ball.texture, ball.x, ball.y);
        game.getBatch().draw(player1.getPlatform().texture, player1.getPlatform().x, player1.getPlatform().y);
        game.getBatch().draw(player2.getPlatform().texture, player2.getPlatform().x, player2.getPlatform().y);

        game.getBatch().end();
    }

    @Override
    public void render(float delta) {
        if (game.getState() != State.PLAYING) return;
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        Physics.update();
        this.draw();

    }

    @Override
    public void show() {
        game.setState(State.PLAYING);
        this.isVisible = true;
    }

    @Override
    public void hide() {
        this.isVisible = false;
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

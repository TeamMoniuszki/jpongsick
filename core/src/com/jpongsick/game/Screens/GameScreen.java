package com.jpongsick.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.jpongsick.game.Config;
import com.jpongsick.game.Entities.*;
import com.jpongsick.game.JPongSick;
import com.jpongsick.game.Physics;
import com.jpongsick.game.Util.State;
import com.badlogic.gdx.utils.Align;


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
        camera.update();
        this.ball = new Ball(0, 0, 15);
        this.ball.setCenter(Config.halfWidth, Config.halfHeight);

        this.player1 = PlayerManager.createPlayer(new Platform(0, 0, 10, 100), new Score(), "Player1");
        this.player2 = PlayerManager.createPlayer(new Platform(0, 0, 10, 100), new Score(), "Player2");
        this.player1.getPlatform().setCenter(25, Config.halfHeight);
        this.player2.getPlatform().setCenter(Config.width - 25, Config.halfHeight);

        this.player1.getLabel().setPosition(Config.width / 4f, 7 * Config.height / 8f, Align.center);
        this.player2.getLabel().setPosition(0.75f * Config.width , 7 * Config.height / 8f, Align.center);
        this.player1.getLabel().setVisible(false);
        this.player2.getLabel().setVisible(false);

        game.addActor(this.player1.getLabel());
        game.addActor(this.player2.getLabel());

        Physics.initialize(this.ball, this.player1.getPlatform(), this.player2.getPlatform(), game);
    }


    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        if (game.getState() == State.PLAYING) {
            Physics.update();
        }

        this.draw();
    }

    @Override
    public void show() {
        this.isVisible = true;

        this.player1.setNickname(game.getMainMenuScreen().getNickInput1().getText());
        this.player1.updateLabel();
        this.player1.getLabel().setVisible(true);

        if(game.getMainMenuScreen().getNickInput2().isDisabled()){
            this.player2.setNickname(AI.getRandomNickname());
        }
        else {
            this.player2.setNickname(game.getMainMenuScreen().getNickInput2().getText());
        }
        this.player2.updateLabel();
        this.player2.getLabel().setVisible(true);

    }

    @Override
    public void hide() {
        this.isVisible = false;
        this.player1.getLabel().setVisible(false);
        this.player2.getLabel().setVisible(false);
        Physics.resetPhysics();
    }

    @Override
    public void pause() { // nie wolno uzywac (jak narazie)

    }

    @Override
    public void resume() { // nie wolno uzywac (jak narazie)

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {

    }

}

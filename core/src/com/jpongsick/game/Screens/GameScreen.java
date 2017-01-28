package com.jpongsick.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jpongsick.game.Config;
import com.jpongsick.game.Entities.*;
import com.jpongsick.game.JPongSick;
import com.jpongsick.game.Physics;
import com.jpongsick.game.Util.State;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;


public class GameScreen implements Screen {
    private final JPongSick game;
    private OrthographicCamera camera;
    private boolean isVisible;
    private Ball ball;
    private Player player1;
    private Player player2;
//    private Viewport viewport;

    private Label score1;
    private Label score2;
    private Label pauseMessage;

    public GameScreen(final JPongSick game,  boolean isVisible) {
        this.game = game;
        this.isVisible = isVisible;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.width, Config.height);
//        viewport = new FitViewport(Config.width, Config.height, camera);

        this.ball = new Ball(0, 0, 15);
        this.ball.setCenter(Gdx.app.getGraphics().getWidth() / 2, Gdx.app.getGraphics().getHeight() / 2);

        this.player1 = PlayerManager.createPlayer(new Platform(0, 0, 10, 100), new Score(), "Waldek");
        this.player2 = PlayerManager.createPlayer(new Platform(0, 0, 10, 100), new Score(), "Zdzichu");
        this.player1.getPlatform().setCenter(25, Gdx.app.getGraphics().getHeight() / 2);
        this.player2.getPlatform().setCenter(Gdx.app.getGraphics().getWidth() - 25, Gdx.app.getGraphics().getHeight() / 2);

        this.pauseMessage = new Label("PRESS ANY KEY TO CONTINUE", new LabelStyle(Config.font, Color.WHITE));
        this.pauseMessage.setPosition(Gdx.app.getGraphics().getWidth() / 2f, Gdx.app.getGraphics().getHeight() / 2f, Align.center);
        this.pauseMessage.setVisible(false);
        game.getStage().addActor(pauseMessage);


        this.score1 = new Label(player1.getNickname() + ": " + player1.getScore().getPoints(), new LabelStyle(Config.font, Color.WHITE));
        this.score2 = new Label(player2.getNickname() + ": " + player2.getScore().getPoints(), new LabelStyle(Config.font, Color.WHITE));
        this.score1.setPosition(Gdx.app.getGraphics().getWidth() / 4f, 7 * Gdx.app.getGraphics().getHeight() / 8f, Align.center);
        this.score2.setPosition(0.75f * Gdx.app.getGraphics().getWidth() , 7 * Gdx.app.getGraphics().getHeight() / 8f, Align.center);
        this.score1.setVisible(false);
        this.score2.setVisible(false);
        game.getStage().addActor(score1);
        game.getStage().addActor(score2);

        Physics.initialize(this.ball, this.player1.getPlatform(), this.player2.getPlatform(), game);
    }



    public void draw() {
        game.getBatch().begin();

        game.getBatch().draw(ball.texture, ball.x, ball.y);
        game.getBatch().draw(player1.getPlatform().texture, player1.getPlatform().x, player1.getPlatform().y);
        game.getBatch().draw(player2.getPlatform().texture, player2.getPlatform().x, player2.getPlatform().y);
        game.getBatch().draw(player2.getPlatform().texture, player2.getPlatform().x, player2.getPlatform().y);

        game.getBatch().end();
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

    public Label getScore1() {
        return score1;
    }

    public void setScore1(Label score1) {
        this.score1 = score1;
    }

    public Label getScore2() {
        return score2;
    }

    public void setScore2(Label score2) {
        this.score2 = score2;
    }

    @Override
    public void render(float delta) {
        if (game.getState() != State.PLAYING) return;
        this.pauseMessage.setVisible(false); //FIXME KAPRAWOSC OVERFLOW, NIE DZIALA W RESUME
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
        this.score1.setVisible(true);
        this.score2.setVisible(true);
        this.pauseMessage.setVisible(false);
    }

    @Override
    public void hide() {
        this.isVisible = false;
        this.score1.setVisible(false);
        this.score2.setVisible(false);
        this.pauseMessage.setVisible(false);
    }

    @Override
    public void pause() {
        game.setState(State.PAUSE);
        this.pauseMessage.setVisible(true);
        if(Gdx.input.isTouched()|| Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
            resume();
        }
    }

    @Override
    public void resume() {
//        this.pauseMessage.setVisible(false);  FIXME KAPRAWOSC OVERFLOW V2
        game.setState(State.PLAYING);
    }

    @Override
    public void resize(int width, int height) {
//        viewport.update(width, height);
//        game.getStage().getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {

    }

}

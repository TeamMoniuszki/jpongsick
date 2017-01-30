package com.jpongsick.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.jpongsick.game.Config;
import com.jpongsick.game.FacadeObserver;
import com.jpongsick.game.Input;
import com.jpongsick.game.JPongSick;
import com.jpongsick.game.Util.State;


public class MainMenuScreen implements Screen {
    private final JPongSick game;
    private OrthographicCamera camera;
    private Button buttonStart;
    private boolean isVisible;
    private TextField nickInput1;
    private TextField nickInput2;
    private CheckBox aiCheckbox;

    public MainMenuScreen(final JPongSick game, boolean isVisible) {
        this.game = game;
        this.isVisible = isVisible;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.width, Config.height);

        buttonStart = new TextButton("START GAME", Config.skin);
        buttonStart.setPosition(Config.halfWidth, Config.halfHeight, Align.center);

        nickInput1 = new TextField("Player 1", Config.skin);
        nickInput2 = new TextField("Player 2", Config.skin);
        nickInput1.setPosition(Config.width/3f, Config.height/3f, Align.center);
        nickInput2.setPosition(2*Config.width/3f, Config.height/3f, Align.center);
        nickInput1.setMaxLength(15);
        nickInput2.setMaxLength(15);

        aiCheckbox = new CheckBox("BOT", Config.skin);
        aiCheckbox.setPosition(Config.halfWidth, Config.height/5f, Align.center);

        game.getStage().addActor(buttonStart);
        game.getStage().addActor(nickInput1);
        game.getStage().addActor(nickInput2);
        game.getStage().addActor(aiCheckbox);
    }

    public TextField getNickInput1() {
        return nickInput1;
    }

    public void setNickInput1(TextField nickInput1) {
        this.nickInput1 = nickInput1;
    }

    public TextField getNickInput2() {
        return nickInput2;
    }

    public void setNickInput2(TextField nickInput2) {
        this.nickInput2 = nickInput2;
    }

    public void draw() {
        game.getBatch().begin();

        game.getBatch().end();
    }

    @Override
    public void render(float delta) {
        if(game.getState() != State.MENU) return;
        if(aiCheckbox.isChecked()){
            nickInput2.setDisabled(true);
        }
        else {
            nickInput2.setDisabled(false);
        }
        Gdx.gl.glClearColor(0, 0.3f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
        this.draw();

        if (buttonStart.isPressed()) {
            FacadeObserver.isAIGame = aiCheckbox.isChecked();
            hide();
            game.getGameScreen().show();
            FacadeObserver.notify(FacadeObserver.Event.RESUME_GAME);
            game.setScreen(game.getGameScreen());
        }

    }

    @Override
    public void show() {
        this.isVisible = true;
        game.setState(State.MENU);
        buttonStart.setVisible(true);
        nickInput1.setText(game.getGameScreen().getPlayer1().getNickname());
        nickInput2.setText(game.getGameScreen().getPlayer2().getNickname());
        nickInput1.setVisible(true);
        nickInput2.setVisible(true);
        aiCheckbox.setVisible(true);
    }

    @Override
    public void hide() {
        this.isVisible = false;
        buttonStart.setVisible(false);
        nickInput1.setVisible(false);
        nickInput2.setVisible(false);
        aiCheckbox.setVisible(false);
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

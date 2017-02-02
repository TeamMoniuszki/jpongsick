package com.jpongsick.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.jpongsick.game.*;
import com.jpongsick.game.Entities.AI;
import com.jpongsick.game.Util.UIManager;
import com.jpongsick.game.Util.State;


public class MainMenuScreen implements Screen {
    private final JPongSick game;
    private OrthographicCamera camera;
    private Button buttonStart;
    private boolean isVisible;
    private TextField nickInput1;
    private TextField nickInput2;
    private CheckBox aiCheckbox;
    private SelectBox difficultySelection;

    public MainMenuScreen(final JPongSick game, boolean isVisible) {
        this.game = game;
        this.isVisible = isVisible;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.width, Config.height);

        buttonStart = UIManager.createTextButton("START GAME", UIManager.skin, (float)(0.1875 * Config.width), (float)(Config.height/12), Config.halfWidth, Config.halfHeight, Align.center);
        nickInput1 = UIManager.createTextField("Player 1", UIManager.skin, (float)(0.1875 * Config.width), (float)(Config.height/20), Config.width/3f, Config.height/3f, Align.center, 15);
        nickInput2 = UIManager.createTextField("Player 2", UIManager.skin, (float)(0.1875 * Config.width), (float)(Config.height/20), 2*Config.width/3f, Config.height/3f, Align.center, 15);
        aiCheckbox = UIManager.createCheckBox("BOT", UIManager.skin, Config.halfWidth, Config.height/5f, Align.center);
        difficultySelection = UIManager.createSelectBox(UIManager.skin, "Difficulty", Config.width/8, Config.height/20, 3, Config.halfWidth, Config.halfHeight/4, Align.center, AI.Difficulty.values(), true, false);


        game.addActor(buttonStart);
        game.addActor(nickInput1);
        game.addActor(nickInput2);
        game.addActor(aiCheckbox);
        game.addActor(difficultySelection);
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

    public CheckBox getAiCheckbox() {
        return aiCheckbox;
    }

    public void setAiCheckbox(CheckBox aiCheckbox) {
        this.aiCheckbox = aiCheckbox;
    }

    public void draw() {
        game.getBatch().begin();

        game.getBatch().end();
    }

    @Override
    public void render(float delta) {
        if(game.getState() != State.MENU) return;
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();  //Necessary?
        game.getBatch().setProjectionMatrix(camera.combined);
        nickInput2.setDisabled(aiCheckbox.isChecked());
        difficultySelection.setDisabled(!aiCheckbox.isChecked());
        difficultySelection.setVisible(aiCheckbox.isChecked());

        this.draw();

        if (buttonStart.isPressed()) {
            Logic.isAIGame = aiCheckbox.isChecked();
            if(difficultySelection.getSelected()!=null){
                AI.setDifficulty((AI.Difficulty)difficultySelection.getSelected());
            }
            Logic.handle(Logic.Event.NEW_GAME);
        }

    }

    @Override
    public void show() {
        this.isVisible = true;
        game.setState(State.MENU);
        buttonStart.setVisible(true);
        nickInput1.setText(game.getGameScreen().getPlayer1().getNickname().toUpperCase());
        nickInput2.setText(game.getGameScreen().getPlayer2().getNickname().toUpperCase());
        nickInput1.setVisible(true);
        nickInput2.setVisible(true);
        aiCheckbox.setVisible(true);
        difficultySelection.setVisible(aiCheckbox.isChecked());
    }

    @Override
    public void hide() {
        this.isVisible = false;
        buttonStart.setVisible(false);
        nickInput1.setVisible(false);
        nickInput2.setVisible(false);
        aiCheckbox.setVisible(false);
        difficultySelection.setVisible(false);
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

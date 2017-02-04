package com.jpongsick.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.jpongsick.game.Config;
import com.jpongsick.game.Entities.Ball;
import com.jpongsick.game.Entities.Platform;
import com.jpongsick.game.JPongSick;
import com.jpongsick.game.Util.Announcer;
import com.jpongsick.game.Util.State;
import com.jpongsick.game.Util.UIManager;

/**
 * Created by Szymon on 04.02.2017.
 */
public class DevConsoleScreen implements Screen {
    private TextField input;
    JPongSick game;
    boolean isVisible;
    private OrthographicCamera camera;
    private Screen lastScreen;
    private State lastState;
    private float accumulator;


    public DevConsoleScreen (JPongSick game, boolean isVisible){
        this.game = game;
        this.isVisible = isVisible;
        this.accumulator = 0;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.width, Config.height);
        input = UIManager.createTextField("", UIManager.skin, 800, 30, 0, Config.height-1, Align.topLeft, 30);
        this.hide();
        game.addActor(input);
    }

    public Screen getLastScreen() {
        return lastScreen;
    }

    public void setLastScreen(Screen lastScreen) {
        this.lastScreen = lastScreen;
    }

    public State getLastState() {
        return lastState;
    }

    public void setLastState(State lastState) {
        this.lastState = lastState;
    }

    public TextField getInput() {
        return input;
    }

    public void setInput(TextField input) {
        this.input = input;
    }

    public void executeCommand(){
        String[] split = input.getText().split(" ");
        if(split[0].equals("setBallSpeed")){
            game.getGameScreen().getBall().setSpeed(Float.parseFloat(split[1]), Float.parseFloat(split[2]));
            accumulator = 0;
            Announcer.setText("Command executed");
            Announcer.showLabel();
            input.setText("");
        }
        else if(split[0].equals("setBallLen")){
            Ball.len = Integer.parseInt(split[1]);
            input.setText("");
        }

        else if(split[0].equals("setPlatformSpeed")){
            Platform.speed = Integer.parseInt(split[1]);
            input.setText("");
        }

        else if(split[0].equals("resetVariables")){
            Platform.resetSpeed();
            Ball.resetLen();
            game.getGameScreen().getBall().resetSpeed();
            input.setText("");
        }


    }

    @Override
    public void show() {
        this.input.setDisabled(false);
        this.input.setVisible(true);
    }

    @Override
    public void render(float delta) { //FIXME: MAKE THE LABEL DISAPPEAR BY CLEARING THE BACKGROUND WITH ALPHA CHANNEL
        accumulator+=Gdx.app.getGraphics().getDeltaTime();
        if(accumulator>=3.0f){
            Announcer.hideLabel();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        this.input.setDisabled(true);
        this.input.setVisible(false);
    }

    @Override
    public void dispose() {

    }
}

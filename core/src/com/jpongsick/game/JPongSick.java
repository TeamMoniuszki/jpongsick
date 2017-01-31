package com.jpongsick.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jpongsick.game.Entities.AI;
import com.jpongsick.game.Entities.PlayerManager;
import com.jpongsick.game.Screens.GameScreen;
import com.jpongsick.game.Util.Announcer;
import com.jpongsick.game.Util.State;
import com.jpongsick.game.Screens.MainMenuScreen;

public class JPongSick extends Game {
	private SpriteBatch batch;
	private Stage stage;
	private MainMenuScreen mainMenuScreen;
	private GameScreen gameScreen;
	private State state;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public SpriteBatch getBatch() {
		return this.batch;
	}

	public MainMenuScreen getMainMenuScreen() {
		return this.mainMenuScreen;
	}

	public GameScreen getGameScreen() {
		return this.gameScreen;
	}

	public Stage getStage() {
		return this.stage;
	}

	@Override
	public void create () {
		//Order is important
		Config.initialize();
		Input.initialize(this);
		AI.initialize(this);
		PlayerManager.initialize();
		Logic.initialize(this);

		this.batch = new SpriteBatch();
		this.stage = new Stage();
		this.state = State.MENU;
		this.mainMenuScreen = new MainMenuScreen(this, true);
		this.gameScreen = new GameScreen(this, false);

		Gdx.input.setInputProcessor(stage);
		this.setScreen(mainMenuScreen);

        //Announcer must be initialized last
        Announcer.initialize(this);
	}

	@Override
	public void render () {
		super.render();
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
        Input.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		Config.dispose();
	}
}

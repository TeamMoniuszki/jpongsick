package com.jpongsick.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JPongSick extends Game {
	private SpriteBatch batch;
	private BitmapFont font;


	public SpriteBatch getBatch() {
		return this.batch;
	}

	public BitmapFont getFont() {
		return this.font;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();

		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}

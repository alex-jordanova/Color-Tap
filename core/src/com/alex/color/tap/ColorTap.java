package com.alex.color.tap;

import com.alex.color.tap.assets.Assets;
import com.alex.color.tap.screen.GameScreen;
import com.alex.color.tap.screen.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Pongy extends Game {
    public static int WIDTH = 2520;
    public static int HEIGHT = 4160;

	private GameState gameState;
	private Assets assets;
	private int highscore = 0;

	public ColorTap() {
		assets = new Assets();
	}
	
	@Override
	public void create () {
		setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		assets.dispose();
	}

	public Assets getAssets() {
		return assets;
	}

	public int getHighscore() {
		return highscore;
	}

	public void updateHighscore(int score) {
		highscore = score;
	}

	public void setGameState(GameState state) {
		this.gameState = state;
	}

}

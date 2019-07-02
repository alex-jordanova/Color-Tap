package com.alex.color.tap.screen;

import com.alex.color.tap.ColorTap;
import com.alex.color.tap.GameState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen implements Screen {

    private ColorTap colorTap;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private static final String PLAY = "Tap to play";

    public MenuScreen(ColorTap colorTap) {
        this.colorTap = colorTap;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, ColorTap.WIDTH, ColorTap.HEIGHT);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(7/255f, 7/255f, 7/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);


        batch.begin();
        batch.draw(new Texture("logo.jpg"), 0, 0);
        SplashScreen.font.draw(batch, PLAY, ColorTap.WIDTH / 2.5f, ColorTap.HEIGHT / 4f);
        String highscore = "Highscore: " + colorTap.getHighscore();
        SplashScreen.font.draw(batch, highscore, ColorTap.WIDTH / 2.5f, ColorTap.HEIGHT / 6f);
        batch.end();

        if (Gdx.input.justTouched()) {
            colorTap.setGameState(GameState.PLAYING);
            colorTap.setScreen(new GameScreen(colorTap));
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

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}

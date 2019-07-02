package com.alex.color.tap.screen;

import com.alex.color.tap.ColorTap;
import com.alex.color.tap.assets.Assets;
import com.alex.color.tap.world.GameWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
    private ColorTap colorTap;
    private SpriteBatch batch;
    private GameWorld world;
    private OrthographicCamera camera;

    public GameScreen(ColorTap colorTap) {
        this.colorTap = colorTap;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, colorTap.WIDTH, colorTap.HEIGHT);
        world = new GameWorld(colorTap);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(7/255f, 7/255f, 7/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        String score = "Score: " + world.getScore();
        SplashScreen.font.draw(batch, score, ColorTap.WIDTH / 2.5f, ColorTap.HEIGHT  - 100f);
        batch.end();

        world.render();
        world.update();
        camera.update();

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

package com.alex.color.tap.screen;

import com.alex.color.tap.GameState;
import com.alex.color.tap.assets.Assets;
import com.alex.color.tap.ColorTap;
import com.alex.color.tap.assets.BallTextures;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import net.dermetfan.gdx.assets.AnnotationAssetManager;

public class SplashScreen implements Screen {
    private ColorTap colorTap;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private final String LOADING = "Loading...";


    public static BitmapFont font;

    public SplashScreen(ColorTap colorTap) {
        this.colorTap = colorTap;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, ColorTap.WIDTH, ColorTap.HEIGHT);


        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(new FileHandle("Bebas-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 72;
        font = generator.generateFont(parameter);
        font.getData().setScale(2);
        generator.dispose();
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

        drawSplash();
        loadAssets(colorTap.getAssets());
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

    private void drawSplash() {
        batch.begin();
        batch.draw(new Texture("logo.jpg"), 0, 0);
        font.draw(batch, LOADING, ColorTap.WIDTH / 2.3f, ColorTap.HEIGHT / 4f);
        batch.end();

    }

    private void loadAssets(Assets assets) {
        assets.load();
        if (assets.areLoaded()) {
            BallTextures.createBallTextures(assets.getManager().get(Assets.balls));
            colorTap.setGameState(GameState.MENU);
            colorTap.setScreen(new MenuScreen(colorTap));
        }
    }
}

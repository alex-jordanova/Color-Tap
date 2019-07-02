package com.alex.color.tap.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
    public static final AssetDescriptor<Texture> balls = new AssetDescriptor<Texture>("balls.png", Texture.class);
    public static final AssetDescriptor<Texture> paddle = new AssetDescriptor<Texture>("paddle.png", Texture.class);

    private static AssetManager manager;

    public Assets() {
        manager = new AssetManager();

    }

    public void load() {
        manager.load(balls);
        manager.load(paddle);
    }

    public static AssetManager getManager() {
        return manager;
    }

    public boolean areLoaded() {
        return manager.update();
    }



    public void dispose() {
        manager.dispose();
    }
}

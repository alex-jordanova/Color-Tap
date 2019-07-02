package com.alex.color.tap.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BallTextures {
    public static  TextureRegion redBall;
    public static  TextureRegion purpleBall;
    public static  TextureRegion greenBall;
    public static  TextureRegion yellowBall;

    private static final int IMG_SIZE = 631;

    public static void createBallTextures(Texture balls) {
        TextureAtlas atlas = new TextureAtlas();
        atlas.addRegion("redBall", balls, 0 , 0, IMG_SIZE, IMG_SIZE);
        atlas.addRegion("purpleBall", balls, IMG_SIZE, 0, IMG_SIZE, IMG_SIZE);
        atlas. addRegion("greenBall", balls, 2 * IMG_SIZE, 0, IMG_SIZE, IMG_SIZE);
        atlas.addRegion("yellowBall", balls, 3 *IMG_SIZE, 0, IMG_SIZE, IMG_SIZE);
        redBall = atlas.findRegion("redBall");
        purpleBall = atlas.findRegion("purpleBall");
        greenBall = atlas.findRegion("greenBall");
        yellowBall = atlas.findRegion("yellowBall");

    }
}

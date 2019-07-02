package com.alex.color.tap.model;

import com.alex.color.tap.assets.BallTextures;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum BallColor {
    RED (BallTextures.redBall),
    PURPLE (BallTextures.purpleBall),
    GREEN (BallTextures.greenBall),
    YELLOW (BallTextures.yellowBall);

    private TextureRegion ball;

    BallColor(TextureRegion ball) {
        this.ball = ball;
    }

    public TextureRegion getTextureRegion() {
        return ball;
    }

    public static BallColor generateColor() {
        List<BallColor> colors = new ArrayList<BallColor>(Arrays.asList(values()));
        return colors.get(new Random().nextInt(colors.size()));
    }
}

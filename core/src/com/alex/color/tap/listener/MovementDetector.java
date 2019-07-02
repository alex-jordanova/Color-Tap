package com.alex.color.tap.listener;


import com.alex.color.tap.model.Ball;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class MovementDetector extends DragListener {

    private Ball ball;

    public MovementDetector(Ball ball) {
        this.ball = ball;
    }

    @Override
    public void drag(InputEvent event, float x, float y, int pointer) {
         ball.moveBy(x - ball.getWidth() / 2, y - ball.getHeight() / 2);
    }



}

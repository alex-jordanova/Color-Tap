package com.alex.color.tap.listener;

import com.alex.color.tap.model.Ball;
import com.alex.color.tap.model.GamePiece;
import com.alex.color.tap.world.GameWorld;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;


public class CollisionDetector implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        GamePiece firstObject = (GamePiece) contact.getFixtureA().getUserData();
        GamePiece secondObject = (GamePiece) contact.getFixtureB().getUserData();

        if (firstObject.isBall() && secondObject.isPaddle()) {
            Ball ball = (Ball) contact.getFixtureA().getUserData();
            ball.hit();
            GameWorld.updateScore();
        } else if (secondObject.isBall() && firstObject.isPaddle()) {
            Ball ball = (Ball) contact.getFixtureB().getUserData();
            ball.hit();
            GameWorld.updateScore();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

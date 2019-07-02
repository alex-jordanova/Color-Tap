package com.alex.color.tap.model;

import com.alex.color.tap.world.GameWorld;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bar extends Actor implements GamePiece {
    private Body body;
    private World physicsWorld;
    private float width = 0.2f;
    private float height = GameWorld.GAME_HEIGHT;

    public Bar(World physicsWorld, float x, float y) {
        this.physicsWorld = physicsWorld;
        setCharacteristics(x, y);
        initializeBody();
    }


    private void initializeBody() {
        body = physicsWorld.createBody(getBodyDefinition());
        FixtureDef fix = getFixtureDefinition();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);
        fix.shape = shape;

        body.createFixture(fix).setUserData(this);
        body.setUserData(this);

        shape.dispose();
    }

    @Override
    public void act(float delta) {
        body.setLinearVelocity(1, 0);
    }

    public boolean isBall() {
        return false;
    }

    public boolean isPaddle() {
        return false;
    }

    private void setCharacteristics(float x, float y) {
        this.setPosition(x, y);
        this.setSize(width, height);
    }

    private BodyDef getBodyDefinition() {
        BodyDef bodyDefinition = new BodyDef();
        bodyDefinition.type = BodyDef.BodyType.StaticBody;
        bodyDefinition.position.set(this.getX(), this.getY());
        return bodyDefinition;
    }

    private FixtureDef getFixtureDefinition() {
        FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.density = 25.5f;
        fixtureDefinition.friction = 0.3f;
        fixtureDefinition.restitution = 1f;
        return fixtureDefinition;
    }

}

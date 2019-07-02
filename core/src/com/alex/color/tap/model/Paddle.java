package com.alex.color.tap.model;

import com.alex.color.tap.assets.Assets;
import com.alex.color.tap.world.GameWorld;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Paddle extends Actor implements GamePiece {

    private Body body;
    private World physicsWorld;
    private Texture texture;
    private static final float width =  GameWorld.GAME_WIDTH / 6;
    private static final float height = 0.5f;

    public Paddle(World physicsWorld, float x, float y) {
        this.physicsWorld = physicsWorld;
        texture = Assets.getManager().get(Assets.paddle);
        setCharacteristics(x, y);
        initializeBody();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), width * 2, height * 2);
    }

    @Override
    public void act(float delta) {
        body.setLinearVelocity(0, 0);
        this.setPosition(body.getPosition().x - getWidth(), body.getPosition().y - getHeight());
    }

    public void moveLeft() {
        body.setLinearVelocity(-20f, 0);
        this.setPosition(body.getPosition().x - getWidth(), body.getPosition().y - getHeight());
    }

    public void moveRight() {
        body.setLinearVelocity(20f, 0);
        this.setPosition(body.getPosition().x - getWidth(), body.getPosition().y - getHeight());
    }

    public boolean isBall() {
        return false;
    }

    public boolean isPaddle() {
        return true;
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
    private void setCharacteristics(float x, float y) {
        this.setPosition(x, y);
        this.setSize(width, height);
    }

    private BodyDef getBodyDefinition() {
        BodyDef bodyDefinition = new BodyDef();
        bodyDefinition.type = BodyDef.BodyType.KinematicBody;
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

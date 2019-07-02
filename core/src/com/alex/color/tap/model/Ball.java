package com.alex.color.tap.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.Collections;

public class Ball extends Actor implements GamePiece {
    private Body body;
    private World physicsWorld;
    private BallColor ballColor;
    private float radius;

    public Ball(World physicsWorld, float x, float y) {
        this.physicsWorld = physicsWorld;
        ballColor = BallColor.generateColor();
        radius = ballColor.getTextureRegion().getRegionWidth() / 4;
        setCharacteristics(x, y);
        initializeBody();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
       batch.draw(ballColor.getTextureRegion(), getX(), getY(), getWidth(), getHeight());
    }


    @Override
    public void act(float delta) {
        this.setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        body.applyForceToCenter(0, -30f, false);
    }

    public void hit() {
        body.applyLinearImpulse(generateDirection(), 2, -10, 2 , false);
    }

    private void initializeBody() {
        body = physicsWorld.createBody(getBodyDefinition());
        FixtureDef fix = getFixtureDefinition();

        CircleShape shape = new CircleShape();
        shape.setRadius(radius / 160);
        fix.shape = shape;

        body.createFixture(fix).setUserData(this);
        body.setUserData(this);
        body.setBullet(true);
        body.setLinearDamping(0f);

        shape.dispose();
    }

    public boolean hasFallen() {
        return getY() < 0;
    }

    public boolean isBall() {
        return true;
    }

    public boolean isPaddle() {
        return false;
    }

    private void setCharacteristics(float x, float y) {
        this.setPosition(x, y);
        this.setSize(2*radius / 160, 2*radius / 160);
        this.setOrigin(radius / 160, radius / 160);
    }

    private BodyDef getBodyDefinition() {
        BodyDef bodyDefinition = new BodyDef();
        bodyDefinition.type = BodyDef.BodyType.DynamicBody;
        bodyDefinition.position.set(this.getX(), this.getY());
        bodyDefinition.linearDamping = 0.1f;
        bodyDefinition.angularDamping = 0.5f;
        return bodyDefinition;
    }

    private FixtureDef getFixtureDefinition() {
        FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.density = 0.9f;
        fixtureDefinition.friction = 0f;
        fixtureDefinition.restitution = 1f;
        return fixtureDefinition;
    }


    private int generateDirection() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(5);
        numbers.add(-5);
        Collections.shuffle(numbers);

        return numbers.get(0);
    }

}

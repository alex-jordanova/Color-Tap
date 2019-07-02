package com.alex.color.tap.world;


import com.alex.color.tap.ColorTap;
import com.alex.color.tap.GameState;
import com.alex.color.tap.listener.CollisionDetector;
import com.alex.color.tap.model.Ball;
import com.alex.color.tap.model.Bar;
import com.alex.color.tap.model.Paddle;
import com.alex.color.tap.screen.MenuScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameWorld {

    private ColorTap colorTap;
    private World physicsWorld;
    private Stage stage;
    private Ball ball;
    private static Integer score;
    private Paddle paddle;
    private Bar leftWall;
    private Bar rightWall;
    private Box2DDebugRenderer renderer;

    public static final float GAME_WIDTH = ColorTap.WIDTH / 160;
    public static final float GAME_HEIGHT = ColorTap.HEIGHT / 160;

    public GameWorld(ColorTap colorTap) {
        this.colorTap = colorTap;
        physicsWorld = new World(new Vector2(0, -9.8f), false);
        score = new Integer(0);
        physicsWorld.setContactListener(new CollisionDetector());

        stage = new Stage(new StretchViewport(GAME_WIDTH, GAME_HEIGHT));
        initializeActors();
        prepareStage();
    }

    public void render() {
        stage.draw();
    }

    public void update() {
        stage.act(Gdx.graphics.getDeltaTime());
        if (isLeftSideTouched()) {
            paddle.moveLeft();
        } else if (isRightSideTouched()) {
            paddle.moveRight();
        }
        if (ball.hasFallen()) {
            endGame();
        }
        physicsWorld.step(Gdx.graphics.getDeltaTime(),6,2);
    }

    public static void updateScore() {
        score++;
    }

    public static int getScore() {
        return score;
    }

    private boolean isLeftSideTouched() {
        return Gdx.input.justTouched() && Gdx.input.getX() <  Gdx.graphics.getWidth() / 2;
    }

    private boolean isRightSideTouched() {
        return Gdx.input.justTouched() && Gdx.input.getX() >  Gdx.graphics.getWidth() / 2;
    }

    private void endGame() {
        if (colorTap.getHighscore() < score) {
            colorTap.updateHighscore(score);
        }
        colorTap.setGameState(GameState.MENU);
        colorTap.setScreen(new MenuScreen(colorTap));
    }

    private void initializeActors() {
        paddle = new Paddle(physicsWorld, GAME_WIDTH / 2, 2);
        ball = new Ball(physicsWorld, GAME_WIDTH / 2, GAME_HEIGHT - 3);
        rightWall = new Bar(physicsWorld, GAME_WIDTH, 0);
        leftWall = new Bar(physicsWorld, 0, 0);
    }

    private void prepareStage() {
        stage.addActor(ball);
        stage.addActor(paddle);
        stage.addActor(rightWall);
        stage.addActor(leftWall);
    }

}

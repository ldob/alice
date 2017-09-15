package eu.ldob.alice.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

import eu.ldob.alice.Constants;

public class PlayerActor extends Actor {

    private boolean big;
    private boolean fast;

    private Texture textureRight;
    private Texture textureLeft;
    private Texture texture;

    private Vector2 position;

    public  PlayerActor() {
        this(false);
    }

    public PlayerActor(boolean big) {
        this(big, false);
    }

    public PlayerActor(boolean big, boolean fast) {
        this.big = big;
        this.fast = fast;

        this.textureRight = new Texture(Gdx.files.internal("player/mario_right.png"));
        this.textureLeft = new Texture(Gdx.files.internal("player/mario_left.png"));
        this.texture = textureRight;

        this.position = new Vector2(0, 0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, position.x, position.y, Constants.PLAYER_SIZE, Constants.PLAYER_SIZE);
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= delta * (fast ? 650f : 500f);
            texture = textureLeft;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += delta * (fast ? 650f : 500f);
            texture = textureRight;
        }

        float accelerometerInput = -Gdx.input.getAccelerometerY() / (Constants.GRAVITATIONAL_ACCELERATION * Constants.ACCELEROMETER_SENSITIVITY);
        position.x += -delta * accelerometerInput * (fast ? 140f : 100f);

        if(accelerometerInput > 0.1f) {
            texture = textureLeft;
        }
        else if(accelerometerInput < -0.1f) {
            texture = textureRight;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isTouched()) {
            startJump();
        }

        calculateJump(delta);
        ensureInBounds();
    }

    private void ensureInBounds() {
        if (position.x < 0) {
            position.x = 0;
        }
        if (position.x > Constants.WORLD_WIDTH - Constants.PLAYER_SIZE) {
            position.x = Constants.WORLD_WIDTH - Constants.PLAYER_SIZE;
        }
    }

    private float JUMP_TIME = 750f;
    private float JUMP_SCALE = 3f;

    private boolean isJumping = false;
    private long jumpTime;
    private void startJump() {
        if(!isJumping) {
            isJumping = true;
            jumpTime = System.currentTimeMillis();
        }
    }
    private void calculateJump(float delta) {
        if(!isJumping) {
            return;
        }

        long now = System.currentTimeMillis();
        long jumpDelta = now - jumpTime;

        if(position.y >= 0) {
            position.y += delta * (JUMP_TIME / 2 - jumpDelta) * JUMP_SCALE;
        }
        else {
            position.y = 0;
            isJumping = false;
        }
    }

    public List<AFood> hitFood(FoodActor foodList) {
        List<AFood> hitFood = new ArrayList<AFood>();

        for(AFood food : foodList) {
            if (food.getPosition().dst(position) < Constants.PLAYER_SIZE) {
                hitFood.add(food);
            }
        }

        return hitFood;
    }
}

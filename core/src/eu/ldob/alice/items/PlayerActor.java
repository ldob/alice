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

    private float sizeScale = 1f;
    private float speedScale = 1f;

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
        if(big) {
            sizeScale = 1.4f;
        }
        if(fast) {
            speedScale = 1.4f;
        }

        this.textureRight = new Texture(Gdx.files.internal("player/mario_right.png"));
        this.textureLeft = new Texture(Gdx.files.internal("player/mario_left.png"));
        this.texture = textureRight;

        this.setOrigin(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        this.position = new Vector2(Constants.PLAYER_WIDTH / 2, 0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, position.x, position.y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= delta * Constants.PLAYER_VELOCITY_SCALE * sizeScale;
            texture = textureLeft;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += delta * Constants.PLAYER_VELOCITY_SCALE * sizeScale;
            texture = textureRight;
        }

        float accelerometerInput = -Gdx.input.getAccelerometerY() / (Constants.GRAVITATIONAL_ACCELERATION * Constants.ACCELEROMETER_SENSITIVITY);
        position.x += -delta * accelerometerInput * Constants.PLAYER_VELOCITY_SCALE * sizeScale;

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
        if (position.x > Constants.WORLD_WIDTH - Constants.PLAYER_WIDTH) {
            position.x = Constants.WORLD_WIDTH - Constants.PLAYER_WIDTH;
        }
    }

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
            position.y += delta * (Constants.JUMP_TIME / 2 - jumpDelta) * Constants.JUMP_SCALE;
        }
        else {
            position.y = 0;
            isJumping = false;
        }
    }

    public List<AFood> hitFood(FoodActor foodList) {
        List<AFood> hitFood = new ArrayList<AFood>();

        Vector2 playerHeadCenter = new Vector2(position.x, position.y);
        playerHeadCenter.add(Constants.PLAYER_WIDTH / 2, Constants.PLAYER_HEIGHT * 3 / 4);

        for(AFood food : foodList) {
            Vector2 foodCenter = new Vector2(food.getPosition().x, food.getPosition().y);
            foodCenter.add(food.getWidth() / 2, food.getHeight() / 2);

            if (foodCenter.dst(playerHeadCenter) < Constants.PLAYER_HEIGHT / 2) {
                hitFood.add(food);
            }
        }

        return hitFood;
    }
}

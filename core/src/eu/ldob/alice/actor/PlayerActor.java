package eu.ldob.alice.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

import eu.ldob.alice.Constants;
import eu.ldob.alice.evaluation.Benefits;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;
import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP_PINGPONG;
import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.NORMAL;

public class PlayerActor extends Actor {

    private Benefits benefits;

    private Animation<Texture> currentAnimation;
    private Animation<Texture> animationStanding;
    private Animation<Texture> animationWalking;
    private Animation<Texture> animationRunning;
    private Animation<Texture> animationJumping;

    private float stateTime = 0f;

    private Vector2 position;

    private boolean flip;

    public PlayerActor(Benefits benefits) {

        this.benefits = benefits;

        Array<Texture> textureStanding = new Array<Texture>(2);
        textureStanding.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_STANDING_1)));
        textureStanding.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_STANDING_2)));

        Array<Texture> textureRunning = new Array<Texture>(6);
        textureRunning.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_RUNNING_1)));
        textureRunning.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_RUNNING_2)));
        textureRunning.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_RUNNING_3)));
        textureRunning.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_RUNNING_4)));
        textureRunning.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_RUNNING_5)));
        textureRunning.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_RUNNING_6)));

        Array<Texture> textureJumping = new Array<Texture>(6);
        textureJumping.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_JUMPING_1)));
        textureJumping.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_JUMPING_2)));
        textureJumping.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_JUMPING_3)));
        textureJumping.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_JUMPING_4)));
        textureJumping.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_JUMPING_5)));
        textureJumping.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_JUMPING_6)));

        /*
        Array<Texture> textureGameover = new Array<Texture>(2);
        textureGameover.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_GAMEOVER_1)));
        textureGameover.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_GAMEOVER_1)));
        textureGameover.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_GAMEOVER_1)));
        textureGameover.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_GAMEOVER_1)));
        textureGameover.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_GAMEOVER_1)));
        textureGameover.add(new Texture(Gdx.files.internal(Constants.PLAYER_TEXTURE_GAMEOVER_1)));
        */

        animationStanding = new Animation<Texture>(Constants.ANIMATION_FRAME_DURATION_STANDING, textureStanding, LOOP);
        animationWalking = new Animation<Texture>(Constants.ANIMATION_FRAME_DURATION_WALKING, textureRunning, LOOP);
        animationRunning = new Animation<Texture>(Constants.ANIMATION_FRAME_DURATION_RUNNING, textureRunning, LOOP);
        animationJumping = new Animation<Texture>(benefits.getJumpTime() / (1000f * textureJumping.size), textureJumping, NORMAL);

        currentAnimation = animationStanding;

        flip = false;

        this.setOrigin(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        this.position = new Vector2(Constants.WORLD_WIDTH / 2, 0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(currentAnimation.getKeyFrame(stateTime+=Gdx.graphics.getDeltaTime()), flip ? (position.x + Constants.PLAYER_WIDTH) : position.x, position.y, flip ? -Constants.PLAYER_WIDTH : Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
    }

    public void update(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= delta * Constants.PLAYER_VELOCITY_SCALE;
            setCurrentAnimation(animationRunning);
            flip = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += delta * Constants.PLAYER_VELOCITY_SCALE;
            setCurrentAnimation(animationRunning);
            flip = false;
        }

        float accelerometerInput = -Gdx.input.getAccelerometerY() / (Constants.GRAVITATIONAL_ACCELERATION * Constants.ACCELEROMETER_SENSITIVITY);
        position.x += -delta * accelerometerInput * Constants.PLAYER_VELOCITY_SCALE;

        if(accelerometerInput > 0.1f) {
            if(accelerometerInput > 0.5f) {
                setCurrentAnimation(animationRunning);
            }
            else {
                setCurrentAnimation(animationWalking);
            }

            flip = true;
        }
        else if(accelerometerInput < -0.1f) {
            if(accelerometerInput < -0.5f) {
                setCurrentAnimation(animationRunning);
            }
            else {
                setCurrentAnimation(animationWalking);
            }

            flip = false;
        }
        else if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            setCurrentAnimation(animationStanding);
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
            setCurrentAnimation(animationStanding);
        }
        else if (position.x > Constants.WORLD_WIDTH - Constants.PLAYER_WIDTH) {
            position.x = Constants.WORLD_WIDTH - Constants.PLAYER_WIDTH;
            setCurrentAnimation(animationStanding);
        }
    }

    private boolean isJumping = false;
    private long jumpTime;
    private void startJump() {
        if(!isJumping) {
            setCurrentAnimation(animationJumping);
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
            position.y += delta * (benefits.getJumpTime() / 2 - jumpDelta) * Constants.JUMP_SCALE;
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

    public void gameover() {
        setCurrentAnimation(animationStanding, true);
    }

    private void setCurrentAnimation(Animation<Texture> animation) {
        setCurrentAnimation(animation, false);
    }

    private void setCurrentAnimation(Animation<Texture> animation, boolean ignoreJumping) {
        if((!isJumping || ignoreJumping) && !currentAnimation.equals(animation)) {
            stateTime = 0f;
            this.currentAnimation = animation;
        }
    }
}

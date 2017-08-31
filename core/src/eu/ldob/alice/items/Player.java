package eu.ldob.alice.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import eu.ldob.alice.*;
import eu.ldob.alice.mode.Benefits;


public class Player {

    private static final float SCALE = 0.4f;

    private Benefits benefits;

    private Vector2 position;
    private Viewport viewport;

    private Texture textureMarioRight;
    private Sprite spriteMarioRight;
    private Texture textureMarioLeft;
    private Sprite spriteMarioLeft;

    private Sprite sprite;

    public Player(Viewport viewport, Benefits benefits) {
        this.viewport = viewport;
        this.benefits = benefits;

        this.textureMarioRight = new Texture(Gdx.files.internal("player/mario_right.png"));
        this.spriteMarioRight = new Sprite(textureMarioRight, 0, 0, Constants.PLAYER_SIZE, Constants.PLAYER_SIZE);
        this.spriteMarioRight.setScale(SCALE);

        this.textureMarioLeft = new Texture(Gdx.files.internal("player/mario_left.png"));
        this.spriteMarioLeft = new Sprite(textureMarioLeft, 0, 0, Constants.PLAYER_SIZE, Constants.PLAYER_SIZE);
        this.spriteMarioLeft.setScale(SCALE);

        init();
    }

    public void init() {
        sprite = this.spriteMarioRight;
        position = new Vector2(viewport.getWorldWidth() / 2, sprite.getHeight() / 2);
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= delta * viewport.getWorldWidth() / 30 * benefits.getPlayerSpeed();
            sprite = spriteMarioLeft;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += delta * viewport.getWorldWidth() / 30 * benefits.getPlayerSpeed();
            sprite = spriteMarioRight;
        }

        float accelerometerInput = -Gdx.input.getAccelerometerY() / (Constants.GRAVITATIONAL_ACCELERATION * Constants.ACCELEROMETER_SENSITIVITY);
        position.x += -delta * accelerometerInput * viewport.getWorldWidth() / 30 * benefits.getPlayerSpeed();

        if(accelerometerInput > 0.1f) {
            sprite = spriteMarioLeft;
        }
        else if(accelerometerInput < -0.1f) {
            sprite = spriteMarioRight;
        }

        ensureInBounds();
    }

    private void ensureInBounds() {
        if (position.x - sprite.getWidth() < 0) {
            position.x = sprite.getWidth();
        }
        if (position.x + sprite.getWidth() > viewport.getWorldWidth()) {
            position.x = viewport.getWorldWidth() - sprite.getWidth();
        }
    }

    public AFood hitFood(FoodList foodList) {
        for(AFood food : foodList) {
            if (food.getPosition().dst(position) < sprite.getHeight() / 2) {
                return food;
            }
        }

        return null;
    }

    public void draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);

        batch.begin();
        sprite.draw(batch);
        batch.end();
    }
}
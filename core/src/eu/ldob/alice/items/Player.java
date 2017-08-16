package eu.ldob.alice.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import eu.ldob.alice.*;


public class Player {

    private Vector2 position;

    private Viewport viewport;

    private eu.ldob.alice.Benefits benefits;

    public Player(Viewport viewport, eu.ldob.alice.Benefits benefits) {
        this.viewport = viewport;
        this.benefits = benefits;

        init();
    }

    public void init() {
        position = new Vector2(viewport.getWorldWidth() / 2, Constants.PLAYER_HEAD_HEIGHT);
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= delta * benefits.getPlayerSpeed();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += delta * benefits.getPlayerSpeed();
        }

        float accelerometerInput = -Gdx.input.getAccelerometerY() / (Constants.GRAVITATIONAL_ACCELERATION * Constants.ACCELEROMETER_SENSITIVITY);

        position.x += -delta * accelerometerInput * benefits.getPlayerSpeed();

        ensureInBounds();
    }

    private void ensureInBounds() {
        if (position.x - Constants.PLAYER_HEAD_RADIUS < 0) {
            position.x = Constants.PLAYER_HEAD_RADIUS;
        }
        if (position.x + Constants.PLAYER_HEAD_RADIUS > viewport.getWorldWidth()) {
            position.x = viewport.getWorldWidth() - Constants.PLAYER_HEAD_RADIUS;
        }
    }

    public AFood hitFood(FoodList foodList) {
        for(AFood food : foodList) {
            if (food.getPosition().dst(position) < Constants.PLAYER_HEAD_RADIUS) {
                return food;
            }
        }

        return null;
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Constants.PLAYER_COLOR);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.circle(position.x, position.y, Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_HEAD_SEGMENTS);

        Vector2 torsoTop = new Vector2(position.x, position.y - Constants.PLAYER_HEAD_RADIUS);
        Vector2 torsoBottom = new Vector2(torsoTop.x, torsoTop.y - 2 * Constants.PLAYER_HEAD_RADIUS);

        renderer.rectLine(torsoTop, torsoBottom, Constants.PLAYER_LIMB_WIDTH);

        renderer.rectLine(
                torsoTop.x, torsoTop.y,
                torsoTop.x + Constants.PLAYER_HEAD_RADIUS, torsoTop.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH);
        renderer.rectLine(
                torsoTop.x, torsoTop.y,
                torsoTop.x - Constants.PLAYER_HEAD_RADIUS, torsoTop.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH);

        renderer.rectLine(
                torsoBottom.x, torsoBottom.y,
                torsoBottom.x + Constants.PLAYER_HEAD_RADIUS, torsoBottom.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH);

        renderer.rectLine(
                torsoBottom.x, torsoBottom.y,
                torsoBottom.x - Constants.PLAYER_HEAD_RADIUS, torsoBottom.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH);
    }
}
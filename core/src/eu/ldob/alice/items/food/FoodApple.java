package eu.ldob.alice.items.food;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import eu.ldob.alice.items.AFood;


public class FoodApple extends AFood {

    private static final float RADIUS = 0.3f;

    private static final int CALORIC_VALUE = 60;
    private static final int CARBS_VALUE = 0;
    private static final int FAT_VALUE = 0;
    private static final int PROTEIN_VALUE = 0;
    private static final int VIT_A_VALUE = 20;
    private static final int VIT_C_VALUE = 0;
    private static final int CALCIUM_VALUE = 0;
    private static final int IRON_VALUE = 0;


    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    public FoodApple(Vector2 position, Vector2 acceleration) {
        this.position = position;
        this.acceleration = acceleration;
        this.velocity = new Vector2();
    }

    @Override
    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.GREEN);
        renderer.circle(position.x, position.y, RADIUS, 16);
    }

    @Override
    protected Vector2 getPosition() {
        return position;
    }

    @Override
    protected Vector2 getVelocity() {
        return velocity;
    }

    @Override
    protected Vector2 getAcceleration() {
        return acceleration;
    }

    @Override
    protected float getRadius() {
        return RADIUS;
    }

    @Override
    public int getCaloricValue() {
        return CALORIC_VALUE;
    }

    @Override
    public int getCarbs() {
        return CARBS_VALUE;
    }

    @Override
    public int getFat() {
        return FAT_VALUE;
    }

    @Override
    public int getProteins() {
        return PROTEIN_VALUE;
    }

    @Override
    public int getVitaminA() {
        return VIT_A_VALUE;
    }

    @Override
    public int getVitaminC() {
        return VIT_C_VALUE;
    }

    @Override
    public int getCalcium() {
        return CALCIUM_VALUE;
    }

    @Override
    public int getIron() {
        return IRON_VALUE;
    }
}

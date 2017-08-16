package eu.ldob.alice.items.food;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import eu.ldob.alice.items.AFood;


public class FoodSpaghetti extends AFood {

    private final String NAME = "Spaghetti";

    private static final int CALORIC_VALUE = 400;
    private static final int CARBS_VALUE = 0;
    private static final int FAT_VALUE = 0;
    private static final int PROTEIN_VALUE = 0;
    private static final int VIT_A_VALUE = 10;
    private static final int VIT_C_VALUE = 0;
    private static final int CALCIUM_VALUE = 0;
    private static final int IRON_VALUE = 0;

    private static final float RADIUS = 0.3f;

    private NutritionFacts nutritionFacts;

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    public FoodSpaghetti(Vector2 position, Vector2 acceleration) {
        this.position = position;
        this.acceleration = acceleration;
        this.velocity = new Vector2();

        this.nutritionFacts = new NutritionFacts(CALORIC_VALUE, CARBS_VALUE, FAT_VALUE, PROTEIN_VALUE, VIT_A_VALUE, VIT_C_VALUE, CALCIUM_VALUE, IRON_VALUE);
    }

    @Override
    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.YELLOW);
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
    public String getName() {
        return NAME;
    }

    @Override
    public NutritionFacts getNutritionFacts() {
        return nutritionFacts;
    }
}

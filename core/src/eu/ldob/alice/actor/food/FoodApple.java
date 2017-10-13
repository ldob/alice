package eu.ldob.alice.actor.food;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import eu.ldob.alice.actor.AFood;
import eu.ldob.alice.actor.util.FoodType;
import eu.ldob.alice.actor.util.NutritionFacts;


public class FoodApple extends AFood {

    private final String NAME = "Apfel";

    private static final int CALORIC_VALUE = 65;
    private static final int CARBS_VALUE = 15;
    private static final int FAT_VALUE = 0;
    private static final int PROTEIN_VALUE = 0;
    private static final int VIT_A_VALUE = 10;
    private static final int VIT_C_VALUE = 12;
    private static final int CALCIUM_VALUE = 5;
    private static final int IRON_VALUE = 200;

    private static final float WIDTH = 56;
    private static final float HEIGHT = 56;

    private NutritionFacts nutritionFacts;

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private FoodType foodType;

    private Texture texture;

    public FoodApple(FoodType foodType, Vector2 position, Vector2 acceleration) {
        this.foodType = foodType;
        this.position = position;
        this.acceleration = acceleration;
        this.velocity = new Vector2();

        this.texture = new Texture(Gdx.files.internal("food/apple.png"));

        this.nutritionFacts = new NutritionFacts(CALORIC_VALUE, CARBS_VALUE, FAT_VALUE, PROTEIN_VALUE, VIT_A_VALUE, VIT_C_VALUE, CALCIUM_VALUE, IRON_VALUE);
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
    protected Texture getTexture() {
        return texture;
    }

    @Override
    protected float getWidth() {
        return WIDTH;
    }

    @Override
    protected float getHeight() {
        return HEIGHT;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public FoodType getFoodType() {
        return foodType;
    }

    @Override
    public NutritionFacts getNutritionFacts() {
        return nutritionFacts;
    }
}

package eu.ldob.alice.actor.food;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import eu.ldob.alice.actor.AFood;
import eu.ldob.alice.actor.util.FoodType;
import eu.ldob.alice.actor.util.NutritionFacts;


public class FoodYogurt extends AFood {

    private final String NAME = "Joghurt";

    private static final int CALORIC_VALUE = 160;
    private static final int CARBS_VALUE = 6;
    private static final int FAT_VALUE = 15;
    private static final int PROTEIN_VALUE = 6;
    private static final int VIT_A_VALUE = 10;
    private static final int VIT_C_VALUE = 1;
    private static final int CALCIUM_VALUE = 140;
    private static final int IRON_VALUE = 120;

    private static final float WIDTH = 64;
    private static final float HEIGHT = 64;

    private NutritionFacts nutritionFacts;

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private FoodType foodType;

    private Texture texture;

    public FoodYogurt(FoodType foodType, Vector2 position, Vector2 acceleration) {
        this.foodType = foodType;
        this.position = position;
        this.acceleration = acceleration;
        this.velocity = new Vector2();

        this.texture = new Texture(Gdx.files.internal("food/yogurt.png"));

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

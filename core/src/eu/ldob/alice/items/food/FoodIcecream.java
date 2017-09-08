package eu.ldob.alice.items.food;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import eu.ldob.alice.items.AFood;


public class FoodIcecream extends AFood {

    private final String NAME = "Eis";

    private static final int CALORIC_VALUE = 236;
    private static final int CARBS_VALUE = 26;
    private static final int FAT_VALUE = 14;
    private static final int PROTEIN_VALUE = 2;
    private static final int VIT_A_VALUE = 0;
    private static final int VIT_C_VALUE = 0;
    private static final int CALCIUM_VALUE = 0;
    private static final int IRON_VALUE = 0;

    private static final float SCALE = 0.7f;

    private NutritionFacts nutritionFacts;

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private Texture texture;
    private Sprite sprite;

    public FoodIcecream(Vector2 position, Vector2 acceleration) {
        this.position = position;
        this.acceleration = acceleration;
        this.velocity = new Vector2();

        this.texture = new Texture(Gdx.files.internal("food/icecream.png"));
        this.sprite = new Sprite(texture, 0, 0, 128, 128);
        this.sprite.setScale(SCALE);
        this.sprite.setOriginCenter();

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
    protected Sprite getSprite() {
        return sprite;
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

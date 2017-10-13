package eu.ldob.alice.items.factory.food;


import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

import eu.ldob.alice.items.AFood;
import eu.ldob.alice.items.IFoodFactory;
import eu.ldob.alice.items.food.FoodBread;
import eu.ldob.alice.items.util.FoodFactoryUtil;
import eu.ldob.alice.items.util.FoodFactoryUtil.Acceleration;
import eu.ldob.alice.items.util.FoodType;
import eu.ldob.alice.evaluation.Mode;

public class FoodFactoryBread implements IFoodFactory {

    private static final FoodType FOOD_TYPE = FoodType.NEUTRAL;

    private float spawnRate;
    private Map<Acceleration,Vector2> accelerations = new HashMap<Acceleration, Vector2>();

    public FoodFactoryBread(float spawnRate) {
        this.spawnRate = spawnRate;

        accelerations.put(Acceleration.LOW, new Vector2(0, -1.6f));
        accelerations.put(Acceleration.LOW_LEFT, new Vector2(-0.3f, -1.6f));
        accelerations.put(Acceleration.LOW_RIGHT, new Vector2(0.3f, -1.6f));
        accelerations.put(Acceleration.HIGH, new Vector2(0, -4f));
        accelerations.put(Acceleration.HIGH_LEFT, new Vector2(-1.0f, -6.5f));
        accelerations.put(Acceleration.HIGH_RIGHT, new Vector2(1.0f, -6.5f));
    }

    @Override
    public AFood generate(Mode mode, Vector2 position) {
        return new FoodBread(FOOD_TYPE, position, FoodFactoryUtil.getAcceleration(mode, accelerations));
    }

    @Override
    public float getSpawnRate() {
        return spawnRate;
    }

    @Override
    public FoodType getFoodType() {
        return FOOD_TYPE;
    }
}

package eu.ldob.alice.items.factory;


import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

import eu.ldob.alice.items.*;
import eu.ldob.alice.mode.Benefits;
import eu.ldob.alice.items.factory.FoodFactoryUtil.Acceleration;
import eu.ldob.alice.items.food.FoodYogurt;
import eu.ldob.alice.items.food.FoodType;

public class FoodFactoryYogurt implements eu.ldob.alice.items.IFoodFactory {

    private static final FoodType FOOD_TYPE = FoodType.NEUTRAL;

    private float spawnRate;
    private Map<Acceleration,Vector2> accelerations = new HashMap<Acceleration, Vector2>();

    public FoodFactoryYogurt(float spawnRate) {
        this.spawnRate = spawnRate;

        accelerations.put(Acceleration.LOW, new Vector2(0, -2f));
        accelerations.put(Acceleration.LOW_LEFT, new Vector2(-0.2f, -2f));
        accelerations.put(Acceleration.LOW_RIGHT, new Vector2(0.2f, -2f));
        accelerations.put(Acceleration.HIGH, new Vector2(0, -3f));
        accelerations.put(Acceleration.HIGH_LEFT, new Vector2(-0.5f, -3f));
        accelerations.put(Acceleration.HIGH_RIGHT, new Vector2(0.5f, -3f));
    }

    @Override
    public AFood generate(Benefits benefits, Vector2 position) {
        return new FoodYogurt(FOOD_TYPE, position, FoodFactoryUtil.getAcceleration(benefits, accelerations));
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

package eu.ldob.alice.items.factory;


import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

import eu.ldob.alice.items.*;
import eu.ldob.alice.mode.Benefits;
import eu.ldob.alice.items.food.FoodApple;

import eu.ldob.alice.items.factory.FoodFactoryUtil.Acceleration;
import eu.ldob.alice.items.food.FoodType;

public class FoodFactoryApple implements eu.ldob.alice.items.IFoodFactory {

    private static final FoodType FOOD_TYPE = FoodType.HEALTHY;

    private float spawnRate;
    private Map<Acceleration,Vector2> accelerations = new HashMap<Acceleration, Vector2>();

    public FoodFactoryApple(float spawnRate) {
        this.spawnRate = spawnRate;

        accelerations.put(Acceleration.LOW, new Vector2(0, -0.6f));
        accelerations.put(Acceleration.LOW_LEFT, new Vector2(-0.1f, -0.6f));
        accelerations.put(Acceleration.LOW_RIGHT, new Vector2(0.1f, -0.6f));
        accelerations.put(Acceleration.HIGH, new Vector2(0, -3f));
        accelerations.put(Acceleration.HIGH_LEFT, new Vector2(-0.5f, -2.5f));
        accelerations.put(Acceleration.HIGH_RIGHT, new Vector2(0.5f, -2.5f));
    }

    @Override
    public AFood generate(Benefits benefits, Vector2 position) {
        return new FoodApple(FOOD_TYPE, position, FoodFactoryUtil.getAcceleration(benefits, accelerations));
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

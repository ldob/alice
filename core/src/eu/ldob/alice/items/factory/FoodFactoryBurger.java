package eu.ldob.alice.items.factory;


import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

import eu.ldob.alice.items.*;
import eu.ldob.alice.mode.Benefits;
import eu.ldob.alice.items.factory.FoodFactoryUtil.Acceleration;
import eu.ldob.alice.items.food.FoodBurger;
import eu.ldob.alice.items.food.FoodType;

public class FoodFactoryBurger implements eu.ldob.alice.items.IFoodFactory {

    private float spawnRate;
    private Map<Acceleration,Vector2> accelerations = new HashMap<Acceleration, Vector2>();

    public FoodFactoryBurger(float spawnRate) {
        this.spawnRate = spawnRate;

        accelerations.put(Acceleration.LOW, new Vector2(0, -2.6f));
        accelerations.put(Acceleration.LOW_LEFT, new Vector2(-0.4f, -2.6f));
        accelerations.put(Acceleration.LOW_RIGHT, new Vector2(0.4f, -2.6f));
        accelerations.put(Acceleration.HIGH, new Vector2(0, -5f));
        accelerations.put(Acceleration.HIGH_LEFT, new Vector2(-1.4f, -8.5f));
        accelerations.put(Acceleration.HIGH_RIGHT, new Vector2(1.4f, -8.5f));
    }

    @Override
    public AFood generate(Benefits benefits, Vector2 position) {
        return new FoodBurger(position, FoodFactoryUtil.getAcceleration(benefits, accelerations));
    }

    @Override
    public float getSpawnRate() {
        return spawnRate;
    }

    @Override
    public FoodType getFoodType() {
        return FoodType.JUNK;
    }
}

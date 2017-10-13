package eu.ldob.alice.actor.factory.food;


import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

import eu.ldob.alice.actor.AFood;
import eu.ldob.alice.actor.factory.IFoodFactory;
import eu.ldob.alice.actor.food.FoodCheese;
import eu.ldob.alice.actor.util.FoodFactoryUtil;
import eu.ldob.alice.actor.util.FoodFactoryUtil.Acceleration;
import eu.ldob.alice.actor.util.FoodType;
import eu.ldob.alice.evaluation.Mode;

public class FoodFactoryCheese implements IFoodFactory {

    private static final FoodType FOOD_TYPE = FoodType.NEUTRAL;

    private float spawnRate;
    private Map<Acceleration,Vector2> accelerations = new HashMap<Acceleration, Vector2>();

    public FoodFactoryCheese(float spawnRate) {
        this.spawnRate = spawnRate;

        accelerations.put(Acceleration.LOW, new Vector2(0, -0.9f));
        accelerations.put(Acceleration.LOW_LEFT, new Vector2(-1.4f, -0.9f));
        accelerations.put(Acceleration.LOW_RIGHT, new Vector2(1.4f, -0.9f));
        accelerations.put(Acceleration.HIGH, new Vector2(0, -3.3f));
        accelerations.put(Acceleration.HIGH_LEFT, new Vector2(-2.1f, -3f));
        accelerations.put(Acceleration.HIGH_RIGHT, new Vector2(2.1f, -3f));
    }

    @Override
    public AFood generate(Mode mode, Vector2 position) {
        return new FoodCheese(FOOD_TYPE, position, FoodFactoryUtil.getAcceleration(mode, accelerations));
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

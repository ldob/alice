package eu.ldob.alice.items.factory;


import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

import eu.ldob.alice.Level;
import eu.ldob.alice.items.AFood;
import eu.ldob.alice.items.food.FoodApple;

import eu.ldob.alice.items.factory.FoodFactoryUtil.Acceleration;

public class FoodFactoryApple implements IFoodFactory {

    private Map<Acceleration,Vector2> accelerations = new HashMap<Acceleration, Vector2>();

    public FoodFactoryApple() {
        accelerations.put(Acceleration.LOW, new Vector2(0, -0.3f));
        accelerations.put(Acceleration.MEDIUM, new Vector2(0, -1.0f));
        accelerations.put(Acceleration.HIGH, new Vector2(0, -1.5f));
        accelerations.put(Acceleration.HIGH_LEFT, new Vector2(-0.2f, -1.5f));
        accelerations.put(Acceleration.HIGH_RIGHT, new Vector2(0.2f, -1.5f));
    }

    @Override
    public AFood generate(Level level, Vector2 position) {
        return new FoodApple(position, FoodFactoryUtil.getAcceleration(level, accelerations));
    }

    @Override
    public float getSpawnRate() {
        return 0.5f;
    }
}

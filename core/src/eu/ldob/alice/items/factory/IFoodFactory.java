package eu.ldob.alice.items.factory;

import com.badlogic.gdx.math.Vector2;

import eu.ldob.alice.Level;
import eu.ldob.alice.items.AFood;
import eu.ldob.alice.items.food.FoodType;


public interface IFoodFactory {

    AFood generate(Level level, Vector2 position);

    float getSpawnRate();
    FoodType getFoodType();
}

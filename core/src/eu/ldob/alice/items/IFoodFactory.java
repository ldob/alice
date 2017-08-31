package eu.ldob.alice.items;

import com.badlogic.gdx.math.Vector2;

import eu.ldob.alice.mode.Benefits;
import eu.ldob.alice.items.AFood;
import eu.ldob.alice.items.food.FoodType;


public interface IFoodFactory {

    AFood generate(Benefits benefits, Vector2 position);

    float getSpawnRate();
    FoodType getFoodType();
}

package eu.ldob.alice.items;

import com.badlogic.gdx.math.Vector2;

import eu.ldob.alice.items.util.FoodType;
import eu.ldob.alice.evaluation.Mode;


public interface IFoodFactory {

    AFood generate(Mode mode, Vector2 position);

    float getSpawnRate();
    FoodType getFoodType();
}

package eu.ldob.alice.items.factory;

import com.badlogic.gdx.math.Vector2;

import eu.ldob.alice.Level;
import eu.ldob.alice.items.AFood;


public interface IFoodFactory {

    AFood generate(Level level, Vector2 position);

    float getSpawnRate();
}

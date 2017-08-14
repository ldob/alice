package eu.ldob.alice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.ldob.alice.items.factory.FoodFactoryApple;
import eu.ldob.alice.items.factory.FoodFactoryBurger;
import eu.ldob.alice.items.factory.FoodFactorySpaghetti;
import eu.ldob.alice.items.factory.IFoodFactory;

import static eu.ldob.alice.Constants.*;


public enum Level {

    EASY(EASY_HEALTHY_SPAWNS_PER_SECOND, EASY_JUNK_SPAWNS_PER_SECOND, new IFoodFactory[]{new FoodFactoryApple(), new FoodFactorySpaghetti()}),
    MEDIUM(MEDIUM_HEALTHY_SPAWNS_PER_SECOND, MEDIUM_JUNK_SPAWNS_PER_SECOND, new IFoodFactory[]{new FoodFactoryApple(), new FoodFactorySpaghetti(), new FoodFactoryBurger()}),
    HARD(HARD_HEALTHY_SPAWNS_PER_SECOND, HARD_JUNK_SPAWNS_PER_SECOND, new IFoodFactory[]{new FoodFactoryApple(), new FoodFactorySpaghetti(), new FoodFactoryBurger()});

    private float spawnRateHealthy;
    private float spawnRateJunk;
    private IFoodFactory[] foodFactories;

    Level(float spawnRateHealthy, float spawnRateJunk, IFoodFactory[] foodFactories) {
        this.spawnRateHealthy = spawnRateHealthy;
        this.spawnRateJunk = spawnRateJunk;
        this.foodFactories = foodFactories;
    }

    public IFoodFactory[] getFoodFactories() {
        return foodFactories;
    }

}

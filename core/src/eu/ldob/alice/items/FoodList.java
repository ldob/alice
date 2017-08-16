package eu.ldob.alice.items;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Iterator;

import eu.ldob.alice.*;
import eu.ldob.alice.items.factory.IFoodFactory;
import eu.ldob.alice.items.food.FoodType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FoodList implements Iterable<AFood> {

    private Level level;
    private eu.ldob.alice.Benefits benefits;

    private DelayedRemovalArray<AFood> foodList;

    private Viewport viewport;

    public FoodList(Viewport viewport, Level level, eu.ldob.alice.Benefits benefits) {
        this.viewport = viewport;
        this.level = level;
        this.benefits = benefits;

        init();
    }

    public void init() {
        foodList = new DelayedRemovalArray<AFood>(false, Constants.INITIAL_FOOD_ARRAY_CAPACITY);
    }

    public void removeValue(AFood value) {
        foodList.removeValue(value, true);
    }

    public void update(float delta) {

        // add new foods
        for(IFoodFactory foodFactory : level.getFoodFactories()) {

            float spawnRate;
            if(foodFactory.getFoodType() == FoodType.HEALTHY) {
                spawnRate = foodFactory.getSpawnRate() * benefits.getHealthySpawnRateFactor();
            }
            else if(foodFactory.getFoodType() == FoodType.JUNK) {

                spawnRate = foodFactory.getSpawnRate() * benefits.getJunkSpawnRateFactor();
            }
            else {
                spawnRate = foodFactory.getSpawnRate();
            }

            if (MathUtils.random() < delta * spawnRate) {
                foodList.add(foodFactory.generate(level, new Vector2(viewport.getWorldWidth() * 0.1f + MathUtils.random() * viewport.getWorldWidth() * 0.8f, viewport.getWorldHeight())));
            }
        }

        // update each food
        for (AFood food : foodList) {
            food.update(delta);
        }

        // remove dropped food
        foodList.begin();
        for(int i = 0; i < foodList.size; i++) {
            if(foodList.get(i).removeFromScreen()) {
                foodList.removeIndex(i);
            }
        }
        foodList.end();
    }

    public void render(ShapeRenderer renderer) {
        for(int i = 0; i < foodList.size; i++) {
            foodList.get(i).render(renderer);
        }
    }

    @Override
    public Iterator<AFood> iterator() {
        return new FoodListIterator();
    }

    private class FoodListIterator implements Iterator<AFood> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < foodList.size;
        }

        @Override
        public AFood next() {
            return foodList.get(index++);
        }

        @Override
        public void remove() {
            throw new NotImplementedException();
        }
    }
}
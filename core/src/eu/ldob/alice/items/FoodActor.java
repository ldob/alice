package eu.ldob.alice.items;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.DelayedRemovalArray;

import java.util.Iterator;

import eu.ldob.alice.Constants;
import eu.ldob.alice.items.util.FoodType;
import eu.ldob.alice.evaluation.Benefits;
import eu.ldob.alice.evaluation.Mode;

public class FoodActor extends Actor implements Iterable<AFood> {

    private Mode mode;
    private Benefits benefits;

    private DelayedRemovalArray<AFood> foodList;

    public FoodActor(Mode mode, Benefits benefits) {
        this.mode = mode;
        this.benefits = benefits;

        this.foodList = new DelayedRemovalArray<AFood>(false, Constants.INITIAL_FOOD_ARRAY_CAPACITY);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for(int i = 0; i < foodList.size; i++) {
            foodList.get(i).draw(batch);
        }
    }

    public void update(float delta) {
        // add new foods
        for(IFoodFactory foodFactory : mode.getFoodFactories()) {

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
                foodList.add(foodFactory.generate(mode, new Vector2(Constants.WORLD_WIDTH * 0.1f + MathUtils.random() * Constants.WORLD_WIDTH * 0.8f, Constants.WORLD_HEIGHT)));
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

    public void removeFood(AFood food) {
        foodList.removeValue(food, true);
    }

    @Override
    public Iterator<AFood> iterator() {
        return new FoodActor.FoodListIterator();
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
            throw new IllegalStateException();
        }
    }
}

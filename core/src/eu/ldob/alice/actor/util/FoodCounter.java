package eu.ldob.alice.actor.util;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import eu.ldob.alice.actor.AFood;

public class FoodCounter {

    private Map<AFood,Integer> map = new HashMap<AFood, Integer>();

    public void add(AFood food) {
        if(map.containsKey(food)) {
            map.put(food, map.get(food) + 1);
        }
        else {
            map.put(food, 1);
        }
    }

    public Set<AFood> getFoodList() {
        return map.keySet();
    }

    public int getFoodCount(AFood food) {
        return map.get(food);
    }

    public NutritionFacts getTotalNutritionFacts() {

        NutritionFacts nutritionFacts = new NutritionFacts();

        for(AFood food : map.keySet()) {
            nutritionFacts.add(food.getNutritionFacts(), map.get(food));
        }

        return nutritionFacts;
    }

}

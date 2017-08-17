package eu.ldob.alice.mode;

import eu.ldob.alice.Constants;
import eu.ldob.alice.items.factory.FoodFactoryApple;
import eu.ldob.alice.items.factory.FoodFactoryBurger;
import eu.ldob.alice.items.factory.FoodFactorySpaghetti;
import eu.ldob.alice.items.factory.IFoodFactory;


public enum Mode {

    TIME(Constants.MODE_TIME_LABEL, new IFoodFactory[]{new FoodFactoryApple(0.5f), new FoodFactorySpaghetti(0.6f), new FoodFactoryBurger(0.2f)}, new EvaluationTime()),
    COLLECT_VITAMINS(Constants.MODE_VITAMINS_LABEL, new IFoodFactory[]{new FoodFactoryApple(0.4f), new FoodFactorySpaghetti(0.8f)}, null),
    AVOID_FAT(Constants.MODE_FAT_LABEL, new IFoodFactory[]{new FoodFactoryApple(0.4f), new FoodFactorySpaghetti(0.5f), new FoodFactoryBurger(0.3f)}, null);

    private String name;
    private IFoodFactory[] foodFactories;
    private IEvaluation evaluation;

    Mode(String name, IFoodFactory[] foodFactories, IEvaluation evaluation) {
        this.name = name;
        this.foodFactories = foodFactories;
        this.evaluation = evaluation;
    }

    public String getName() {
        return name;
    }

    public IFoodFactory[] getFoodFactories() {
        return foodFactories;
    }

    public IEvaluation getEvaluation() {
        return evaluation;
    }

}

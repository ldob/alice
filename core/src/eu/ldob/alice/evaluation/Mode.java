package eu.ldob.alice.evaluation;

import eu.ldob.alice.Constants;
import eu.ldob.alice.actor.factory.food.FoodFactoryApple;
import eu.ldob.alice.actor.factory.food.FoodFactoryBanana;
import eu.ldob.alice.actor.factory.food.FoodFactoryBread;
import eu.ldob.alice.actor.factory.food.FoodFactoryBurger;
import eu.ldob.alice.actor.factory.food.FoodFactoryCheese;
import eu.ldob.alice.actor.factory.food.FoodFactoryIcecream;
import eu.ldob.alice.actor.factory.food.FoodFactoryYogurt;
import eu.ldob.alice.actor.factory.IFoodFactory;


public enum Mode {

    BALANCED (
            Constants.MODE_BALANCED_LABEL,
            new IFoodFactory[] {
                    new FoodFactoryApple(0.2f),
                    new FoodFactoryBanana(0.2f),
                    new FoodFactoryBread(0.2f),
                    new FoodFactoryBurger(0.2f),
                    new FoodFactoryCheese(0.2f),
                    new FoodFactoryIcecream(0.2f),
                    new FoodFactoryYogurt(0.2f)
            },
            new EvaluationBalanced()
    ),

    COLLECT_VITAMINS (
            Constants.MODE_VITAMINS_LABEL,
            new IFoodFactory[] {
                    new FoodFactoryApple(0.4f),
                    new FoodFactoryYogurt(0.3f),
                    new FoodFactoryIcecream(0.2f),
                    new FoodFactoryBurger(0.3f)},
            new EvaluationCollectVitamins()
    ),

    MINERAL_HUNTER (
            Constants.MODE_MINERAL_HUNTER_LABEL,
            new IFoodFactory[] {
                    new FoodFactoryApple(0.4f),
                    new FoodFactoryYogurt(0.3f),
                    new FoodFactoryIcecream(0.2f),
                    new FoodFactoryBurger(0.3f)},
            new EvaluationMineralHunter()
    ),

    AVOID_FAT (
            Constants.MODE_FAT_LABEL,
            new IFoodFactory[] {
                    new FoodFactoryApple(0.3f),
                    new FoodFactoryBanana(0.3f),
                    new FoodFactoryCheese(01.2f),
                    new FoodFactoryIcecream(1.2f),
                    new FoodFactoryBurger(1.8f)},
            new EvaluationAvoidFat()
    );


    private String name;
    private IFoodFactory[] foodFactories;
    private AEvaluation evaluation;

    Mode(String name, IFoodFactory[] foodFactories, AEvaluation evaluation) {
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

    public AEvaluation getEvaluation() {
        return evaluation;
    }

    public static Mode getByEvaluation(AEvaluation evaluation) {
        for(Mode m : Mode.values()) {
            if(evaluation.equals(m.getEvaluation())) {
                return m;
            }
        }

        return null;
    }
}

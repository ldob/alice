package eu.ldob.alice.evaluation;


import java.util.ArrayList;
import java.util.List;

import eu.ldob.alice.actor.util.FoodCounter;
import eu.ldob.alice.actor.util.NutritionFacts;

import static eu.ldob.alice.evaluation.NutritionType.CALORIC_VALUE;
import static eu.ldob.alice.evaluation.NutritionType.CARBS;
import static eu.ldob.alice.evaluation.NutritionType.FAT;
import static eu.ldob.alice.evaluation.NutritionType.PROTEINS;

public class EvaluationBalanced extends AEvaluation {

    private static final int CALORIC_VALUE_WEIGHT = 1500;
    private static final int CARBS_WEIGHT = 1200;
    private static final int FAT_WEIGHT = 1200;
    private static final int PROTEINS_WEIGHT = 1100;
    private static final int VITAMIN_A_WEIGHT = 0;
    private static final int VITAMIN_C_WEIGHT = 0;
    private static final int CALCIUM_WEIGHT = 0;
    private static final int IRON_WEIGHT = 0;

    @Override
    protected NutritionType[] getNutritionTypes() {
        return new NutritionType[]{ CALORIC_VALUE, CARBS, FAT, PROTEINS };
    }

    @Override
    public int getCaloricValueWeight() {
        return CALORIC_VALUE_WEIGHT;
    }

    @Override
    public int getCarbsWeight() {
        return CARBS_WEIGHT;
    }

    @Override
    public int getFatWeight() {
        return FAT_WEIGHT;
    }

    @Override
    public int getProteinsWeight() {
        return PROTEINS_WEIGHT;
    }

    @Override
    public int getVitaminAWeight() {
        return VITAMIN_A_WEIGHT;
    }

    @Override
    public int getVitaminCWeight() {
        return VITAMIN_C_WEIGHT;
    }

    @Override
    public int getCalciumWeight() {
        return CALCIUM_WEIGHT;
    }

    @Override
    public int getIronWeight() {
        return IRON_WEIGHT;
    }

    @Override
    public boolean isGameOver(Benefits benefits, float time, FoodCounter foodCounter) {

        if(time > benefits.getMaxmumGameTime()) {
            return true;
        }

        NutritionFacts totalNutritionFacts = foodCounter.getTotalNutritionFacts();

        return (
                totalNutritionFacts.getCaloricValue() > benefits.getCaloricValueTarget() * 1.2f
                    ||
                totalNutritionFacts.getFat() > benefits.getFatTarget() * 1.2f
        );
    }

    @Override
    public List<GameOverReason> getGameOverReasons(eu.ldob.alice.evaluation.Benefits benefits, float time, FoodCounter foodCounter) {
        List<GameOverReason> reasons = new ArrayList<GameOverReason>();

        NutritionFacts totalNutritionFacts = foodCounter.getTotalNutritionFacts();

        if(time > benefits.getMaxmumGameTime()) {
            reasons.add(GameOverReason.TIME);
        }

        if(totalNutritionFacts.getCaloricValue() > benefits.getCaloricValueTarget() * 1.2f) {
            reasons.add(GameOverReason.CALORIC_VALUE);
        }

        if(totalNutritionFacts.getFat() > benefits.getFatTarget() * 1.2f) {
            reasons.add(GameOverReason.FAT);
        }

        return reasons;
    }
}
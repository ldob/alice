package eu.ldob.alice.evaluation;


import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;

import eu.ldob.alice.actor.util.FoodCounter;

import static eu.ldob.alice.evaluation.NutritionType.CALORIC_VALUE;
import static eu.ldob.alice.evaluation.NutritionType.FAT;
import static eu.ldob.alice.evaluation.NutritionType.IRON;
import static eu.ldob.alice.evaluation.NutritionType.VITAMIN_C;

public class EvaluationAvoidFat extends AEvaluation {

    private static final int CALORIC_VALUE_WEIGHT = 500;
    private static final int CARBS_WEIGHT = 0;
    private static final int FAT_WEIGHT = 3500;
    private static final int PROTEINS_WEIGHT = 0;
    private static final int VITAMIN_A_WEIGHT = 500;
    private static final int VITAMIN_C_WEIGHT = 0;
    private static final int CALCIUM_WEIGHT = 0;
    private static final int IRON_WEIGHT = 500;

    @Override
    protected NutritionType[] getNutritionTypes() {
        return new NutritionType[]{ CALORIC_VALUE, FAT, VITAMIN_C, IRON };
    }

    @Override
    public int getFatScore(int fat, Benefits benefits){
        return MathUtils.round(Math.max(1, 1.0f / (fat - benefits.getFatTarget())));
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

        return false;
    }

    @Override
    public List<GameOverReason> getGameOverReasons(Benefits benefits, float time, FoodCounter foodCounter) {
        List<GameOverReason> reasons = new ArrayList<GameOverReason>();

        if(time > benefits.getMaxmumGameTime()) {
            reasons.add(GameOverReason.TIME);
        }

        return reasons;
    }
}
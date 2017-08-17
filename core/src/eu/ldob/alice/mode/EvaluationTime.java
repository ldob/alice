package eu.ldob.alice.mode;


import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.StringBuilder;

import eu.ldob.alice.Benefits;
import eu.ldob.alice.Constants;
import eu.ldob.alice.items.FoodCounter;
import eu.ldob.alice.items.food.NutritionFacts;

public class EvaluationTime implements IEvaluation {

    @Override
    public boolean isGameOver(Benefits benefits, float time, FoodCounter foodCounter) {

        if(time > TIME_TARGET) {
            return true;
        }

        NutritionFacts totalNutritionFacts = foodCounter.getTotalNutritionFacts();

        return (
            totalNutritionFacts.getCaloricValue() > CALORIC_VALUE_TARGET * benefits.getCaloricValueFactor() * 1.2f
            ||
            totalNutritionFacts.getFat() > FAT_TARGET * 1.2f
        );
    }

    @Override
    public int getTotalPoints(Benefits benefits, float time, FoodCounter foodCounter) {
        return 0;
    }

    @Override
    public String getHudText(Benefits benefits, float time, FoodCounter foodCounter) {
        NutritionFacts totalNutritionFacts = foodCounter.getTotalNutritionFacts();

        StringBuilder sb = new StringBuilder();

        sb.append((time > TIME_TARGET - 5) ? Constants.ERROR_HEX_COLOR : ((time > TIME_TARGET - 15) ? Constants.WARN_HEX_COLOR : Constants.DEFAULT_HEX_COLOR));
        sb.append(Constants.SCORE_TIME_LABEL + MathUtils.round(time) + " / " + TIME_TARGET + Constants.SCORE_TIME_UNIT + "\n");

        float caloricValue = CALORIC_VALUE_TARGET * benefits.getCaloricValueFactor();
        sb.append((totalNutritionFacts.getCaloricValue() > caloricValue) ? Constants.ERROR_HEX_COLOR : ((totalNutritionFacts.getCaloricValue() > caloricValue * 0.8f) ? Constants.WARN_HEX_COLOR : Constants.DEFAULT_HEX_COLOR));
        sb.append(Constants.SCORE_CALORIC_VALUE_LABEL + totalNutritionFacts.getCaloricValue() + " / " + MathUtils.round(caloricValue) + Constants.SCORE_CALORIC_VALUE_UNIT + "\n");

        sb.append((totalNutritionFacts.getCarbs() < CARBS_TARGET * 0.8f) ? Constants.ERROR_HEX_COLOR : ((totalNutritionFacts.getCarbs() < CARBS_TARGET) ? Constants.WARN_HEX_COLOR : Constants.DEFAULT_HEX_COLOR));
        sb.append(Constants.SCORE_CARBS_LABEL + totalNutritionFacts.getCarbs() + " / " + CARBS_TARGET + Constants.SCORE_CARBS_UNIT + "\n");

        sb.append((totalNutritionFacts.getFat() > FAT_TARGET) ? Constants.ERROR_HEX_COLOR : ((totalNutritionFacts.getFat() > FAT_TARGET * 0.8f) ? Constants.WARN_HEX_COLOR : Constants.DEFAULT_HEX_COLOR));
        sb.append(Constants.SCORE_FAT_LABEL + totalNutritionFacts.getFat() + " / " + FAT_TARGET + Constants.SCORE_FAT_UNIT + "\n");

        sb.append((totalNutritionFacts.getProteins() < PROTEINS_TARGET * 0.8f) ? Constants.ERROR_HEX_COLOR : ((totalNutritionFacts.getProteins() < PROTEINS_TARGET) ? Constants.WARN_HEX_COLOR : Constants.DEFAULT_HEX_COLOR));
        sb.append(Constants.SCORE_PROTEINS_LABEL + totalNutritionFacts.getProteins() + " / " + PROTEINS_TARGET + Constants.SCORE_PROTEINS_UNIT + "\n");

        sb.append((totalNutritionFacts.getVitaminA() < VITAMIN_A_TARGET * 0.8f) ? Constants.ERROR_HEX_COLOR : ((totalNutritionFacts.getVitaminA() < VITAMIN_A_TARGET) ? Constants.WARN_HEX_COLOR : Constants.DEFAULT_HEX_COLOR));
        sb.append(Constants.SCORE_VITAMIN_A_LABEL + totalNutritionFacts.getVitaminA() + " / " + VITAMIN_A_TARGET + Constants.SCORE_VITAMIN_A_UNIT + "\n");

        sb.append((totalNutritionFacts.getVitaminC() < VITAMIN_C_TARGET * 0.8f) ? Constants.ERROR_HEX_COLOR : ((totalNutritionFacts.getVitaminC() < VITAMIN_C_TARGET) ? Constants.WARN_HEX_COLOR : Constants.DEFAULT_HEX_COLOR));
        sb.append(Constants.SCORE_VITAMIN_C_LABEL + totalNutritionFacts.getVitaminC() + " / " + VITAMIN_C_TARGET + Constants.SCORE_VITAMIN_C_UNIT + "\n");

        sb.append((totalNutritionFacts.getCalcium() < CALCIUM_TARGET * 0.8f) ? Constants.ERROR_HEX_COLOR : ((totalNutritionFacts.getCalcium() < CALCIUM_TARGET) ? Constants.WARN_HEX_COLOR : Constants.DEFAULT_HEX_COLOR));
        sb.append(Constants.SCORE_CALCIUM_LABEL + totalNutritionFacts.getCalcium() + " / " + CALCIUM_TARGET + Constants.SCORE_CALCIUM_UNIT + "\n");

        sb.append((totalNutritionFacts.getIron() < IRON_TARGET * 0.8f) ? Constants.ERROR_HEX_COLOR : ((totalNutritionFacts.getIron() < IRON_TARGET) ? Constants.WARN_HEX_COLOR : Constants.DEFAULT_HEX_COLOR));
        sb.append(Constants.SCORE_IRON_LABEL + totalNutritionFacts.getIron() + " / " + IRON_TARGET + Constants.SCORE_IRON_UNIT);

        return sb.toString();
    }

    @Override
    public String getEvaluationText(Benefits benefits, float time, FoodCounter foodCounter) {
        return null;
    }
}

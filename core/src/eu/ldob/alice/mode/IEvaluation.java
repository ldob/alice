package eu.ldob.alice.mode;

import eu.ldob.alice.Benefits;
import eu.ldob.alice.items.FoodCounter;

public interface IEvaluation {

    int TIME_TARGET = 60;
    int CALORIC_VALUE_TARGET = 1880;
    int CARBS_TARGET = 280;
    int FAT_TARGET = 60;
    int PROTEINS_TARGET = 55;
    int VITAMIN_A_TARGET = 900;
    int VITAMIN_C_TARGET = 100;
    int CALCIUM_TARGET = 1000;
    int IRON_TARGET = 3000;

    boolean isGameOver(Benefits benefits, float time, FoodCounter foodCounter);

    int getTotalPoints(Benefits benefits, float time, FoodCounter foodCounter);

    String getHudText(Benefits benefits, float time, FoodCounter foodCounter);

    String getEvaluationText(Benefits benefits, float time, FoodCounter foodCounter);

}

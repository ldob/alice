package eu.ldob.alice.mode;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.List;

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
    List<GameOverReason> getGameOverReasons(Benefits benefits, float time, FoodCounter foodCounter);

    int getTotalScore(float time, FoodCounter foodCounter);
    int getTimeScore(float time);
    int getCaloricValueScore(int caloricValue);
    int getCarbsScore(int carbs);
    int getFatScore(int fat);
    int getProteinsScore(int proteins);
    int getVitaminAScore(int vitaminA);
    int getVitaminCScore(int vitaminC);
    int getCalciumScore(int calcium);
    int getIronScore(int iron);

    String getHudText(Benefits benefits, float time, FoodCounter foodCounter);
    Table getResultTable(Skin skin, Benefits benefits, float time, FoodCounter foodCounter);

    enum GameOverReason {
        TIME, CALORIC_VALUE, FAT
    }

}

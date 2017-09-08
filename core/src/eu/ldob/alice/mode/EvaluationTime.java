package eu.ldob.alice.mode;


import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.StringBuilder;

import java.util.ArrayList;
import java.util.List;

import eu.ldob.alice.Constants;
import eu.ldob.alice.items.AFood;
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
    public List<GameOverReason> getGameOverReasons(Benefits benefits, float time, FoodCounter foodCounter) {
        List<GameOverReason> reasons = new ArrayList<GameOverReason>();

        NutritionFacts totalNutritionFacts = foodCounter.getTotalNutritionFacts();

        if(time > TIME_TARGET) {
            reasons.add(GameOverReason.TIME);
        }

        if(totalNutritionFacts.getCaloricValue() > CALORIC_VALUE_TARGET * benefits.getCaloricValueFactor() * 1.2f) {
            reasons.add(GameOverReason.CALORIC_VALUE);
        }

        if(totalNutritionFacts.getFat() > FAT_TARGET * 1.2f) {
            reasons.add(GameOverReason.FAT);
        }

        return reasons;
    }

    @Override
    public int getTotalScore(float time, FoodCounter foodCounter) {
        NutritionFacts tnf = foodCounter.getTotalNutritionFacts();
        return  this.getTimeScore(time) +
                this.getCaloricValueScore(tnf.getCaloricValue()) +
                this.getCarbsScore(tnf.getCarbs()) +
                this.getFatScore(tnf.getFat()) +
                this.getProteinsScore(tnf.getProteins()) +
                this.getVitaminAScore(tnf.getVitaminA()) +
                this.getVitaminCScore(tnf.getVitaminC()) +
                this.getCalciumScore(tnf.getCalcium()) +
                this.getIronScore(tnf.getIron());
    }

    @Override
    public int getTimeScore(float time) {
        return 0;
    }

    @Override
    public int getCaloricValueScore(int caloricValue) {
        return Math.round(Math.max(0, 1000 - (caloricValue * 0.3f)));
    }

    @Override
    public int getCarbsScore(int carbs) {
        return Math.round(Math.min(carbs * 3, 2500));
    }

    @Override
    public int getFatScore(int fat) {
        return Math.round(fat * (-5f) + (float)Math.pow(fat, 2) * (-0.2f));
    }

    @Override
    public int getProteinsScore(int proteins) {
        return Math.round(proteins * 55.2f);
    }

    @Override
    public int getVitaminAScore(int vitaminA) {
        return 0;
    }

    @Override
    public int getVitaminCScore(int vitaminC) {
        return 0;
    }

    @Override
    public int getCalciumScore(int calcium) {
        return 0;
    }

    @Override
    public int getIronScore(int iron) {
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
    public Table getResultTable(Skin skin, Benefits benefits, float time, FoodCounter foodCounter) {
        Table tbResult = new Table(skin);

        final Label lbCaloricValue = new Label("Brennwert\n[kcal]", skin);
        lbCaloricValue.setAlignment(Align.center);
        final Label lbCarbs = new Label("Kohlenhydrate\n[g]", skin);
        lbCarbs.setAlignment(Align.center);
        final Label lbFat = new Label("Fett\n[g]", skin);
        lbFat.setAlignment(Align.center);
        final Label lbProteins = new Label("Proteine\n[g]", skin);
        lbProteins.setAlignment(Align.center);

        tbResult.add().padRight(10).colspan(3);
        tbResult.add(lbCaloricValue).padRight(10).uniform();
        tbResult.add(lbCarbs).padRight(10).uniform();
        tbResult.add(lbFat).padRight(10).uniform();
        tbResult.add(lbProteins).uniform();
        tbResult.row().padTop(10);

        for(AFood food : foodCounter.getFoodList()) {
            NutritionFacts nf = food.getNutritionFacts();

            tbResult.add(String.valueOf(foodCounter.getFoodCount(food))).right();
            tbResult.add("x").padLeft(5).padRight(5).center();
            tbResult.add(food.getName()).left();
            tbResult.add(String.valueOf(nf.getCaloricValue()));
            tbResult.add(String.valueOf(nf.getCarbs()));
            tbResult.add(String.valueOf(nf.getFat()));
            tbResult.add(String.valueOf(nf.getProteins()));
            tbResult.row();
        }

        tbResult.row().padTop(10);

        NutritionFacts tnf = foodCounter.getTotalNutritionFacts();

        tbResult.add().colspan(2);
        tbResult.add(Constants.TOTAL_LABEL).right();
        tbResult.add(String.valueOf(tnf.getCaloricValue()));
        tbResult.add(String.valueOf(tnf.getCarbs()));
        tbResult.add(String.valueOf(tnf.getFat()));
        tbResult.add(String.valueOf(tnf.getProteins()));


        tbResult.row();
        tbResult.add().colspan(2);
        tbResult.add(Constants.SCORE_LABEL).right();
        tbResult.add(String.valueOf(this.getCaloricValueScore(tnf.getCaloricValue())));
        tbResult.add(String.valueOf(this.getCarbsScore(tnf.getCarbs())));
        tbResult.add(String.valueOf(this.getFatScore(tnf.getFat())));
        tbResult.add(String.valueOf(this.getProteinsScore(tnf.getProteins())));

        return tbResult;
    }
}
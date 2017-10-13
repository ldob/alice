package eu.ldob.alice.evaluation;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.ArrayList;
import java.util.List;

import eu.ldob.alice.Constants;
import eu.ldob.alice.items.util.FoodCounter;
import eu.ldob.alice.items.util.NutritionFacts;

import static eu.ldob.alice.Constants.*;

public class EvaluationBalanced extends AEvaluation {

    private static final int CALORIC_VALUE_WEIGHT = 1000;
    private static final int CARBS_WEIGHT = 1000;
    private static final int FAT_WEIGHT = 1000;
    private static final int PROTEINS_WEIGHT = 1000;
    private static final int VITAMIN_A_WEIGHT = 1000;
    private static final int VITAMIN_C_WEIGHT = 1000;
    private static final int CALCIUM_WEIGHT = 1000;
    private static final int IRON_WEIGHT = 1000;

    private List<eu.ldob.alice.evaluation.HudItem> hudItems;
    private eu.ldob.alice.evaluation.HudItem hiTime;
    private eu.ldob.alice.evaluation.HudItem hiCaloricValue;
    private eu.ldob.alice.evaluation.HudItem hiCarbs;
    private eu.ldob.alice.evaluation.HudItem hiFat;
    private eu.ldob.alice.evaluation.HudItem hiProteins;

    @Override
    public boolean isGameOver(eu.ldob.alice.evaluation.Benefits benefits, float time, FoodCounter foodCounter) {

        if(time > TIME_TARGET) {
            return true;
        }

        NutritionFacts totalNutritionFacts = foodCounter.getTotalNutritionFacts();

        return (
            totalNutritionFacts.getCaloricValue() > benefits.getCaloricValueTarget() * 1.2f
            ||
            totalNutritionFacts.getFat() > FAT_TARGET * 1.2f
        );
    }

    @Override
    public List<GameOverReason> getGameOverReasons(eu.ldob.alice.evaluation.Benefits benefits, float time, FoodCounter foodCounter) {
        List<GameOverReason> reasons = new ArrayList<GameOverReason>();

        NutritionFacts totalNutritionFacts = foodCounter.getTotalNutritionFacts();

        if(time > TIME_TARGET) {
            reasons.add(GameOverReason.TIME);
        }

        if(totalNutritionFacts.getCaloricValue() > benefits.getCaloricValueTarget() * 1.2f) {
            reasons.add(GameOverReason.CALORIC_VALUE);
        }

        if(totalNutritionFacts.getFat() > FAT_TARGET * 1.2f) {
            reasons.add(GameOverReason.FAT);
        }

        return reasons;
    }

    @Override
    public Table getHudTable(Skin skin, eu.ldob.alice.evaluation.Benefits benefits) {

        Table tbHud = new Table(skin);

        hudItems = new ArrayList<eu.ldob.alice.evaluation.HudItem>();

        hiTime = new eu.ldob.alice.evaluation.HudItem(skin, Constants.SCORE_TIME_LABEL, TIME_TARGET, Constants.SCORE_TIME_UNIT, eu.ldob.alice.evaluation.HudItem.Formatting.NEGATIVE);
        hudItems.add(hiTime);

        hiCaloricValue = new eu.ldob.alice.evaluation.HudItem(skin, Constants.SCORE_CALORIC_VALUE_LABEL, benefits.getCaloricValueTarget(), Constants.SCORE_CALORIC_VALUE_UNIT, eu.ldob.alice.evaluation.HudItem.Formatting.NEUTRAL);
        hudItems.add(hiCaloricValue);

        hiCarbs = new eu.ldob.alice.evaluation.HudItem(skin, Constants.SCORE_CARBS_LABEL, Constants.CARBS_TARGET, Constants.SCORE_CARBS_UNIT, eu.ldob.alice.evaluation.HudItem.Formatting.NEUTRAL);
        hudItems.add(hiCarbs);

        hiFat = new eu.ldob.alice.evaluation.HudItem(skin, Constants.SCORE_FAT_LABEL, FAT_TARGET, Constants.SCORE_FAT_UNIT, eu.ldob.alice.evaluation.HudItem.Formatting.NEGATIVE);
        hudItems.add(hiFat);

        hiProteins = new eu.ldob.alice.evaluation.HudItem(skin, Constants.SCORE_PROTEINS_LABEL, Constants.PROTEINS_TARGET, Constants.SCORE_PROTEINS_UNIT, eu.ldob.alice.evaluation.HudItem.Formatting.POSITIVE);
        hudItems.add(hiProteins);

        for(eu.ldob.alice.evaluation.HudItem hudItem : hudItems) {
            tbHud.add(hudItem.lbName).width(250).fillX();
            tbHud.add(hudItem.lbValue).width(60).fillX();
            tbHud.add(hudItem.lbSeparator).width(25).fillX();
            tbHud.add(hudItem.lbTarget).width(60).fillX();
            tbHud.add(hudItem.lbUnit).width(50).fillX();
            tbHud.row();
        }

        tbHud.padRight(30).padTop(15);

        return tbHud;
    }

    @Override
    public void updateHudTable(float time, FoodCounter foodCounter) {

        NutritionFacts totalNutritionFacts = foodCounter.getTotalNutritionFacts();

        hiTime.update(time);
        hiCaloricValue.update(totalNutritionFacts.getCaloricValue());
        hiCarbs.update(totalNutritionFacts.getCarbs());
        hiFat.update(totalNutritionFacts.getFat());
        hiProteins.update(totalNutritionFacts.getProteins());
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
}
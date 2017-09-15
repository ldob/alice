package eu.ldob.alice.mode;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.List;

import eu.ldob.alice.Constants;
import eu.ldob.alice.items.AFood;
import eu.ldob.alice.items.FoodCounter;
import eu.ldob.alice.items.food.NutritionFacts;

public class EvaluationTime implements IEvaluation {

    private List<HudItem> hudItems;
    private HudItem hiTime;
    private HudItem hiCaloricValue;
    private HudItem hiCarbs;
    private HudItem hiFat;
    private HudItem hiProteins;

    private Label.LabelStyle labelStyle;

    public EvaluationTime() {
        BitmapFont font = new BitmapFont();
        labelStyle = new Label.LabelStyle(font, Constants.SCORE_GOOD_COLOR);
    }

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
    public Table getHudTable(Skin skin, Benefits benefits) {

        Table tbHud = new Table(skin);

        hudItems = new ArrayList<HudItem>();

        hiTime = new HudItem(labelStyle, Constants.SCORE_TIME_LABEL, TIME_TARGET, Constants.SCORE_TIME_UNIT, HudItem.Formatting.NEGATIVE);
        hudItems.add(hiTime);

        hiCaloricValue = new HudItem(labelStyle, Constants.SCORE_CALORIC_VALUE_LABEL, CALORIC_VALUE_TARGET * benefits.getCaloricValueFactor(), Constants.SCORE_CALORIC_VALUE_UNIT, HudItem.Formatting.NEUTRAL);
        hudItems.add(hiCaloricValue);

        hiCarbs = new HudItem(labelStyle, Constants.SCORE_CARBS_LABEL, CARBS_TARGET, Constants.SCORE_CARBS_UNIT, HudItem.Formatting.NEUTRAL);
        hudItems.add(hiCarbs);

        hiFat = new HudItem(labelStyle, Constants.SCORE_FAT_LABEL, FAT_TARGET, Constants.SCORE_FAT_UNIT, HudItem.Formatting.NEGATIVE);
        hudItems.add(hiFat);

        hiProteins = new HudItem(labelStyle, Constants.SCORE_PROTEINS_LABEL, PROTEINS_TARGET, Constants.SCORE_PROTEINS_UNIT, HudItem.Formatting.POSITIVE);
        hudItems.add(hiProteins);

        for(HudItem hudItem : hudItems) {
            tbHud.add(hudItem.lbName).width(150).fillX();
            tbHud.add(hudItem.lbValue).width(60).fillX();
            tbHud.add(hudItem.lbSeparator).width(20).fillX();
            tbHud.add(hudItem.lbTarget).width(60).fillX();
            tbHud.add(hudItem.lbUnit).width(30).fillX();
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
package eu.ldob.alice.evaluation;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import eu.ldob.alice.Constants;
import eu.ldob.alice.actor.AFood;
import eu.ldob.alice.actor.util.FoodCounter;
import eu.ldob.alice.actor.util.NutritionFacts;
import eu.ldob.alice.evaluation.formatting.NegativeFormatting;
import eu.ldob.alice.rest.AliceHttpListener;
import eu.ldob.alice.rest.AliceHttpRequest;

public abstract class AEvaluation {



    private List<HudItem> hudItems;
    private HudItem hiTime;

    /**
     * SCORE WEIGHT
     */

    protected abstract int getCaloricValueWeight();
    protected abstract int getCarbsWeight();
    protected abstract int getFatWeight();
    protected abstract int getProteinsWeight();
    protected abstract int getVitaminAWeight();
    protected abstract int getVitaminCWeight();
    protected abstract int getCalciumWeight();
    protected abstract int getIronWeight();

    /**
     * SCORE CALCULATION
     */

    public int getScore(NutritionType type, int value, Benefits benefits) {
        switch(type) {
            case CALORIC_VALUE: return this.getCaloricValueScore(value, benefits);
            case CARBS: return this.getCarbsScore(value, benefits);
            case FAT: return this.getFatScore(value, benefits);
            case PROTEINS: return this.getProteinsScore(value, benefits);
            case VITAMIN_A: return this.getVitaminAScore(value, benefits);
            case VITAMIN_C: return this.getVitaminCScore(value, benefits);
            case CALCIUM: return this.getCalciumScore(value, benefits);
            case IRON: return this.getIronScore(value, benefits);
        }

        throw new RuntimeException("NutritionType not implemented yet");
    }

    public int getCaloricValueScore(int caloricValue, Benefits benefits) {
        double ratio = (double)caloricValue / benefits.getCaloricValueTarget();
        return MathUtils.ceil((float)(ratio <= 1 ? Math.pow(ratio, 5) :  Math.pow(ratio, -7)) * this.getCaloricValueWeight());
    }

    public int getCarbsScore(int carbs, Benefits benefits){
        double ratio = (double)carbs / benefits.getCarbsTarget();
        return MathUtils.ceil((float)(ratio <= 1 ? Math.pow(ratio, 2) :  Math.pow(ratio, -3)) * this.getCarbsWeight());
    }

    public int getFatScore(int fat, Benefits benefits){
        double ratio = (double)fat / benefits.getFatTarget();
        return MathUtils.ceil((float)(ratio <= 1 ? Math.pow((1.1 - ratio) / 1.1, 1/4) : Math.pow(ratio, -5) / 2) * this.getFatWeight());
    }

    public int getProteinsScore(int proteins, Benefits benefits){
        double ratio = (double)proteins / benefits.getProteinsTarget();
        return MathUtils.ceil((float)(ratio <= 1 ? Math.pow(ratio, 2) * 4 / 5 * this.getProteinsWeight() : this.getProteinsWeight() - Math.pow(ratio, -1) / 5));
    }

    public int getVitaminAScore(int vitaminA, Benefits benefits){
        double ratio = (double)vitaminA / benefits.getVitaminATarget();
        return MathUtils.ceil((float)(ratio <= 1 ? Math.pow(ratio, 5) * 7 / 10 * this.getVitaminAWeight() : this.getVitaminAWeight() - Math.pow(ratio, -1) * 3 / 10));
    }

    public int getVitaminCScore(int vitaminC, Benefits benefits){
        double ratio = (double)vitaminC / benefits.getVitaminCTarget();
        return MathUtils.ceil((float)(ratio <= 1 ? Math.pow(ratio, 5) * 7 / 10 * this.getVitaminCWeight() :  this.getVitaminCWeight() - Math.pow(ratio, -1) * 3 / 10));
    }

    public int getCalciumScore(int calcium, Benefits benefits){
        double ratio = calcium / benefits.getCalciumTarget();
        return MathUtils.ceil((float)(ratio <= 1 ? Math.pow(ratio, 3) :  Math.pow(ratio, -2)) * this.getCalciumWeight());
    }

    public int getIronScore(int iron, Benefits benefits){
        double ratio = (double)iron / benefits.getIronTarget();
        return MathUtils.ceil((float)(ratio <= 1 ? Math.pow(ratio, 3) :  Math.pow(ratio, -2)) * this.getIronWeight());
    }

    public int getTotalScore(FoodCounter foodCounter, Benefits benefits) {
        NutritionFacts tnf = foodCounter.getTotalNutritionFacts();
        return  this.getCaloricValueScore(tnf.getCaloricValue(), benefits) +
                this.getCarbsScore(tnf.getCarbs(), benefits) +
                this.getFatScore(tnf.getFat(), benefits) +
                this.getProteinsScore(tnf.getProteins(), benefits);
    }

    /**
     * HUD TABLE
     */

    public Table getHudTable(Skin skin, Benefits benefits) {

        Table tbHud = new Table(skin);

        hudItems = new ArrayList<HudItem>();

        hiTime = new HudItem(skin, Constants.SCORE_TIME_LABEL, benefits.getMaxmumGameTime(), Constants.SCORE_TIME_UNIT, new NegativeFormatting());
        hudItems.add(hiTime);

        for(NutritionType type : this.getNutritionTypes()) {
            final HudItem hudItem = new HudItem(skin, type, benefits, type.getFormatting());
            hudItems.add(hudItem);
        }

        for(HudItem hudItem : hudItems) {
            tbHud.add(hudItem.getRow());
            tbHud.row();
        }

        tbHud.padRight(30).padTop(15);

        return tbHud;
    }

    public void updateHudTable(float time, FoodCounter foodCounter) {

        NutritionFacts totalNutritionFacts = foodCounter.getTotalNutritionFacts();

        hiTime.update(time);

        for(HudItem hudItem : hudItems) {
            if(hudItem.getNutritionType() != null) {
                hudItem.update(totalNutritionFacts.get(hudItem.getNutritionType()));
            }
        }
    }

    /**
     * RESULT TABLE
     */
    private Label[] lbScores;
    private int[] scores;
    private int[] shownScores;

    private Label lbScoreTotal;
    private Label lbRankTotal;

    private int scoreTotalShown;
    private int rankTotalShown;
    private int rank;

    protected abstract NutritionType[] getNutritionTypes();

    public Table getResultTable(Skin skin, Benefits benefits, FoodCounter foodCounter) {
        scoreTotalShown = -1;
        rankTotalShown = -1;
        rank = -1;

        final Table tbResult = new Table(skin);

        NutritionType[] nutritionTypes = this.getNutritionTypes();

        tbResult.add().colspan(3);

        for(NutritionType type : nutritionTypes) {
            final Label lbItem = new Label(type.getName() + "\n[" + type.getUnit() + "]", skin);
            lbItem.setAlignment(Align.center);
            tbResult.add(lbItem).colspan(3).width(250);
        }

        tbResult.row().padTop(10);

        for(AFood food : foodCounter.getFoodList()) {
            NutritionFacts nf = food.getNutritionFacts();
            int count = foodCounter.getFoodCount(food);

            tbResult.add(String.valueOf(count)).right();
            tbResult.add("x").padLeft(5).padRight(5).center();
            tbResult.add(food.getName()).left();

            for(NutritionType type : nutritionTypes) {
                int itemValue = nf.get(type);
                tbResult.add(String.valueOf(itemValue * count)).right();
                tbResult.add("(" + count + "x " + String.valueOf(itemValue) + ")").colspan(2);
            }

            tbResult.row();
        }

        tbResult.row().padTop(10);
        tbResult.add(Constants.TOTAL_LABEL).colspan(3).right();

        NutritionFacts tnf = foodCounter.getTotalNutritionFacts();
        for(NutritionType type : nutritionTypes) {
            tbResult.add(String.valueOf(tnf.get(type))).right();
            tbResult.add("/");
            tbResult.add(String.valueOf(benefits.getTarget(type))).left();
        }

        tbResult.row().padTop(15);
        tbResult.add(Constants.SCORE_LABEL).colspan(3).right();

        int i = 0;
        lbScores = new Label[nutritionTypes.length];
        scores = new int[nutritionTypes.length];
        for(NutritionType type : nutritionTypes) {
            final Label lbValue = new Label("???", skin);
            final int scoreValue = this.getScore(type, tnf.get(type), benefits);

            lbScores[i] = lbValue;
            scores[i] = scoreValue;
            i++;

            tbResult.add(lbValue).colspan(3);
        }

        tbResult.row().padTop(15);
        tbResult.add().colspan(3);
        tbResult.add(Constants.SCORE_TOTAL_LABEL).colspan(3).right();

        lbScoreTotal = new Label("???", skin);
        final int scoreTotal = this.getTotalScore(foodCounter, benefits);
        tbResult.add(lbScoreTotal).colspan(3).left().padLeft(15);

        tbResult.add(Constants.RANK_TOTAL_LABEL).colspan(3).right();
        lbRankTotal = new Label("???", skin);
        tbResult.add(lbRankTotal).colspan(3).left().padLeft(15);

        shownScores = new int[lbScores.length];
        for(int j = 0; j < shownScores.length; j++) {
            shownScores[j] = -1;
        }

        AliceHttpRequest.getInstance().setScore(Mode.getByEvaluation(this), scoreTotal, new AliceHttpListener() {
            @Override
            public void onResult(String result) {
                rank = Integer.parseInt(result);
            }

            @Override
            public void onError(String errorMessage) {
                // do nothing
            }
        });

        AsyncExecutor scoreUpdate = new AsyncExecutor(10);
        scoreUpdate.submit(new AsyncTask<Void>() {

            @Override
            public Void call() throws Exception {
                try {

                    Thread.sleep(2000);

                    scoreTotalShown = 0;
                    int summedScores = 0;
                    int tmpTotalScore = 0;


                    for(int i = 0; i < lbScores.length; i++) {

                        for(int counter = 1; counter < scores[i]; counter += MathUtils.ceil(scores[i] / 50f)) {
                            shownScores[i] = counter;
                            scoreTotalShown = tmpTotalScore + counter;
                            Thread.sleep(20);
                        }
                        shownScores[i] = scores[i];
                        summedScores += scores[i];
                        scoreTotalShown = summedScores;
                        tmpTotalScore = scoreTotalShown;

                        Thread.sleep(700);
                    }

                    if(rank != -1) {
                        for(int counter = (rank + 50) * 20; counter > rank; counter -= MathUtils.ceil(counter / 20f)) {
                            rankTotalShown = counter;
                            Thread.sleep(20);
                        }
                        rankTotalShown = rank;
                    }
                    else {
                        rankTotalShown = -2;
                    }

                } catch (InterruptedException e) {
                    // TODO
                }

                return null;
            }
        });

        return tbResult;
    }

    public void updateResultTable() {

        for(int i = 0; i < lbScores.length; i++) {
            if(shownScores[i] > -1) {
                lbScores[i].setText(String.valueOf(shownScores[i]));
            }
        }

        if(scoreTotalShown > -1) {
            lbScoreTotal.setText(String.valueOf(scoreTotalShown));
        }
        if(rankTotalShown > -1) {
            lbRankTotal.setText(String.valueOf(rankTotalShown));
        }
        else if(scoreTotalShown == -2) {
            lbRankTotal.setText("---");
        }
    }

    /**
     * GAME OVER REASON
     */

    public abstract boolean isGameOver(Benefits benefits, float time, FoodCounter foodCounter);
    public abstract List<GameOverReason> getGameOverReasons(Benefits benefits, float time, FoodCounter foodCounter);

    public enum GameOverReason {
        TIME(Constants.GAMEOVER_TIME), CALORIC_VALUE(Constants.GAMEOVER_CALORIC_VALUE), FAT(Constants.GAMEOVER_FAT);

        private String text;

        GameOverReason(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

}

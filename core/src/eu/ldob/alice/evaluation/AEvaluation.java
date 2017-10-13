package eu.ldob.alice.evaluation;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncTask;

import java.util.List;

import eu.ldob.alice.Constants;
import eu.ldob.alice.items.AFood;
import eu.ldob.alice.items.util.FoodCounter;
import eu.ldob.alice.items.util.NutritionFacts;
import eu.ldob.alice.rest.AliceHttpListener;
import eu.ldob.alice.rest.AliceHttpRequest;

public abstract class AEvaluation {

    /**
     * HUD TABLE
     */

    public abstract Table getHudTable(Skin skin, Benefits benefits);
    public abstract void updateHudTable(float time, FoodCounter foodCounter);

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

    public int getCaloricValueScore(int caloricValue, int target) {
        double ratio = (double)caloricValue / target;
        return MathUtils.ceil((float)(ratio <= 1 ? Math.pow(ratio, 5) :  Math.pow(ratio, -7)) * this.getCaloricValueWeight());
    }

    public int getCarbsScore(int carbs){
        double ratio = (double)carbs / Constants.CARBS_TARGET;
        return MathUtils.ceil((float)(ratio <= 1 ? Math.pow(ratio, 2) :  Math.pow(ratio, -3)) * this.getCarbsWeight());
    }

    public int getFatScore(int fat){
        double ratio = (double)fat / Constants.FAT_TARGET;
        return MathUtils.ceil((float)(ratio <= 1 ? Math.pow((1.1 - ratio) / 1.1, 1/4) : Math.pow(ratio, -5) / 2) * this.getFatWeight());
    }

    public int getProteinsScore(int proteins){
        double ratio = (double)proteins / Constants.PROTEINS_TARGET;
        return MathUtils.ceil((float)(ratio <= 1 ? Math.pow(ratio, 2) * 4 / 5 * this.getProteinsWeight() : this.getProteinsWeight() - Math.pow(ratio, -1) / 5));
    }

    public int getVitaminAScore(int vitaminA){
        double ratio = (double)vitaminA / Constants.VITAMIN_A_TARGET;
        return MathUtils.ceil((float)(ratio <= 1 ? Math.pow(ratio, 5) * 7 / 10 * this.getVitaminAWeight() : this.getVitaminAWeight() - Math.pow(ratio, -1) * 3 / 10));
    }

    public int getVitaminCScore(int vitaminC){
        double ratio = (double)vitaminC / Constants.VITAMIN_C_TARGET;
        return MathUtils.ceil((float)(ratio <= 1 ? Math.pow(ratio, 5) * 7 / 10 * this.getVitaminCWeight() :  this.getVitaminCWeight() - Math.pow(ratio, -1) * 3 / 10));
    }

    public int getCalciumScore(int calcium){
        double ratio = calcium / Constants.CALCIUM_TARGET;
        return MathUtils.ceil((float)(ratio <= 1 ? Math.pow(ratio, 3) :  Math.pow(ratio, -2)) * this.getCalciumWeight());
    }

    public int getIronScore(int iron){
        double ratio = (double)iron / Constants.IRON_TARGET;
        return MathUtils.ceil((float)(ratio <= 1 ? Math.pow(ratio, 3) :  Math.pow(ratio, -2)) * this.getIronWeight());
    }

    public int getTotalScore(FoodCounter foodCounter, Benefits benefits) {
        NutritionFacts tnf = foodCounter.getTotalNutritionFacts();
        return  this.getCaloricValueScore(tnf.getCaloricValue(), benefits.getCaloricValueTarget()) +
                this.getCarbsScore(tnf.getCarbs()) +
                this.getFatScore(tnf.getFat()) +
                this.getProteinsScore(tnf.getProteins());
    }

    /**
     * RESULT TABLE
     */


    private Label lbScoreCaloricValue;
    private Label lbScoreCarbs;
    private Label lbScoreFat;
    private Label lbScoreProteins;
    private Label lbScoreTotal;
    private Label lbRankTotal;

    private int scoreCaloricValueShown = -1;
    private int scoreCarbsShown = -1;
    private int scoreFatShown = -1;
    private int scoreProteinsShown = -1;
    private int scoreTotalShown = -1;
    private int rankTotalShown = -1;

    private int rank = -1;

    protected abstract List<EvaluationItem> getEvaluationItems();

    public Table getResultTable(Skin skin, Benefits benefits, float time, FoodCounter foodCounter) {
        final Table tbResult = new Table(skin);

        List<EvaluationItem> evaluationItems = this.getEvaluationItems();

        final Label lbCaloricValue = new Label("Brennwert\n[kcal]", skin);
        lbCaloricValue.setAlignment(Align.center);
        final Label lbCarbs = new Label("Kohlenhydrate\n[g]", skin);
        lbCarbs.setAlignment(Align.center);
        final Label lbFat = new Label("Fett\n[g]", skin);
        lbFat.setAlignment(Align.center);
        final Label lbProteins = new Label("Proteine\n[g]", skin);
        lbProteins.setAlignment(Align.center);

        tbResult.add().colspan(3);
        tbResult.add(lbCaloricValue).colspan(3).width(250);
        tbResult.add(lbCarbs).colspan(3).width(250);
        tbResult.add(lbFat).colspan(3).width(250);
        tbResult.add(lbProteins).colspan(3).width(250);
        tbResult.row().padTop(10);

        for(AFood food : foodCounter.getFoodList()) {
            NutritionFacts nf = food.getNutritionFacts();
            int count = foodCounter.getFoodCount(food);

            tbResult.add(String.valueOf(count)).right();
            tbResult.add("x").padLeft(5).padRight(5).center();
            tbResult.add(food.getName()).left();

            tbResult.add(String.valueOf(nf.getCaloricValue() * count)).right();
            tbResult.add("(" + count + "x " + String.valueOf(nf.getCaloricValue()) + ")").colspan(2);

            tbResult.add(String.valueOf(nf.getCarbs() * count)).right();
            tbResult.add("(" + count + "x " + String.valueOf(nf.getCarbs()) + ")").colspan(2);

            tbResult.add(String.valueOf(nf.getFat() * count)).right();
            tbResult.add("(" + count + "x " + String.valueOf(nf.getFat()) + ")").colspan(2);

            tbResult.add(String.valueOf(nf.getProteins() * count)).right();
            tbResult.add("(" + count + "x " + String.valueOf(nf.getProteins()) + ")").colspan(2);

            tbResult.row();
        }

        tbResult.row().padTop(10);

        NutritionFacts tnf = foodCounter.getTotalNutritionFacts();

        tbResult.add(Constants.TOTAL_LABEL).colspan(3).right();
        tbResult.add(String.valueOf(tnf.getCaloricValue())).right();
        tbResult.add("/");
        tbResult.add(String.valueOf(benefits.getCaloricValueTarget())).left();
        tbResult.add(String.valueOf(tnf.getCarbs())).right();
        tbResult.add("/");
        tbResult.add(String.valueOf(Constants.CARBS_TARGET)).left();
        tbResult.add(String.valueOf(tnf.getFat())).right();
        tbResult.add("/");
        tbResult.add(String.valueOf(Constants.FAT_TARGET)).left();
        tbResult.add(String.valueOf(tnf.getProteins())).right();
        tbResult.add("/");
        tbResult.add(String.valueOf(Constants.PROTEINS_TARGET)).left();

        tbResult.row().padTop(15);
        tbResult.add(Constants.SCORE_LABEL).colspan(3).right();

        lbScoreCaloricValue = new Label("???", skin);
        final int scoreCaloricValue = this.getCaloricValueScore(tnf.getCaloricValue(), benefits.getCaloricValueTarget());
        tbResult.add(lbScoreCaloricValue).colspan(3);

        lbScoreCarbs = new Label("???", skin);
        final int scoreCarbs = this.getCarbsScore(tnf.getCarbs());
        tbResult.add(lbScoreCarbs).colspan(3);

        lbScoreFat = new Label("???", skin);
        final int scoreFat = this.getFatScore(tnf.getFat());
        tbResult.add(lbScoreFat).colspan(3);

        lbScoreProteins = new Label("???", skin);
        final int scoreProteins = this.getProteinsScore(tnf.getProteins());
        tbResult.add(lbScoreProteins).colspan(3);

        tbResult.row().padTop(15);
        tbResult.add().colspan(3);
        tbResult.add(Constants.SCORE_TOTAL_LABEL).colspan(3).right();

        lbScoreTotal = new Label("???", skin);
        final int scoreTotal = this.getTotalScore(foodCounter, benefits);
        tbResult.add(lbScoreTotal).colspan(3).left().padLeft(15);

        tbResult.add(Constants.RANK_TOTAL_LABEL).colspan(3).right();
        lbRankTotal = new Label("???", skin);
        tbResult.add(lbRankTotal).colspan(3).left().padLeft(15);

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
                    int tmpTotalScore = 0;

                    for(int counter = 0; counter < scoreCaloricValue; counter += scoreCaloricValue / 50) {
                        scoreCaloricValueShown = counter;
                        scoreTotalShown = tmpTotalScore + counter;
                        Thread.sleep(20);
                    }
                    scoreCaloricValueShown = scoreCaloricValue;
                    scoreTotalShown = scoreCaloricValue;
                    tmpTotalScore = scoreTotalShown;

                    Thread.sleep(700);

                    for(int counter = 0; counter < scoreCarbs; counter += scoreCarbs / 50) {
                        scoreCarbsShown = counter;
                        scoreTotalShown = tmpTotalScore + counter;
                        Thread.sleep(20);
                    }
                    scoreCarbsShown = scoreCarbs;
                    scoreTotalShown = scoreCaloricValue + scoreCarbs;
                    tmpTotalScore = scoreTotalShown;

                    Thread.sleep(700);

                    for(int counter = 0; counter < scoreFat; counter += scoreFat / 50) {
                        scoreFatShown = counter;
                        scoreTotalShown = tmpTotalScore + counter;
                        Thread.sleep(20);
                    }
                    scoreFatShown = scoreFat;
                    scoreTotalShown = scoreCaloricValue + scoreCarbs + scoreFat;
                    tmpTotalScore = scoreTotalShown;

                    Thread.sleep(700);

                    for(int counter = 0; counter < scoreProteins; counter += scoreProteins / 50) {
                        scoreProteinsShown = counter;
                        scoreTotalShown = tmpTotalScore + counter;
                        Thread.sleep(20);
                    }
                    lbScoreProteins.setText(String.valueOf(scoreProteins));
                    scoreTotalShown = scoreCaloricValue + scoreCarbs + scoreFat + scoreProteins;

                    Thread.sleep(700);

                    if(rank != -1) {
                        for (int counter = (rank + 50) * 20; counter >= rank; counter -= counter / 20) {
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

        if(scoreCaloricValueShown > -1) {
            lbScoreCaloricValue.setText(String.valueOf(scoreCaloricValueShown));
        }
        if(scoreCarbsShown > -1) {
            lbScoreCarbs.setText(String.valueOf(scoreCarbsShown));
        }
        if(scoreFatShown > -1) {
            lbScoreFat.setText(String.valueOf(scoreFatShown));
        }
        if(scoreProteinsShown > -1) {
            lbScoreProteins.setText(String.valueOf(scoreProteinsShown));
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
        TIME("Zeit ist ausgelaufen!"), CALORIC_VALUE("Zu viel Brennwerte!"), FAT("Zu viel Fett!");

        private String text;

        GameOverReason(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

}

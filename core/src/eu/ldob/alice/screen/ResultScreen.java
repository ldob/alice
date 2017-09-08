package eu.ldob.alice.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import eu.ldob.alice.AliceGame;
import eu.ldob.alice.Constants;
import eu.ldob.alice.items.AFood;
import eu.ldob.alice.items.food.NutritionFacts;
import eu.ldob.alice.mode.IEvaluation;
import eu.ldob.alice.mode.Mode;
import eu.ldob.alice.mode.Benefits;
import eu.ldob.alice.items.FoodCounter;
import eu.ldob.alice.rest.AliceHttpListener;
import eu.ldob.alice.rest.AliceHttpRequest;

public class ResultScreen implements Screen {

    private Stage stage;
    private Skin skin;

    private AliceGame game;
    private float time;
    private FoodCounter counter;
    private Mode mode;
    private Benefits benefits;

    private Label lbRank;
    private int totalScore;
    private String rank;

    public ResultScreen(AliceGame game, Skin skin, float time, FoodCounter counter, Mode mode, Benefits benefits) {
        this.game = game;
        this.skin = skin;
        this.mode = mode;
        this.time = time;
        this.counter = counter;
        this.benefits = benefits;

        init();
    }

    private void init() {
        totalScore = mode.getEvaluation().getTotalScore(time, counter);

        AliceHttpRequest.getInstance().setScore(mode, totalScore, new AliceHttpListener() {
            @Override
            public void onResult(String result) {
                rank = result;
                lbRank.setText("Rang: " + rank);
            }

            @Override
            public void onError(String errorMessage) {
                // do nothing
            }
        });
    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table tbRoot = new Table();
        tbRoot.setFillParent(true);
        tbRoot.setDebug(Constants.DEBUG);
        stage.addActor(tbRoot);

        final Label lbHead = new Label(Constants.RESULT_LABEL, skin);
        tbRoot.add(lbHead).expand().top();

        Table tbResult = mode.getEvaluation().getResultTable(skin, benefits, time, counter);
        tbResult.setFillParent(true);
        tbResult.setDebug(Constants.DEBUG);
        tbRoot.addActor(tbResult);

        Label lbTotalScore = new Label(Constants.TOTAL_SCORE_LABEL + ": " + totalScore, skin);

        lbRank = new Label("...", skin);
        lbRank.setAlignment(Align.center);
        if(rank != null) {
            lbRank.setText("Rang: " + rank);
        }

        final TextButton btBack = new TextButton(Constants.BACK_LABEL, skin);
        final TextButton btAgain = new TextButton(Constants.AGAIN_LABEL, skin);
        final TextButton btHighscore = new TextButton(Constants.HIGHSCORE_LABEL, skin);

        tbResult.row();
        tbResult.add(lbTotalScore).colspan(4);
        tbResult.add(lbRank).colspan(3);
        tbResult.row().padTop(25);
        tbResult.add(btBack).colspan(3);
        tbResult.add(btAgain).colspan(2);
        tbResult.add(btHighscore).colspan(2);

        final ResultScreen instance = this;

        btBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.showHomeScreen();
            }
        });

        btAgain.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.showGameScreen(mode);
            }
        });

        btHighscore.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.showHighscoreScreen(instance, mode);
            }
        });
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
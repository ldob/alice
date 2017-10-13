package eu.ldob.alice.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import org.json.JSONArray;
import org.json.JSONObject;

import eu.ldob.alice.AliceGame;
import eu.ldob.alice.Constants;
import eu.ldob.alice.AAliceScreen;
import eu.ldob.alice.evaluation.Mode;
import eu.ldob.alice.evaluation.Benefits;
import eu.ldob.alice.items.util.FoodCounter;
import eu.ldob.alice.rest.AliceHttpListener;
import eu.ldob.alice.rest.AliceHttpRequest;

public class ResultScreen extends AAliceScreen {

    private Stage stage;
    private Skin skin;

    private AliceGame game;
    private float time;
    private FoodCounter counter;
    private Mode mode;
    private Benefits benefits;

    public ResultScreen(AliceGame game, Skin skin, float time, FoodCounter counter, Mode mode, Benefits benefits) {
        this.game = game;
        this.skin = skin;
        this.mode = mode;
        this.time = time;
        this.counter = counter;
        this.benefits = benefits;
    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table tbRoot = new Table();
        tbRoot.setFillParent(true);
        tbRoot.setDebug(Constants.DEBUG);
        stage.addActor(tbRoot);

        final Label lbHead = new Label(Constants.RESULT_LABEL, skin, "title");
        tbRoot.add(lbHead).expand().top().padTop(30);

        Table tbResult = mode.getEvaluation().getResultTable(skin, benefits, time, counter);
        tbResult.setFillParent(true);
        tbResult.setDebug(Constants.DEBUG);
        tbRoot.addActor(tbResult);

        final TextButton btBack = new TextButton(Constants.BACK_LABEL, skin);
        final TextButton btAgain = new TextButton(Constants.AGAIN_LABEL, skin);
        final TextButton btHighscore = new TextButton(Constants.HIGHSCORE_LABEL, skin);

        Table tbButtons = new Table(skin);
        tbButtons.add(btBack).width(300);
        tbButtons.add(btAgain).width(300);
        tbButtons.add(btHighscore).width(300);

        tbRoot.row().padBottom(80);
        tbRoot.add(tbButtons);

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

                final Table tbHighscore = new Table(skin);

                tbHighscore.add(new Label(Constants.HIGHSCORE_LABEL, skin, "title")).colspan(2).center().expand();
                tbHighscore.row().padTop(30);

                final Window window = new Window("", skin);
                /*window.setPosition(stage.getViewport().getWorldWidth() * 0.1f, stage.getViewport().getWorldHeight() * 0.1f);*/
                window.setWidth(stage.getViewport().getWorldWidth()/* * 0.8f*/);
                window.setHeight(stage.getViewport().getWorldHeight()/* * 0.8f*/);
                window.add(tbHighscore);
                stage.addActor(window);

                final TextButton btBack = new TextButton(Constants.BACK_LABEL, skin);
                btBack.pad(10, 20, 10, 20);

                btBack.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        tbHighscore.remove();
                        window.remove();
                    }
                });


                AliceHttpRequest.getInstance().getHighscore(mode, new AliceHttpListener() {
                    @Override
                    public void onResult(String result) {

                        JSONObject json = new JSONObject(result);
                        JSONArray score = json.getJSONArray("highscore");

                        final Label lbPlayer = new Label("Player", skin);
                        lbPlayer.setAlignment(Align.center);
                        final Label lbScore = new Label("Score", skin);
                        lbScore.setAlignment(Align.center);

                        tbHighscore.add(lbPlayer).uniform();
                        tbHighscore.add(lbScore).uniform();
                        tbHighscore.row().padTop(10);

                        for(int i = 0; i < score.length() && i < 10; i++) {
                            JSONObject scr = score.getJSONObject(i);

                            tbHighscore.add(scr.getString("player")).padRight(30).padLeft(30);
                            tbHighscore.add(scr.getString("score"));
                            tbHighscore.row();
                        }

                        tbHighscore.row().padTop(25).height(80);
                        tbHighscore.add(btBack).colspan(2);
                    }

                    @Override
                    public void onError(String errorMessage) {

                        final Label lbError = new Label("Fehler beim Laden des Highscores...\n(" + errorMessage + ")", skin);
                        lbError.setAlignment(Align.center);

                        tbHighscore.add(lbError).uniform();
                        tbHighscore.row().padTop(25).height(80);
                        tbHighscore.add(btBack).colspan(2);
                    }
                });
            }
        });
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR_WHITE.r, Constants.BACKGROUND_COLOR_WHITE.g, Constants.BACKGROUND_COLOR_WHITE.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mode.getEvaluation().updateResultTable();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        game.pauseMusic();
    }

    @Override
    public void resume() {
        game.resumeMusic();
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
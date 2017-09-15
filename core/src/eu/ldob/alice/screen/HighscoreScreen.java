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

import org.json.JSONArray;
import org.json.JSONObject;

import eu.ldob.alice.AliceGame;
import eu.ldob.alice.Constants;
import eu.ldob.alice.mode.Mode;
import eu.ldob.alice.rest.AliceHttpListener;
import eu.ldob.alice.rest.AliceHttpRequest;

public class HighscoreScreen implements Screen {

    private Stage stage;
    private Skin skin;

    private AliceGame game;
    private Mode mode;
    private ResultScreen resultScreen;

    private Table tbHighscore;

    private boolean lockRendering = false;

    public HighscoreScreen(AliceGame game, Skin skin, Mode mode, ResultScreen resultScreen) {
        this.game = game;
        this.skin = skin;
        this.mode = mode;
        this.resultScreen = resultScreen;

        init();
    }

    private void init() {
        AliceHttpRequest.getInstance().getHighscore(new AliceHttpListener() {
            @Override
            public void onResult(String result) {

                lockRendering = true;

                JSONObject json = new JSONObject(result);
                JSONArray score = json.getJSONArray(mode.toString());

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

                final TextButton btBack = new TextButton(Constants.BACK_LABEL, skin);

                tbHighscore.row().padTop(25);
                tbHighscore.add(btBack).colspan(2);

                btBack.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        game.showScreen(resultScreen);
                    }
                });

                lockRendering = false;
            }

            @Override
            public void onError(String errorMessage) {

                final Label lbError = new Label("Fehler... " + errorMessage, skin);
                lbError.setAlignment(Align.center);

                final TextButton btBack = new TextButton(Constants.BACK_LABEL, skin);

                tbHighscore.add(lbError).uniform();
                tbHighscore.row().padTop(25);
                tbHighscore.add(btBack).colspan(2);

                btBack.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        game.showScreen(resultScreen);
                    }
                });
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

        final Label lbHead = new Label(Constants.HIGHSCORE_LABEL, skin);
        tbRoot.add(lbHead).expand().top();

        tbHighscore = new Table(skin);
        tbHighscore.setFillParent(true);
        tbHighscore.setDebug(Constants.DEBUG);
        tbRoot.addActor(tbHighscore);
    }

    @Override
    public void render(float delta) {

        if(!lockRendering) {
            Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR_WHITE.r, Constants.BACKGROUND_COLOR_WHITE.g, Constants.BACKGROUND_COLOR_WHITE.b, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            stage.act(delta);
            stage.draw();
        }
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
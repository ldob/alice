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

public class ResultScreen implements Screen {

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

        final Label lbHead = new Label(Constants.RESULT_LABEL, skin);
        tbRoot.add(lbHead).expand().top();

        Table tbResult = mode.getEvaluation().getResultTable(skin, benefits, time, counter);
        tbResult.setFillParent(true);
        tbResult.setDebug(Constants.DEBUG);
        tbRoot.addActor(tbResult);

        final TextButton btBack = new TextButton(Constants.BACK_LABEL, skin);
        final TextButton btAgain = new TextButton(Constants.AGAIN_LABEL, skin);

        tbResult.row();
        tbResult.row().padTop(25);
        tbResult.add(btBack).colspan(4);
        tbResult.add(btAgain).colspan(3);

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
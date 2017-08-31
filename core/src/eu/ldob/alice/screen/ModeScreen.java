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
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import eu.ldob.alice.AliceGame;
import eu.ldob.alice.Constants;
import eu.ldob.alice.mode.Benefits;
import eu.ldob.alice.mode.Mode;


public class ModeScreen implements Screen {

    private Stage stage;
    private Skin skin;

    private AliceGame game;
    private Benefits benefits;

    public ModeScreen(AliceGame game, Skin skin, Benefits benefits) {
        this.game = game;
        this.skin = skin;
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

        final Label lbHead = new Label(Constants.MODE_LABEL, skin);
        tbRoot.add(lbHead).expand().top();

        Table tbMode = new Table();
        tbMode.setFillParent(true);
        tbMode.setDebug(Constants.DEBUG);
        tbRoot.addActor(tbMode);

        final TextButton btTime = new TextButton(Mode.TIME.getName(), skin);
        final TextButton btVitamins = new TextButton(Mode.COLLECT_VITAMINS.getName(), skin);
        final TextButton btFat = new TextButton(Mode.AVOID_FAT.getName(), skin);

        tbMode.add(btTime).fillX().uniformX();
        tbMode.row().padTop(15);
        tbMode.add(btVitamins).fillX().uniformX();
        tbMode.row().padTop(15);
        tbMode.add(btFat).fillX().uniformX();

        btTime.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.showGameScreen(Mode.TIME);
            }
        });

        btVitamins.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.showGameScreen(Mode.COLLECT_VITAMINS);
            }
        });

        btFat.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.showGameScreen(Mode.AVOID_FAT);
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
package eu.ldob.alice.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import eu.ldob.alice.AliceGame;
import eu.ldob.alice.Constants;


public class HomeScreen implements Screen {

    private AliceGame game;

    private Stage stage;
    private Skin skin;

    public HomeScreen(AliceGame game, Skin skin) {
        this.game = game;
        this.skin = skin;
    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table tbRoot = new Table();
        tbRoot.setFillParent(true);
        tbRoot.setDebug(Constants.DEBUG);
        stage.addActor(tbRoot);

        final Label lbHead = new Label(Constants.HOME_LABEL, skin);
        tbRoot.add(lbHead).expand().top();

        Table tbHome = new Table();
        tbHome.setFillParent(true);
        tbHome.setDebug(Constants.DEBUG);
        tbRoot.addActor(tbHome);

        final TextButton btPlay = new TextButton(Constants.PLAY_LABEL, skin);
        final TextButton btBenefits = new TextButton(Constants.BENEFITS_LABEL, skin);
        final TextButton btSettings = new TextButton(Constants.SETTINGS_LABEL, skin);
        final TextButton btExit = new TextButton(Constants.EXIT_LABEL, skin);

        tbHome.add(btPlay).fillX().uniformX();
        tbHome.row().padTop(15);
        tbHome.add(btBenefits).fillX().uniformX();
        tbHome.row().padTop(15);
        tbHome.add(btSettings).fillX().uniformX();
        tbHome.row().padTop(15);
        tbHome.add(btExit).fillX().uniformX();

        btPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.showModeScreen();
            }
        });

        btBenefits.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.showBenefitsScreen();
            }
        });

        btExit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
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
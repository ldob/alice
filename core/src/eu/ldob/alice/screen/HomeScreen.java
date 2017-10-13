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
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import eu.ldob.alice.AliceGame;
import eu.ldob.alice.Constants;
import eu.ldob.alice.AAliceScreen;


public class HomeScreen extends AAliceScreen {

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

        final Label lbHead = new Label(Constants.HOME_LABEL, skin, "title");
        tbRoot.add(lbHead).expand().top().padTop(30);

        Table tbHome = new Table();
        tbHome.setFillParent(true);
        tbHome.setDebug(Constants.DEBUG);
        tbRoot.addActor(tbHome);

        final TextButton btPlay = new TextButton(Constants.PLAY_LABEL, skin);
        final TextButton btBenefits = new TextButton(Constants.BENEFITS_LABEL, skin);
        final TextButton btSettings = new TextButton(Constants.SETTINGS_LABEL, skin);
        final TextButton btExit = new TextButton(Constants.EXIT_LABEL, skin);

        btPlay.pad(15, 60, 15, 60);
        btBenefits.pad(15, 60, 15, 60);
        btSettings.pad(15, 60, 15, 60);
        btExit.pad(15, 60, 15, 60);

        tbHome.add(btPlay).fillX().uniform();
        tbHome.row().padTop(30);
        tbHome.add(btBenefits).fillX().uniform();
        tbHome.row().padTop(30);
        tbHome.add(btSettings).fillX().uniform();
        tbHome.row().padTop(60);
        tbHome.add(btExit).fillX().uniform();

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

        btSettings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.showSettingsScreen();
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

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR_WHITE.r, Constants.BACKGROUND_COLOR_WHITE.g, Constants.BACKGROUND_COLOR_WHITE.b, 1);
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
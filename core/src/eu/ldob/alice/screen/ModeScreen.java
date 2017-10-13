package eu.ldob.alice.screen;

import com.badlogic.gdx.Gdx;
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
import eu.ldob.alice.AAliceScreen;
import eu.ldob.alice.evaluation.Benefits;
import eu.ldob.alice.evaluation.Mode;


public class ModeScreen extends AAliceScreen {

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

        final Label lbHead = new Label(Constants.MODE_LABEL, skin, "title");
        tbRoot.add(lbHead).expand().top().padTop(30);

        Table tbMode = new Table();
        tbMode.setFillParent(true);
        tbMode.setDebug(Constants.DEBUG);
        tbRoot.addActor(tbMode);

        for(final Mode mode : Mode.values()) {
            final TextButton btMode = new TextButton(mode.getName(), skin);
            btMode.pad(15, 60, 15, 60);

            tbMode.add(btMode).fillX().uniform();
            tbMode.row().padTop(30);

            btMode.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    game.showGameScreen(mode);
                }
            });
        }

        final TextButton btBack = new TextButton(Constants.BACK_LABEL, skin);
        btBack.pad(15, 60, 15, 60);

        tbMode.row().padTop(60);
        tbMode.add(btBack).uniformY();


        btBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.showHomeScreen();
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
package eu.ldob.alice.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import eu.ldob.alice.AliceGame;
import eu.ldob.alice.Constants;
import eu.ldob.alice.AAliceScreen;
import eu.ldob.alice.util.SettingsStorage;


public class SettingsScreen extends AAliceScreen {

    private Stage stage;
    private Skin skin;

    private AliceGame game;

    public SettingsScreen(AliceGame game, Skin skin) {
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

        final Label lbHead = new Label(Constants.SETTINGS_LABEL, skin, "title");
        tbRoot.add(lbHead).expand().top().padTop(30);

        Table tbSettings = new Table();
        tbSettings.setFillParent(true);
        tbSettings.setDebug(Constants.DEBUG);
        tbRoot.addActor(tbSettings);

        final TextButton btBack = new TextButton(Constants.BACK_LABEL, skin);
        final TextButton btSave = new TextButton(Constants.SAVE_LABEL, skin);

        final Label lbName = new Label(Constants.NAME_LABEL, skin);
        final TextField tfName = new TextField(SettingsStorage.getPlayerName(), skin);

        final Label lbSound = new Label(Constants.SOUND_LABEL, skin);
        final CheckBox cbSound = new CheckBox("", skin);
        cbSound.setChecked(SettingsStorage.isSoundOn());
        cbSound.setTransform(true);
        cbSound.setScale(1f);                       // TODO change scaling to 1.3f

        tbSettings.add(lbName).width(100).left();
        tbSettings.add(tfName).width(250);
        tbSettings.row().padTop(20);
        tbSettings.add(lbSound).left();
        tbSettings.add(cbSound);

        tbSettings.row().padTop(60);
        tbSettings.add(btBack).left();
        tbSettings.add(btSave).right();

        btBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.showHomeScreen();
            }
        });

        btSave.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SettingsStorage.savePlayerName(tfName.getText());
                SettingsStorage.saveSoundOn(cbSound.isChecked());
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
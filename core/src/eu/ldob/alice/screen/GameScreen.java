package eu.ldob.alice.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.List;

import eu.ldob.alice.AliceGame;
import eu.ldob.alice.Constants;
import eu.ldob.alice.AAliceScreen;
import eu.ldob.alice.actor.AFood;
import eu.ldob.alice.actor.FoodActor;
import eu.ldob.alice.actor.PlayerActor;
import eu.ldob.alice.actor.util.FoodType;
import eu.ldob.alice.evaluation.AEvaluation;
import eu.ldob.alice.evaluation.Mode;
import eu.ldob.alice.evaluation.Benefits;
import eu.ldob.alice.actor.util.FoodCounter;
import eu.ldob.alice.util.SettingsStorage;

public class GameScreen extends AAliceScreen {

    private AliceGame game;

    private Stage stage;
    private Skin skin;

    private Mode mode;
    private Benefits benefits;

    private Music music;

    private Table tbRoot;
    private Table tbHud;

    private PlayerActor player;
    private FoodActor food;

    private FoodCounter counter;

    private float time;
    private int displayTime = -1;
    private int oldDisplayTime = -1;

    private Texture background;
    private Texture backgroundGameOver;

    private Sound soundHealthyHit;
    private Sound soundNeutralHit;
    private Sound soundJunkHit;
    private Sound soundGameOver;

    private long gameOverTime = -1;

    public GameScreen(AliceGame game, Skin skin, Mode mode, Benefits benefits, Music music) {
        this.game = game;
        this.skin = skin;
        this.mode = mode;
        this.benefits = benefits;
        this.music = music;

        this.background = new Texture(Gdx.files.internal(Constants.GAME_BACKGROUND));
        this.backgroundGameOver = new Texture(Gdx.files.internal(Constants.GAME_BACKGROUND_GAMEOVER));

        this.soundHealthyHit = Gdx.audio.newSound(Gdx.files.internal("sound/hit_healthy.wav"));
        this.soundNeutralHit = Gdx.audio.newSound(Gdx.files.internal("sound/hit_neutral.wav"));
        this.soundJunkHit = Gdx.audio.newSound(Gdx.files.internal("sound/hit_junk.wav"));
        this.soundGameOver = Gdx.audio.newSound(Gdx.files.internal("sound/game_over.wav"));
        // TODO sound on jump
    }

    @Override
    public void show() {

        stage = new Stage(new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        tbRoot = new Table();
        tbRoot.setFillParent(true);
        tbRoot.setDebug(Constants.DEBUG);

        tbHud = mode.getEvaluation().getHudTable(skin, benefits);
        tbRoot.add(tbHud).expand().right().top();

        time = 0;
        counter = new FoodCounter();

        player = new PlayerActor(benefits);
        food = new FoodActor(mode, benefits);

        stage.addActor(food);
        stage.addActor(player);
        stage.addActor(tbRoot);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR_BLACK.r, Constants.BACKGROUND_COLOR_BLACK.g, Constants.BACKGROUND_COLOR_BLACK.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(mode.getEvaluation().isGameOver(benefits, time, counter)) {
            if(gameOverTime == -1) {
                gameOverTime = System.currentTimeMillis();

                background = backgroundGameOver;
                player.gameover();

                if(SettingsStorage.isSoundOn()) {
                    music.setVolume(0.02f);
                    soundGameOver.play();
                }

                Table tbReason = new Table();
                for(AEvaluation.GameOverReason reason : mode.getEvaluation().getGameOverReasons(benefits, time, counter)) {
                    tbReason.row().padTop(10);
                    tbReason.add(new Label(reason.getText(), skin)).center().expand();
                }

                Dialog dialog = new Dialog("", skin);
                dialog.add(tbReason);
                dialog.show(stage);
            }
            else if(System.currentTimeMillis() - gameOverTime > 4000) {
                game.showResultScreen(time, counter, mode);
            }
        }
        else {
            player.update(delta);
            food.update(delta);

            time += delta;
            oldDisplayTime = displayTime;
            displayTime = MathUtils.round(time);

            List<AFood> hitFood = player.hitFood(food);
            for (AFood hit : hitFood) {
                counter.add(hit);
                food.removeFood(hit);

                if(SettingsStorage.isSoundOn()){
                    if (hit.getFoodType() == FoodType.HEALTHY) {
                        soundHealthyHit.play(0.5f);
                    } else if (hit.getFoodType() == FoodType.JUNK) {
                        soundJunkHit.play(0.5f);
                    } else {
                        soundNeutralHit.play(0.5f);
                    }
                }
            }


            if(oldDisplayTime == -1 || displayTime != oldDisplayTime || hitFood.size() > 0) {
                mode.getEvaluation().updateHudTable(displayTime, counter);
            }
        }

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        stage.getBatch().end();

        stage.act(delta);
        stage.draw();
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
        disposeAll();
    }

    @Override
    public void dispose() {
        disposeAll();
    }

    private void disposeAll() {
        stage.dispose();

        background.dispose();
        backgroundGameOver.dispose();

        soundHealthyHit.dispose();
        soundNeutralHit.dispose();
        soundJunkHit.dispose();
        soundGameOver.dispose();
    }
}
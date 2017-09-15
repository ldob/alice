package eu.ldob.alice.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

import eu.ldob.alice.AliceGame;
import eu.ldob.alice.Constants;
import eu.ldob.alice.items.AFood;
import eu.ldob.alice.items.FoodActor;
import eu.ldob.alice.items.PlayerActor;
import eu.ldob.alice.mode.Mode;
import eu.ldob.alice.mode.Benefits;
import eu.ldob.alice.items.FoodCounter;

public class GameScreen implements Screen {

    private AliceGame game;

    private Stage stage;
    private Skin skin;

    private Mode mode;
    private Benefits benefits;

    private Table tbHud;

    private PlayerActor player;
    private FoodActor food;

    private FoodCounter counter;
    private float time;

    private Texture background;
    private Texture backgroundGameOver;

    private long gameOverTime = -1;

    public GameScreen(AliceGame game, Skin skin, Mode mode, Benefits benefits) {
        this.game = game;
        this.skin = skin;
        this.mode = mode;
        this.benefits = benefits;

        this.background = new Texture(Gdx.files.internal("background/background.png"));
        this.backgroundGameOver = new Texture(Gdx.files.internal("background/background_gameover.png"));
    }

    @Override
    public void show() {

        stage = new Stage(new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        Table tbRoot = new Table();
        tbRoot.setFillParent(true);
        tbRoot.setDebug(Constants.DEBUG);
        stage.addActor(tbRoot);

        tbHud = mode.getEvaluation().getHudTable(skin, benefits);
        tbRoot.add(tbHud).expand().right().top();

        time = 0;
        counter = new FoodCounter();

        player = new PlayerActor(false, false);
        food = new FoodActor(mode, benefits);
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
            }
            else if(System.currentTimeMillis() - gameOverTime > 3000) {
                game.showResultScreen(time, counter, mode);
            }
        }
        else {
            player.update(delta);
            food.update(delta);

            time += delta;

            for (AFood hit : player.hitFood(food)) {
                counter.add(hit);
                food.removeValue(hit);
            }
        }

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        stage.getBatch().end();

        stage.addActor(food);
        stage.addActor(player);

        mode.getEvaluation().updateHudTable(time, counter);

        stage.act(delta);
        stage.draw();
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
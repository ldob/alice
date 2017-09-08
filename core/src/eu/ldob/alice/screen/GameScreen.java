package eu.ldob.alice.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import eu.ldob.alice.AliceGame;
import eu.ldob.alice.Constants;
import eu.ldob.alice.items.AFood;
import eu.ldob.alice.items.PlayerActor;
import eu.ldob.alice.mode.Mode;
import eu.ldob.alice.mode.Benefits;
import eu.ldob.alice.items.FoodCounter;
import eu.ldob.alice.items.FoodList;
import eu.ldob.alice.items.Player;

public class GameScreen extends InputAdapter implements Screen {

    private AliceGame game;

    private Stage stage;
    private Skin skin;

    private Mode mode;
    private Benefits benefits;

    private Table tbHud;
    private Table tbGame;

    private PlayerActor player;
    private FoodList foodList;

    private FoodCounter counter;
    private float time;

    public GameScreen(AliceGame game, Skin skin, Mode mode, Benefits benefits) {
        this.game = game;
        this.skin = skin;
        this.mode = mode;
        this.benefits = benefits;
    }

    @Override
    public void show() {

        stage = new Stage(new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        Table tbRoot = new Table();
        tbRoot.setFillParent(true);
        tbRoot.setDebug(Constants.DEBUG);
        stage.addActor(tbRoot);

        tbHud = new Table(skin);
        tbGame = new Table(skin);

        tbRoot.add(tbHud).right();
        tbRoot.add(tbGame).expand().left();

        time = 0;
        counter = new FoodCounter();

        player = new PlayerActor(false, false);
        //foodList = new FoodListActor();

        //player = new Player(gameViewport, benefits);
        //foodList = new FoodList(gameViewport, mode, benefits);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {

        //foodList.update(delta);
        player.update(delta);

        time += delta;

        /*
        AFood hit = player.hitFood(foodList);
        if(hit != null) {
            counter.add(hit);
            foodList.removeValue(hit);
        }

        if(mode.getEvaluation().isGameOver(benefits, time, counter)) {
            game.showResultScreen(time, counter, mode);
            return;
        }
        */

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tbHud.clear();
        tbHud.add("hallo");
        tbHud.row();
        tbHud.add("welt");

        tbGame.add(player);

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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        game.showResultScreen(time, counter, mode);
        return true;
    }
}
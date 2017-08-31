package eu.ldob.alice.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import eu.ldob.alice.AliceGame;
import eu.ldob.alice.Constants;
import eu.ldob.alice.mode.Mode;
import eu.ldob.alice.items.AFood;
import eu.ldob.alice.mode.Benefits;
import eu.ldob.alice.items.FoodCounter;
import eu.ldob.alice.items.FoodList;
import eu.ldob.alice.items.Player;

public class GameScreen extends InputAdapter implements Screen {

    private AliceGame game;
    private Mode mode;
    private Benefits benefits;

    private float time;

    private ScreenViewport gameViewport;
    private SpriteBatch gameBatch;

    private ScreenViewport hudViewport;
    private SpriteBatch hudBatch;

    private BitmapFont font;

    private Player player;
    private FoodList foodList;

    private FoodCounter counter;

    public GameScreen(AliceGame game, Mode mode, Benefits benefits) {
        this.game = game;
        this.mode = mode;
        this.benefits = benefits;
    }

    @Override
    public void show() {
        gameViewport = new ScreenViewport();
        gameBatch = new SpriteBatch();

        hudViewport = new ScreenViewport();
        hudBatch = new SpriteBatch();

        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

        player = new Player(gameViewport, benefits);
        foodList = new FoodList(gameViewport, mode, benefits);

        Gdx.input.setInputProcessor(this);

        time = 0;

        counter = new FoodCounter();
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height, true);
        hudViewport.update(width, height, true);
        font.getData().setScale(Math.min(width, height) / Constants.HUD_FONT_REFERENCE_SCREEN_SIZE);
        font.setColor(Constants.LABEL_COLOR);

        player.init();
        foodList.init();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {

        foodList.update(delta);
        player.update(delta);

        time += delta;

        AFood hit = player.hitFood(foodList);
        if(hit != null) {
            counter.add(hit);
            foodList.removeValue(hit);
        }

        if(mode.getEvaluation().isGameOver(benefits, time, counter)) {
            game.showResultScreen(time, counter, mode);
            return;
        }

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameViewport.apply(true);
        gameBatch.setProjectionMatrix(gameViewport.getCamera().combined);

        foodList.draw(gameBatch);
        player.draw(gameBatch);


        hudViewport.apply();
        hudBatch.setProjectionMatrix(hudViewport.getCamera().combined);
        hudBatch.begin();

        final String rightHudText = mode.getEvaluation().getHudText(benefits, time, counter);
        font.getData().markupEnabled = true;
        font.draw(hudBatch, rightHudText, hudViewport.getWorldWidth() - Constants.HUD_MARGIN, hudViewport.getWorldHeight() - Constants.HUD_MARGIN, 0, Align.right, false);

        hudBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        gameBatch.dispose();
        hudBatch.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        game.showResultScreen(time, counter, mode);
        return true;
    }
}
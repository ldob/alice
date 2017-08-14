package eu.ldob.alice.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import eu.ldob.alice.AliceGame;
import eu.ldob.alice.Constants;
import eu.ldob.alice.Level;
import eu.ldob.alice.items.AFood;
import eu.ldob.alice.items.FoodList;
import eu.ldob.alice.items.Player;

public class GameScreen extends InputAdapter implements Screen {

    private AliceGame game;
    private Level level;

    private float time;

    private ExtendViewport gameViewport;
    private ShapeRenderer renderer;

    private ScreenViewport hudViewport;
    private SpriteBatch batch;
    private BitmapFont font;

    private Player player;
    private FoodList foodList;

    private int caloricValueDodged;
    private int vitaminADodged;

    public GameScreen(AliceGame game, Level level) {
        this.game = game;
        this.level = level;
    }

    @Override
    public void show() {
        gameViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);

        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

        hudViewport = new ScreenViewport();
        batch = new SpriteBatch();

        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

        player = new Player(gameViewport);
        foodList = new FoodList(gameViewport, level);

        Gdx.input.setInputProcessor(this);

        time = 0;

        caloricValueDodged = 0;
        vitaminADodged = 0;
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height, true);
        hudViewport.update(width, height, true);
        font.getData().setScale(Math.min(width, height) / Constants.HUD_FONT_REFERENCE_SCREEN_SIZE);

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
            caloricValueDodged += hit.getCaloricValue();
            vitaminADodged += hit.getVitaminA();
            foodList.removeValue(hit);
        }

        gameViewport.apply(true);
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(gameViewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        foodList.render(renderer);
        player.render(renderer);
        renderer.end();

        hudViewport.apply();
        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.begin();

        final String rightHudText = Constants.SCORE_TIME_LABEL + MathUtils.round(time) + Constants.SCORE_TIME_UNIT + "\n" + Constants.SCORE_CALORIC_VALUE_LABEL + caloricValueDodged + Constants.SCORE_CALORIC_VALUE_UNIT + "\n" + Constants.SCORE_VITAMIN_A_LABEL + vitaminADodged + Constants.SCORE_VITAMIN_A_UNIT;

        font.draw(batch, rightHudText, hudViewport.getWorldWidth() - Constants.HUD_MARGIN, hudViewport.getWorldHeight() - Constants.HUD_MARGIN, 0, Align.right, false);

        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        renderer.dispose();
        batch.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        game.showHomeScreen();
        return true;
    }
}
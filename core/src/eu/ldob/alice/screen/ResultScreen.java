package eu.ldob.alice.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import eu.ldob.alice.AliceGame;
import eu.ldob.alice.Constants;
import eu.ldob.alice.Level;
import eu.ldob.alice.items.AFood;
import eu.ldob.alice.Benefits;
import eu.ldob.alice.items.FoodCounter;

public class ResultScreen extends InputAdapter implements Screen {

    private AliceGame game;
    private Level level;
    private Benefits benefits;

    private ShapeRenderer renderer;
    private SpriteBatch batch;
    private FitViewport viewport;

    private BitmapFont font;

    private FoodCounter counter;

    public ResultScreen(AliceGame game, FoodCounter counter, Level level, Benefits benefits) {
        this.game = game;
        this.level = level;
        this.benefits = benefits;
        this.counter = counter;
    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();

        viewport = new FitViewport(Constants.HOME_WORLD_SIZE, Constants.HOME_WORLD_SIZE);
        Gdx.input.setInputProcessor(this);

        font = new BitmapFont();
        font.getData().setScale(Constants.LABEL_SCALE_MEDIUM);
        font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        String text = "";

        for(AFood food : counter.getFoodList()) {
            text += counter.getFoodCount(food) + "x " + food.getName() + "\n";
        }

        batch.begin();

        final GlyphLayout layout = new GlyphLayout(font, text);
        font.draw(batch, text, Constants.LEFT_CENTER.x, Constants.LEFT_CENTER.y + layout.height / 2, 0, Align.center, false);

        batch.end();

        /*
        final String rightHudText =
                Constants.SCORE_TIME_LABEL + MathUtils.round(time) + Constants.SCORE_TIME_UNIT + "\n" +
                Constants.SCORE_CALORIC_VALUE_LABEL + totalNutritionFacts.getCaloricValue() + Constants.SCORE_CALORIC_VALUE_UNIT + "\n" +
                Constants.SCORE_CARBS_LABEL + totalNutritionFacts.getCarbs() + Constants.SCORE_CARBS_UNIT + "\n" +
                Constants.SCORE_FAT_LABEL + totalNutritionFacts.getFat() + Constants.SCORE_FAT_UNIT + "\n" +
                Constants.SCORE_PROTEINS_LABEL + totalNutritionFacts.getProteins() + Constants.SCORE_PROTEINS_UNIT + "\n" +
                Constants.SCORE_VITAMIN_A_LABEL + totalNutritionFacts.getVitaminA() + Constants.SCORE_VITAMIN_A_UNIT + "\n" +
                Constants.SCORE_VITAMIN_C_LABEL + totalNutritionFacts.getVitaminC() + Constants.SCORE_VITAMIN_C_UNIT + "\n" +
                Constants.SCORE_CALCIUM_LABEL + totalNutritionFacts.getCalcium() + Constants.SCORE_CALCIUM_UNIT + "\n" +
                Constants.SCORE_IRON_LABEL + totalNutritionFacts.getIron() + Constants.SCORE_IRON_UNIT;
        */
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        batch.dispose();
        font.dispose();
        renderer.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        game.showHomeScreen();
        return true;
    }
}
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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import eu.ldob.alice.AliceGame;
import eu.ldob.alice.Constants;
import eu.ldob.alice.Benefits;
import eu.ldob.alice.mode.Mode;


public class ModeScreen extends InputAdapter implements Screen {

    private AliceGame game;
    private Benefits benefits;

    private ShapeRenderer renderer;
    private SpriteBatch batch;
    private FitViewport viewport;

    private BitmapFont font;

    public ModeScreen(AliceGame game, Benefits benefits) {
        this.game = game;
        this.benefits = benefits;
    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();

        viewport = new FitViewport(Constants.HOME_WORLD_SIZE, Constants.HOME_WORLD_SIZE);
        Gdx.input.setInputProcessor(this);

        font = new BitmapFont();
        font.getData().setScale(Constants.LABEL_SCALE_SMALL);
        font.setColor(Constants.LABEL_COLOR);
        font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(Constants.PLAY_COLOR);
        renderer.circle(Constants.LEFT_TOP.x, Constants.LEFT_TOP.y, Constants.BUTTON_BUBBLE_RADIUS);

        renderer.setColor(Constants.PLAY_COLOR);
        renderer.circle(Constants.CENTER_TOP.x, Constants.CENTER_TOP.y, Constants.BUTTON_BUBBLE_RADIUS);

        renderer.setColor(Constants.PLAY_COLOR);
        renderer.circle(Constants.RIGHT_TOP.x, Constants.RIGHT_TOP.y, Constants.BUTTON_BUBBLE_RADIUS);

        /*

        renderer.setColor(benefits.isBenefitMoreCaloricValue() ? Constants.BENEFIT_SET_COLOR : Constants.BENEFIT_UNSET_COLOR);
        renderer.circle(Constants.LEFT_BOTTOM.x, Constants.LEFT_BOTTOM.y, Constants.BUTTON_BUBBLE_RADIUS);

        renderer.setColor(benefits.isBenefitMoreHealthyFood() ? Constants.BENEFIT_SET_COLOR : Constants.BENEFIT_UNSET_COLOR);
        renderer.circle(Constants.CENTER_BOTTOM.x, Constants.CENTER_BOTTOM.y, Constants.BUTTON_BUBBLE_RADIUS);

        renderer.setColor(benefits.isBenefitLessJunkFood() ? Constants.BENEFIT_SET_COLOR : Constants.BENEFIT_UNSET_COLOR);
        renderer.circle(Constants.RIGHT_BOTTOM.x, Constants.RIGHT_BOTTOM.y, Constants.BUTTON_BUBBLE_RADIUS);

        */

        renderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();

        String label;

        label = Mode.TIME.getName();
        final GlyphLayout fastLayout = new GlyphLayout(font, label);
        font.draw(batch, label, Constants.LEFT_TOP.x, Constants.LEFT_TOP.y + fastLayout.height / 2, 0, Align.center, false);

        label = Mode.COLLECT_VITAMINS.getName();
        final GlyphLayout bigLayout = new GlyphLayout(font, label);
        font.draw(batch, label, Constants.CENTER_TOP.x, Constants.CENTER_TOP.y + bigLayout.height / 2, 0, Align.center, false);

        label = Mode.AVOID_FAT.getName();
        final GlyphLayout persistentLayout = new GlyphLayout(font, label);
        font.draw(batch, label, Constants.RIGHT_TOP.x, Constants.RIGHT_TOP.y + persistentLayout.height / 2, 0, Align.center, false);

        /*

        final GlyphLayout caloricLayout = new GlyphLayout(font, Constants.CALORIC_LABEL);
        font.draw(batch, Constants.CALORIC_LABEL, Constants.LEFT_BOTTOM.x, Constants.LEFT_BOTTOM.y + caloricLayout.height / 2, 0, Align.center, false);

        final GlyphLayout healthyLayout = new GlyphLayout(font, Constants.HEALTHY_LABEL);
        font.draw(batch, Constants.HEALTHY_LABEL, Constants.CENTER_BOTTOM.x, Constants.CENTER_BOTTOM.y + healthyLayout.height / 2, 0, Align.center, false);

        final GlyphLayout junkLayout = new GlyphLayout(font, Constants.JUNK_LABEL);
        font.draw(batch, Constants.JUNK_LABEL, Constants.RIGHT_BOTTOM.x, Constants.RIGHT_BOTTOM.y + junkLayout.height / 2, 0, Align.center, false);

        */

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
    public void dispose() {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

        if (worldTouch.dst(Constants.LEFT_TOP) < Constants.BUTTON_BUBBLE_RADIUS) {
            game.showGameScreen(Mode.TIME);
        }
        else if (worldTouch.dst(Constants.CENTER_TOP) < Constants.BUTTON_BUBBLE_RADIUS) {
            game.showGameScreen(Mode.COLLECT_VITAMINS);
        }
        else if (worldTouch.dst(Constants.RIGHT_TOP) < Constants.BUTTON_BUBBLE_RADIUS) {
            game.showGameScreen(Mode.AVOID_FAT);
        }

        /*

        else if (worldTouch.dst(Constants.LEFT_BOTTOM) < Constants.BUTTON_BUBBLE_RADIUS) {
            benefits.setBenefitMoreCaloricValue(!benefits.isBenefitMoreCaloricValue());
        }
        else if (worldTouch.dst(Constants.CENTER_BOTTOM) < Constants.BUTTON_BUBBLE_RADIUS) {
            benefits.setBenefitMoreHealthyFood(!benefits.isBenefitMoreHealthyFood());
        }
        else if (worldTouch.dst(Constants.RIGHT_BOTTOM) < Constants.BUTTON_BUBBLE_RADIUS) {
            benefits.setBenefitLessJunkFood(!benefits.isBenefitLessJunkFood());
        }

        */

        else {
            game.showHomeScreen();
        }

        return true;
    }
}
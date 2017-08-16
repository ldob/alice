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
import eu.ldob.alice.Level;


public class HomeScreen  extends InputAdapter implements Screen {

    private AliceGame game;

    private ShapeRenderer renderer;
    private SpriteBatch batch;
    private FitViewport viewport;

    private BitmapFont font;

    public HomeScreen(AliceGame game) {
        this.game = game;
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
    public void render(float delta) {
        viewport.apply();
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(Constants.PLAY_COLOR);
        renderer.circle(Constants.LEFT_CENTER.x, Constants.LEFT_CENTER.y, Constants.BUTTON_BUBBLE_RADIUS);

        renderer.setColor(Constants.BENEFITS_COLOR);
        renderer.circle(Constants.CENTER_CENTER.x, Constants.CENTER_CENTER.y, Constants.BUTTON_BUBBLE_RADIUS);

        renderer.setColor(Constants.SETTINGS_COLOR);
        renderer.circle(Constants.RIGHT_CENTER.x, Constants.RIGHT_CENTER.y, Constants.BUTTON_BUBBLE_RADIUS);

        renderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();

        final GlyphLayout playLayout = new GlyphLayout(font, Constants.PLAY_LABEL);
        font.draw(batch, Constants.PLAY_LABEL, Constants.LEFT_CENTER.x, Constants.LEFT_CENTER.y + playLayout.height / 2, 0, Align.center, false);

        final GlyphLayout benefitsLayout = new GlyphLayout(font, Constants.BENEFITS_LABEL);
        font.draw(batch, Constants.BENEFITS_LABEL, Constants.CENTER_CENTER.x, Constants.CENTER_CENTER.y + benefitsLayout.height / 2, 0, Align.center, false);

        final GlyphLayout settingsLayout = new GlyphLayout(font, Constants.SETTINGS_LABEL);
        font.draw(batch, Constants.SETTINGS_LABEL, Constants.RIGHT_CENTER.x, Constants.RIGHT_CENTER.y + settingsLayout.height / 2, 0, Align.center, false);

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

        if (worldTouch.dst(Constants.LEFT_CENTER) < Constants.BUTTON_BUBBLE_RADIUS) {
            game.showGameScreen(Level.HARD);
        }

        if (worldTouch.dst(Constants.CENTER_CENTER) < Constants.BUTTON_BUBBLE_RADIUS) {
            game.showBenefitsScreen();
        }

        if (worldTouch.dst(Constants.RIGHT_CENTER) < Constants.BUTTON_BUBBLE_RADIUS) {
            // TODO
        }

        return true;
    }
}
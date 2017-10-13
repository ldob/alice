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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import eu.ldob.alice.AliceGame;
import eu.ldob.alice.Constants;
import eu.ldob.alice.AAliceScreen;
import eu.ldob.alice.evaluation.Benefits;


public class BenefitsScreen extends AAliceScreen {

    private Stage stage;
    private Skin skin;

    private AliceGame game;
    private Benefits benefits;

    public BenefitsScreen(AliceGame game, Skin skin, Benefits benefits) {
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

        final Label lbHead = new Label(Constants.BENEFITS_LABEL, skin, "title");
        tbRoot.add(lbHead).expand().top().padTop(30);

        Table tbBenefits = new Table();
        tbBenefits.setFillParent(true);
        tbBenefits.setDebug(Constants.DEBUG);
        tbRoot.addActor(tbBenefits);

        final Label lbDescription = new Label(Constants.BENEFITS_DESCRIPTION, skin);

        final Label lbBig = new Label(Constants.BIG_LABEL, skin);
        final CheckBox cbBig = new CheckBox("", skin);
        cbBig.setChecked(benefits.isBenefitBiggerPlayer());

        final Label lbPersistent = new Label(Constants.PERSISTENT_LABEL, skin);
        final CheckBox cbPersistent = new CheckBox("", skin);
        cbPersistent.setChecked(benefits.isBenefitLongGameTime());

        final Label lbCaloric = new Label(Constants.CALORIC_LABEL, skin);
        final CheckBox cbCaloric = new CheckBox("", skin);
        cbCaloric.setChecked(benefits.isBenefitMoreCaloricValue());

        final Label lbCoordination = new Label(Constants.COORDINATION_LABEL, skin);
        final CheckBox cbCoordination = new CheckBox("", skin);
        cbCoordination.setChecked(benefits.isBenefitJumpHigh());

        final Label lbHealthy = new Label(Constants.HEALTHY_LABEL, skin);
        final CheckBox cbHealthy = new CheckBox("", skin);
        cbHealthy.setChecked(benefits.isBenefitMoreHealthyFood());

        final Label lbJunk = new Label(Constants.JUNK_LABEL, skin);
        final CheckBox cbJunk = new CheckBox("", skin);
        cbJunk.setChecked(benefits.isBenefitLessJunkFood());

        final TextButton btBack = new TextButton(Constants.BACK_LABEL, skin);
        final TextButton btSave = new TextButton(Constants.SAVE_LABEL, skin);

        tbBenefits.add(lbDescription).fillX().colspan(2);

        tbBenefits.row().padTop(40);
        tbBenefits.add(lbBig).left();
        tbBenefits.add(cbBig);
        tbBenefits.row().padTop(25);
        tbBenefits.add(lbPersistent).left();
        tbBenefits.add(cbPersistent);
        tbBenefits.row().padTop(25);
        tbBenefits.add(lbCaloric).left();
        tbBenefits.add(cbCaloric);
        tbBenefits.row().padTop(25);
        tbBenefits.add(lbCoordination).left();
        tbBenefits.add(cbCoordination);
        tbBenefits.row().padTop(25);
        tbBenefits.add(lbHealthy).left();
        tbBenefits.add(cbHealthy);
        tbBenefits.row().padTop(25);
        tbBenefits.add(lbJunk).left();
        tbBenefits.add(cbJunk);

        tbBenefits.row().padTop(40);
        tbBenefits.add(btBack).left();
        tbBenefits.add(btSave).right();

        btBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.showHomeScreen();
            }
        });

        btSave.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                benefits.setBenefitBiggerPlayer(cbBig.isChecked());
                benefits.setBenefitLongGameTime(cbPersistent.isChecked());
                benefits.setBenefitMoreCaloricValue(cbCaloric.isChecked());
                benefits.setBenefitJumpHigh(cbCoordination.isChecked());
                benefits.setBenefitMoreHealthyFood(cbHealthy.isChecked());
                benefits.setBenefitLessJunkFood(cbJunk.isChecked());

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
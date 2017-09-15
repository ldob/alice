package eu.ldob.alice;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import eu.ldob.alice.items.FoodCounter;
import eu.ldob.alice.mode.Benefits;
import eu.ldob.alice.mode.Mode;
import eu.ldob.alice.screen.BenefitsScreen;
import eu.ldob.alice.screen.GameScreen;
import eu.ldob.alice.screen.HighscoreScreen;
import eu.ldob.alice.screen.HomeScreen;
import eu.ldob.alice.screen.ModeScreen;
import eu.ldob.alice.screen.ResultScreen;

public class AliceGame extends Game {

	private Skin skin;

	private eu.ldob.alice.mode.Benefits benefits;

	@Override
	public void create() {
		this.skin = new Skin(Gdx.files.internal(Constants.SKIN));
		this.benefits = new Benefits();

		showHomeScreen();
	}

	public void showHomeScreen() {
		setScreen(new HomeScreen(this, skin));
	}

	public void showBenefitsScreen() {
		setScreen(new BenefitsScreen(this, skin, benefits));
	}

	public void showModeScreen() {
		setScreen(new ModeScreen(this, skin, benefits));
	}

	public void showGameScreen(Mode mode) {
		setScreen(new GameScreen(this, skin, mode, benefits));
		//setScreen(new GameScreenOld(this, mode, benefits));
	}

	public void showResultScreen(float time, FoodCounter counter, Mode mode) {
		setScreen(new ResultScreen(this, skin, time, counter, mode, benefits));
	}

	public void showHighscoreScreen(ResultScreen resultScreen, Mode mode) {
		setScreen(new HighscoreScreen(this, skin, mode, resultScreen));
	}

	public void showScreen(Screen screen) {
		setScreen(screen);
	}
}
package eu.ldob.alice;

import com.badlogic.gdx.Game;

import eu.ldob.alice.items.FoodCounter;
import eu.ldob.alice.screen.BenefitsScreen;
import eu.ldob.alice.screen.GameScreen;
import eu.ldob.alice.screen.HomeScreen;
import eu.ldob.alice.screen.ModeScreen;
import eu.ldob.alice.screen.ResultScreen;

public class AliceGame extends Game {

	private Benefits benefits;

	@Override
	public void create() {
		this.benefits = new Benefits();

		showHomeScreen();
	}

	public void showHomeScreen() {
		setScreen(new HomeScreen(this));
	}

	public void showBenefitsScreen() {
		setScreen(new BenefitsScreen(this, benefits));
	}

	public void showModeScreen() {
		setScreen(new ModeScreen(this, benefits));
	}

	public void showGameScreen(eu.ldob.alice.mode.Mode mode) {
		setScreen(new GameScreen(this, mode, benefits));
	}

	public void showResultScreen(FoodCounter counter, eu.ldob.alice.mode.Mode mode) {
		setScreen(new ResultScreen(this, counter, mode, benefits));
	}
}
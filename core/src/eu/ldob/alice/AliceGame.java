package eu.ldob.alice;

import com.badlogic.gdx.Game;

import eu.ldob.alice.items.FoodCounter;
import eu.ldob.alice.screen.BenefitsScreen;
import eu.ldob.alice.screen.GameScreen;
import eu.ldob.alice.screen.HomeScreen;
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

	public void showGameScreen(Level level) {
		setScreen(new GameScreen(this, level, benefits));
	}

	public void showResultScreen(FoodCounter counter, Level level) {
		setScreen(new ResultScreen(this, counter, level, benefits));
	}
}
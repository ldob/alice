package eu.ldob.alice;

import com.badlogic.gdx.Game;

import eu.ldob.alice.screen.GameScreen;
import eu.ldob.alice.screen.HomeScreen;

public class AliceGame extends Game {

	@Override
	public void create() {
		showHomeScreen();
	}

	public void showHomeScreen() {
		setScreen(new HomeScreen(this));
	}

	public void showGameScreen(Level level) {
		setScreen(new GameScreen(this, level));
	}
}
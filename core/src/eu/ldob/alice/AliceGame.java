package eu.ldob.alice;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import eu.ldob.alice.items.FoodCounter;
import eu.ldob.alice.mode.Benefits;
import eu.ldob.alice.mode.Mode;
import eu.ldob.alice.screen.BenefitsScreen;
import eu.ldob.alice.screen.GameScreen;
import eu.ldob.alice.screen.HighscoreScreen;
import eu.ldob.alice.screen.HomeScreen;
import eu.ldob.alice.screen.LoadingScreen;
import eu.ldob.alice.screen.ModeScreen;
import eu.ldob.alice.screen.ResultScreen;
import eu.ldob.alice.screen.SettingsScreen;

public class AliceGame extends Game {

	private Skin skin;

	private Music musicMenu;
	private Music musicGame;
	private Music musicResult;

	private Benefits benefits;

	@Override
	public void create() {

		skin = new Skin(Gdx.files.internal(Constants.SKIN));
		musicMenu = Gdx.audio.newMusic(Gdx.files.internal("music/menu.mp3"));
		musicGame = Gdx.audio.newMusic(Gdx.files.internal("music/game.mp3"));
		musicResult = Gdx.audio.newMusic(Gdx.files.internal("music/result.mp3"));

		musicMenu.setVolume(0.05f);
		musicGame.setVolume(0.08f);
		musicResult.setVolume(0.05f);

		benefits = new Benefits();

		showHomeScreen();
	}

	public void showHomeScreen() {
		musicGame.stop();
		musicResult.stop();
		if(!musicMenu.isPlaying()) {
			musicMenu.play();
		}
		setScreen(new HomeScreen(this, skin));
	}

	public void showBenefitsScreen() {
		setScreen(new BenefitsScreen(this, skin, benefits));
	}

	public void showModeScreen() {
		setScreen(new ModeScreen(this, skin, benefits));
	}

	public void showGameScreen(Mode mode) {
		musicMenu.stop();
		musicResult.stop();
		if(!musicGame.isPlaying()) {
			musicGame.play();
		}
		setScreen(new GameScreen(this, skin, mode, benefits, musicGame));
	}

	public void showResultScreen(float time, FoodCounter counter, Mode mode) {
		musicMenu.stop();
		musicGame.stop();
		if(!musicResult.isPlaying()) {
			musicResult.play();
		}
		setScreen(new ResultScreen(this, skin, time, counter, mode, benefits));
	}

	public void showHighscoreScreen(ResultScreen resultScreen, Mode mode) {
		setScreen(new HighscoreScreen(this, skin, mode, resultScreen));
	}

	public void showSettingsScreen() {
		setScreen(new SettingsScreen(this, skin));
	}

	public void showLoadingScreen() {
		setScreen(new LoadingScreen(this));
	}

	public void showScreen(Screen screen) {
		setScreen(screen);
	}
}
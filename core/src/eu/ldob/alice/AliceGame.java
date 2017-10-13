package eu.ldob.alice;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import eu.ldob.alice.items.util.FoodCounter;
import eu.ldob.alice.evaluation.Benefits;
import eu.ldob.alice.evaluation.Mode;
import eu.ldob.alice.screen.BenefitsScreen;
import eu.ldob.alice.screen.GameScreen;
import eu.ldob.alice.screen.HomeScreen;
import eu.ldob.alice.screen.LoadingScreen;
import eu.ldob.alice.screen.ModeScreen;
import eu.ldob.alice.screen.ResultScreen;
import eu.ldob.alice.screen.SettingsScreen;
import eu.ldob.alice.util.SettingsStorage;

public class AliceGame extends Game {

	private Skin skin;

	private Music musicMenu;
	private Music musicGame;
	private Music musicResult;

	private Benefits benefits;

	@Override
	public void create() {

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Regular.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

		parameter.size = 32;
		BitmapFont roboto32 = generator.generateFont(parameter);

		parameter.size = 24;
		BitmapFont roboto24 = generator.generateFont(parameter);

		generator.dispose();

		skin = new Skin();
		skin.add("roboto32", roboto32);
		skin.add("roboto24", roboto24);
		skin.addRegions(new TextureAtlas(Gdx.files.internal(Constants.SKIN_ATLAS)));
		skin.load(Gdx.files.internal(Constants.SKIN_JSON));

		musicMenu = Gdx.audio.newMusic(Gdx.files.internal("music/menu.mp3"));
		musicGame = Gdx.audio.newMusic(Gdx.files.internal("music/game.mp3"));
		musicResult = Gdx.audio.newMusic(Gdx.files.internal("music/result.mp3"));

		musicMenu.setVolume(0.05f);
		musicGame.setVolume(0.08f);
		musicResult.setVolume(0.05f);

		musicMenu.setLooping(true);
		musicGame.setLooping(true);
		musicResult.setLooping(true);

		benefits = new Benefits();

		showHomeScreen();
	}

	public void showHomeScreen() {
		musicGame.stop();
		musicResult.stop();
		if(!musicMenu.isPlaying() && SettingsStorage.isSoundOn()) {
			musicMenu.play();
		}
		else if(musicMenu.isPlaying() && !SettingsStorage.isSoundOn()) {
			musicMenu.stop();
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
		if(!musicGame.isPlaying() && SettingsStorage.isSoundOn()) {
			musicGame.play();
		}
		setScreen(new GameScreen(this, skin, mode, benefits, musicGame));
	}

	public void showResultScreen(float time, FoodCounter counter, Mode mode) {
		musicMenu.stop();
		musicGame.stop();
		if(!musicResult.isPlaying() && SettingsStorage.isSoundOn()) {
			musicResult.play();
		}
		setScreen(new ResultScreen(this, skin, time, counter, mode, benefits));
	}

	public void showSettingsScreen() {
		setScreen(new SettingsScreen(this, skin));
	}

	public void showLoadingScreen() {
		setScreen(new LoadingScreen(this));
	}

	public void showScreen(AAliceScreen screen) {
		setScreen(screen);
	}

	public void setScreen(AAliceScreen screen) {
		super.setScreen(screen);
	}

	private Music pausedMusic = null;

    public void pauseMusic() {
        if(musicMenu.isPlaying()) {
            pausedMusic = musicMenu;
            musicResult.pause();
        }
        else if(musicGame.isPlaying()) {
            pausedMusic = musicGame;
            musicGame.pause();
        }
        else if(musicResult.isPlaying()) {
            pausedMusic = musicResult;
            musicResult.pause();
        }
    }

    public void resumeMusic() {
        if(pausedMusic != null) {
            pausedMusic.play();
            pausedMusic = null;
        }
    }
}
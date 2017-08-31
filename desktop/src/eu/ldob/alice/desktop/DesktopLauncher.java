package eu.ldob.alice.desktop;

import com.badlogic.gdx.Files.*;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import eu.ldob.alice.AliceGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.addIcon("icon/ic_launcher-265.png", FileType.Internal);
		config.addIcon("icon/ic_launcher-64.png", FileType.Internal);
		config.addIcon("icon/ic_launcher-32.png", FileType.Internal);
		//config.fullscreen = true;
		//config.height = 800;
		//config.width = 1200;
		config.height = 500;
		config.width = 800;
		config.title = "ALICE";

		new LwjglApplication(new AliceGame(), config);
	}
}

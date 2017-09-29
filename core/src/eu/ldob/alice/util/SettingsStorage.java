package eu.ldob.alice.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class SettingsStorage {

    private static Preferences prefs = Gdx.app.getPreferences("settings");

    private static String playerNameCache;
    private static String soundOnCache;

    public static void savePlayerName(String name) {
        prefs.putString("name", name);
        prefs.flush();

        playerNameCache = name;
    }
    public static String getPlayerName() {
        if(playerNameCache == null) {
            playerNameCache = prefs.getString("name");
        }

        return playerNameCache.equals("") ? "SomeAnonymousDude" : playerNameCache;
    }

    public static void saveSoundOn(boolean on) {
        prefs.putString("soundOn", String.valueOf(on));
        prefs.flush();

        soundOnCache = String.valueOf(on);
    }
    public static boolean isSoundOn() {
        if(soundOnCache == null) {
            soundOnCache = prefs.getString("soundOn");
        }

        return soundOnCache.toLowerCase().equals("true");
    }
}

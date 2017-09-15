package eu.ldob.alice.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class StoreUtil {

    private static Preferences prefs = Gdx.app.getPreferences("settings");

    public static void savePlayerName(String name) {
        prefs.putString("name", name);
        prefs.flush();
    }
    public static String getPlayerName() {
        String value = prefs.getString("name");
        return value.equals("") ? value : "SomeAnonymousDude";
    }

    public static void saveSoundOn(boolean on) {
        prefs.putBoolean("soundOn", on);
        prefs.flush();
    }
    public static boolean isSoundOn() {
        return prefs.getBoolean("soundOn");
    }
}

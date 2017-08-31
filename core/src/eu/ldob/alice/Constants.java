package eu.ldob.alice;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {

    public static final float WORLD_SIZE = 12.0f;
    public static final boolean DEBUG = true;

    public static final Color BACKGROUND_COLOR = new Color(1f, 1f, 1f, 1);
    public static final Color LABEL_COLOR = Color.BLACK;

    //public static final String SKIN = "skins/glassy/glassy-ui.json";
    public static final String SKIN = "skins/android/uiskin.json";

    public static final String ERROR_HEX_COLOR = "[#B71C1C]";
    public static final String WARN_HEX_COLOR = "[#F57F17]";
    //public static final String DEFAULT_HEX_COLOR = "[#212121]";
    public static final String DEFAULT_HEX_COLOR = "[#33691E]";

    public static final float PLAYER_HEAD_RADIUS = 0.5f;
    public static final float PLAYER_HEAD_HEIGHT = 4.0f * PLAYER_HEAD_RADIUS;
    public static final float PLAYER_LIMB_WIDTH = 0.1f;
    public static final int PLAYER_HEAD_SEGMENTS = 20;
    public static final Color PLAYER_COLOR = Color.BLACK;

    public static final float ACCELEROMETER_SENSITIVITY = 0.4f;
    public static final float GRAVITATIONAL_ACCELERATION = 9.8f;

    public static final float HUD_FONT_REFERENCE_SCREEN_SIZE = 480.0f;
    public static final float HUD_MARGIN = 20.0f;

    public static final String HOME_LABEL = "ALICE";
    public static final String PLAY_LABEL = "Los!";
    public static final String BENEFITS_LABEL = "Leistungen";
    public static final String MODE_LABEL = "Spielmodus";
    public static final String RESULT_LABEL = "Ergebnis";
    public static final String SETTINGS_LABEL = "Einstellungen";
    public static final String EXIT_LABEL = "Beenden";

    public static final String SAVE_LABEL = "Speichern";
    public static final String BACK_LABEL = "Zurück";
    public static final String AGAIN_LABEL = "Nochmal";

    public static final String BENEFITS_DESCRIPTION = "Was hast du in den letzten 24 Stunden gemacht?";

    public static final String FAST_LABEL = "> 5km Laufen";
    public static final String BIG_LABEL = "> 1h Krafttraining";
    public static final String PERSISTENT_LABEL = "> 45min Ausdauertraining";
    public static final String CALORIC_LABEL = "> 15.000 Schritte";
    public static final String HEALTHY_LABEL = "Gesunde Ernährung";
    public static final String JUNK_LABEL = "Kein Junk-Food";

    public static final String MODE_TIME_LABEL = "Spiel auf Zeit";
    public static final String MODE_VITAMINS_LABEL = "Hol dir Vitamine";
    public static final String MODE_FAT_LABEL = "Flucht vor Fett";

    public static final float HOME_WORLD_SIZE = 600.0f;
    public static final float BUTTON_BUBBLE_RADIUS = HOME_WORLD_SIZE / 9;

    public static final float LABEL_SCALE_SMALL = 0.8f;
    public static final float LABEL_SCALE_MEDIUM = 1.2f;
    public static final float LABEL_SCALE_LARGE = 1.6f;

    public static final String SCORE_TIME_LABEL = "Zeit: ";
    public static final String SCORE_TIME_UNIT = " sek";
    public static final String SCORE_CALORIC_VALUE_LABEL = "Brennwert: ";
    public static final String SCORE_CALORIC_VALUE_UNIT = " kcal";
    public static final String SCORE_CARBS_LABEL = "Kohlenhydrate: ";
    public static final String SCORE_CARBS_UNIT = " g";
    public static final String SCORE_FAT_LABEL = "Fett: ";
    public static final String SCORE_FAT_UNIT = " g";
    public static final String SCORE_PROTEINS_LABEL = "Proteine: ";
    public static final String SCORE_PROTEINS_UNIT = " g";
    public static final String SCORE_VITAMIN_A_LABEL = "Vitamin A: ";
    public static final String SCORE_VITAMIN_A_UNIT = " ug";
    public static final String SCORE_VITAMIN_C_LABEL = "Vitamin C: ";
    public static final String SCORE_VITAMIN_C_UNIT = " mg";
    public static final String SCORE_CALCIUM_LABEL = "Calcium: ";
    public static final String SCORE_CALCIUM_UNIT = " mg";
    public static final String SCORE_IRON_LABEL = "Eisen: ";
    public static final String SCORE_IRON_UNIT = " ug";

    public static final String TOTAL_LABEL = "Gesamt";
    public static final String SCORE_LABEL = "Score";

    public static final int INITIAL_FOOD_ARRAY_CAPACITY = 100;




    public static final int PLAYER_SIZE = 256;
    public static final float VELOCITY_SCALE = 7;
}

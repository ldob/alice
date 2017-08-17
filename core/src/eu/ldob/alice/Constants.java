package eu.ldob.alice;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {

    public static final float WORLD_SIZE = 10.0f;

    public static final Color BACKGROUND_COLOR = new Color(0.773f, 0.882f, 0.647f, 1);
    public static final Color LABEL_COLOR = Color.BLACK;

    public static final Color PLAY_COLOR = new Color(0.22f, 0.557f, 0.235f, 0.8f);
    public static final Color BENEFITS_COLOR = new Color(0.333f, 0.545f, 0.184f, 1);
    public static final Color SETTINGS_COLOR = new Color(0.106f, 0.369f, 0.125f, 1);

    public static final Color BENEFIT_SET_COLOR = new Color(0.22f, 0.557f, 0.235f, 1);
    public static final Color BENEFIT_UNSET_COLOR = new Color(0.937f, 0.325f, 0.314f, 1);

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

    public static final String PLAY_LABEL = "Los!";
    public static final String BENEFITS_LABEL = "Leistungen";
    public static final String SETTINGS_LABEL = "Einstellungen";

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

    public static final Vector2 LEFT_CENTER = new Vector2(HOME_WORLD_SIZE / 4, HOME_WORLD_SIZE / 2);
    public static final Vector2 CENTER_CENTER = new Vector2(HOME_WORLD_SIZE / 2, HOME_WORLD_SIZE / 2);
    public static final Vector2 RIGHT_CENTER = new Vector2(HOME_WORLD_SIZE * 3 / 4, HOME_WORLD_SIZE / 2);

    public static final Vector2 LEFT_BOTTOM = new Vector2(HOME_WORLD_SIZE / 4, HOME_WORLD_SIZE / 3);
    public static final Vector2 CENTER_BOTTOM = new Vector2(HOME_WORLD_SIZE / 2, HOME_WORLD_SIZE / 3);
    public static final Vector2 RIGHT_BOTTOM = new Vector2(HOME_WORLD_SIZE * 3 / 4, HOME_WORLD_SIZE / 3);

    public static final Vector2 LEFT_TOP = new Vector2(HOME_WORLD_SIZE / 4, HOME_WORLD_SIZE * 2 / 3);
    public static final Vector2 CENTER_TOP = new Vector2(HOME_WORLD_SIZE / 2, HOME_WORLD_SIZE * 2 / 3);
    public static final Vector2 RIGHT_TOP = new Vector2(HOME_WORLD_SIZE * 3 / 4, HOME_WORLD_SIZE * 2 / 3);

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

    public static final int INITIAL_FOOD_ARRAY_CAPACITY = 100;

}

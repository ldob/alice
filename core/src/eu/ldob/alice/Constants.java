package eu.ldob.alice;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {

    public static final float WORLD_SIZE = 10.0f;

    public static final Color BACKGROUND_COLOR = Color.BLUE;

    public static final float PLAYER_HEAD_RADIUS = 0.5f;
    public static final float PLAYER_HEAD_HEIGHT = 4.0f * PLAYER_HEAD_RADIUS;
    public static final float PLAYER_LIMB_WIDTH = 0.1f;
    public static final int PLAYER_HEAD_SEGMENTS = 20;
    public static final Color PLAYER_COLOR = Color.BLACK;
    public static final float PLAYER_MOVEMENT_SPEED = 10.0f;

    public static final float ACCELEROMETER_SENSITIVITY = 0.7f;
    public static final float GRAVITATIONAL_ACCELERATION = 9.8f;



    public static final float HUD_FONT_REFERENCE_SCREEN_SIZE = 480.0f;
    public static final float HUD_MARGIN = 20.0f;

    public static final String EASY_LABEL = "Easy";
    public static final String MEDIUM_LABEL = "Medium";
    public static final String HARD_LABEL = "Hard";

    public static final float EASY_HEALTHY_SPAWNS_PER_SECOND = 3;
    public static final float EASY_JUNK_SPAWNS_PER_SECOND = 1;
    public static final float MEDIUM_HEALTHY_SPAWNS_PER_SECOND = 4;
    public static final float MEDIUM_JUNK_SPAWNS_PER_SECOND = 2;
    public static final float HARD_HEALTHY_SPAWNS_PER_SECOND = 3;
    public static final float HARD_JUNK_SPAWNS_PER_SECOND = 5;

    public static final Color EASY_COLOR = new Color(0.2f, 0.2f, 1, 1);
    public static final Color MEDIUM_COLOR = new Color(0.5f, 0.5f, 1, 1);
    public static final Color HARD_COLOR = new Color(0.7f, 0.7f, 1, 1);

    public static final float LEVEL_WORLD_SIZE = 480.0f;
    public static final float LEVEL_BUBBLE_RADIUS = LEVEL_WORLD_SIZE / 9;
    public static final float LEVEL_LABEL_SCALE = 1.5f;

    public static final Vector2 EASY_CENTER = new Vector2(LEVEL_WORLD_SIZE / 4, LEVEL_WORLD_SIZE / 2);
    public static final Vector2 MEDIUM_CENTER = new Vector2(LEVEL_WORLD_SIZE / 2, LEVEL_WORLD_SIZE / 2);
    public static final Vector2 HARD_CENTER = new Vector2(LEVEL_WORLD_SIZE * 3 / 4, LEVEL_WORLD_SIZE / 2);

    public static final String SCORE_TIME_LABEL = "Zeit: ";
    public static final String SCORE_TIME_UNIT = " / 60 sek";
    public static final String SCORE_CALORIC_VALUE_LABEL = "Brennwert: ";
    public static final String SCORE_CALORIC_VALUE_UNIT = " / 1880 kcal";
    public static final String SCORE_CARBS_LABEL = "Kohlenhydrate: ";
    public static final String SCORE_CARBS_UNIT = " / 280 g";
    public static final String SCORE_FAT_LABEL = "Fett: ";
    public static final String SCORE_FAT_UNIT = " / 60 g";
    public static final String SCORE_PROTEINS_LABEL = "Proteine: ";
    public static final String SCORE_PROTEINS_UNIT = " / 55 g";
    public static final String SCORE_VITAMIN_A_LABEL = "Vitamin A: ";
    public static final String SCORE_VITAMIN_A_UNIT = " / 900 ug";
    public static final String SCORE_VITAMIN_C_LABEL = "Vitamin C: ";
    public static final String SCORE_VITAMIN_C_UNIT = " / 100 mg";
    public static final String SCORE_CALCIUM_LABEL = "Calcium: ";
    public static final String SCORE_CALCIUM_UNIT = " / 1000 mg";
    public static final String SCORE_IRON_LABEL = "Eisen: ";
    public static final String SCORE_IRON_UNIT = " / 3000 ug";

    public static final int INITIAL_FOOD_ARRAY_CAPACITY = 100;

}

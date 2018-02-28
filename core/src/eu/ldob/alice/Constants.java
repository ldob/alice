package eu.ldob.alice;

import com.badlogic.gdx.graphics.Color;

public class Constants {

    public static final boolean DEBUG = false;

    public static final float WORLD_WIDTH = 1280;
    public static final float WORLD_HEIGHT = 720;

    public static final String GAME_BACKGROUND = "background/background_alt.png";
    public static final String GAME_BACKGROUND_GAMEOVER = "background/background_alt_gameover.png";

    public static final int PLAYER_WIDTH = 83;
    public static final int PLAYER_HEIGHT = 128;
    //public static final int PLAYER_WIDTH = 100;
    //public static final int PLAYER_HEIGHT = 100;

    public static final String PLAYER_TEXTURE_STANDING_1 = "player/player_standing_1.png";
    public static final String PLAYER_TEXTURE_STANDING_2 = "player/player_standing_2.png";

    public static final String PLAYER_TEXTURE_RUNNING_1 = "player/player_running_1.png";
    public static final String PLAYER_TEXTURE_RUNNING_2 = "player/player_running_2.png";
    public static final String PLAYER_TEXTURE_RUNNING_3 = "player/player_running_3.png";
    public static final String PLAYER_TEXTURE_RUNNING_4 = "player/player_running_4.png";
    public static final String PLAYER_TEXTURE_RUNNING_5 = "player/player_running_5.png";
    public static final String PLAYER_TEXTURE_RUNNING_6 = "player/player_running_6.png";

    public static final String PLAYER_TEXTURE_JUMPING_1 = "player/player_jumping_1.png";
    public static final String PLAYER_TEXTURE_JUMPING_2 = "player/player_jumping_2.png";
    public static final String PLAYER_TEXTURE_JUMPING_3 = "player/player_jumping_3.png";
    public static final String PLAYER_TEXTURE_JUMPING_4 = "player/player_jumping_4.png";
    public static final String PLAYER_TEXTURE_JUMPING_5 = "player/player_jumping_5.png";
    public static final String PLAYER_TEXTURE_JUMPING_6 = "player/player_jumping_6.png";

    public static final float ANIMATION_FRAME_DURATION_STANDING = 1/3f;
    public static final float ANIMATION_FRAME_DURATION_WALKING = 1/8f;
    public static final float ANIMATION_FRAME_DURATION_RUNNING = 1/12f;

    public static final String SKIN = "skins/comicui/comic-ui";

    public static final String SKIN_JSON = SKIN + ".json";
    public static final String SKIN_ATLAS = SKIN + ".atlas";

    public static final Color BACKGROUND_COLOR_WHITE = new Color(1f, 1f, 1f, 1f);
    public static final Color BACKGROUND_COLOR_BLACK = new Color(0f, 0f, 0f, 1f);

    public static final Color SCORE_ERROR_COLOR = new Color(1f, 0.2f, 0.2f, 0.4f);
    public static final Color SCORE_WARN_COLOR = new Color(1f, 1f, 0f, 0.4f);
    public static final Color SCORE_GOOD_COLOR = new Color(0.1f, 0.9f, 0.1f, 0.4f);
    public static final Color SCORE_DEFAULT_COLOR = new Color(0f, 0f, 1f, 0.4f);

    public static final float ACCELEROMETER_SENSITIVITY = 0.5f;
    public static final float GRAVITATIONAL_ACCELERATION = 9.8f;

    public static final float PLAYER_VELOCITY_SCALE = 500;
    public static final float FOOD_VELOCITY_SCALE = 7;

    public static final float JUMP_SCALE = 3f;

    public static final String HOME_LABEL = "ALICE";
    public static final String PLAY_LABEL = "Los!";
    public static final String BENEFITS_LABEL = "Bonus";
    public static final String MODE_LABEL = "Spielmodus";
    public static final String RESULT_LABEL = "Ergebnis";
    public static final String SETTINGS_LABEL = "Einstellungen";
    public static final String EXIT_LABEL = "Beenden";

    public static final String SAVE_LABEL = "Speichern";
    public static final String BACK_LABEL = "Zurück";
    public static final String AGAIN_LABEL = "Nochmal";
    public static final String HIGHSCORE_LABEL = "Highscore";

    public static final String NAME_LABEL = "Name";
    public static final String SOUND_LABEL = "Ton";

    public static final String BENEFITS_DESCRIPTION = "Was hast du in den letzten 24 Stunden gemacht?";

    public static final String BIG_LABEL = "> 1 h Krafttraining";
    public static final String PERSISTENT_LABEL = "> 45 min Ausdauertraining";
    public static final String CALORIC_LABEL = "> 10.000 Schritte";
    public static final String COORDINATION_LABEL = "> 20 min Koordinationstraining";
    public static final String HEALTHY_LABEL = "Gesunde Ernährung";
    public static final String JUNK_LABEL = "Kein Junk-Food";

    public static final String MODE_BALANCED_LABEL = "Ausgewogene Ernährung";
    public static final String MODE_VITAMINS_LABEL = "Vitaminbombe";
    public static final String MODE_MINERAL_HUNTER_LABEL = "Mineralstoffjäger";
    public static final String MODE_FAT_LABEL = "Flucht vor Fett";

    public static final String SCORE_VALUE_SEPERATOR = " / ";
    public static final String SCORE_TIME_LABEL = "Zeit: ";
    public static final String SCORE_TIME_UNIT = " sek";

    public static final String GAMEOVER_TIME = "Zeit ist abgelaufen!";
    public static final String GAMEOVER_CALORIC_VALUE = "Zu viel Energie!";
    public static final String GAMEOVER_FAT = "Zu viel Fett!";

    public static final String TOTAL_LABEL = "Gesamt";
    public static final String SCORE_LABEL = "Score";
    public static final String SCORE_TOTAL_LABEL = "Gesamt Score";
    public static final String RANK_TOTAL_LABEL = "Rang";

    public static final int INITIAL_FOOD_ARRAY_CAPACITY = 100;
}

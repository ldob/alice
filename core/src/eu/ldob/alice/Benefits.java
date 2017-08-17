package eu.ldob.alice;

public class Benefits {

    private static final float PLAYER_SPEED_SLOW = 10.0f;
    private static final float PLAYER_SPEED_FAST = 14.0f;

    private static final int MAXIMUM_GAME_TIME_SHORT = 60;
    private static final int MAXIMUM_GAME_TIME_LONG = 80;

    private static final float CALORIC_VALUE_FACTOR = 1.25f;
    private static final int CALORIC_VALUE_HIGH = 2350;

    private static final float FOOD_HEALTHY_SPAWN_FACTOR_MORE = 1.3f;
    private static final float FOOD_JUNK_SPAWN_FACTOR_LESS = 0.8f;

    private boolean benefitFastPlayer = false;
    private boolean benefitBiggerPlayer = false;
    private boolean benefitPersistentPlayer = false;
    private boolean benefitMoreCaloricValue = false;
    private boolean benefitMoreHealthyFood = false;
    private boolean benefitLessJunkFood = false;

    public float getPlayerSpeed() {
        return benefitFastPlayer ? PLAYER_SPEED_FAST : PLAYER_SPEED_SLOW;
    }

    public float getPlayerScale() {
        return benefitBiggerPlayer ? 1 : 1;
    }

    public float getCaloricValueFactor() {
        return benefitMoreCaloricValue ? CALORIC_VALUE_FACTOR : 1;
    }

    public float getHealthySpawnRateFactor() {
        return benefitMoreHealthyFood ? FOOD_HEALTHY_SPAWN_FACTOR_MORE : 1;
    }

    public float getJunkSpawnRateFactor() {
        return benefitLessJunkFood ? FOOD_JUNK_SPAWN_FACTOR_LESS : 1;
    }

    public boolean isBenefitFastPlayer() {
        return benefitFastPlayer;
    }

    public void setBenefitFastPlayer(boolean benefitFastPlayer) {
        this.benefitFastPlayer = benefitFastPlayer;
    }

    public boolean isBenefitBiggerPlayer() {
        return benefitBiggerPlayer;
    }

    public void setBenefitBiggerPlayer(boolean benefitBiggerPlayer) {
        this.benefitBiggerPlayer = benefitBiggerPlayer;
    }

    public boolean isBenefitPersistentPlayer() {
        return benefitPersistentPlayer;
    }

    public void setBenefitPersistentPlayer(boolean benefitPersistentPlayer) {
        this.benefitPersistentPlayer = benefitPersistentPlayer;
    }

    public boolean isBenefitMoreCaloricValue() {
        return benefitMoreCaloricValue;
    }

    public void setBenefitMoreCaloricValue(boolean benefitMoreCaloricValue) {
        this.benefitMoreCaloricValue = benefitMoreCaloricValue;
    }

    public boolean isBenefitMoreHealthyFood() {
        return benefitMoreHealthyFood;
    }

    public void setBenefitMoreHealthyFood(boolean benefitMoreHealthyFood) {
        this.benefitMoreHealthyFood = benefitMoreHealthyFood;
    }

    public boolean isBenefitLessJunkFood() {
        return benefitLessJunkFood;
    }

    public void setBenefitLessJunkFood(boolean benefitLessJunkFood) {
        this.benefitLessJunkFood = benefitLessJunkFood;
    }

}

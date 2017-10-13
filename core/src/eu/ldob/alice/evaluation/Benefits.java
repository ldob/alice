package eu.ldob.alice.evaluation;

public class Benefits {

    private static final int MAXIMUM_GAME_TIME_SHORT = 60;
    private static final int MAXIMUM_GAME_TIME_LONG = 80;

    private static final int CALORIC_VALUE_LOW = 1880;
    private static final int CALORIC_VALUE_HIGH = 2350;

    public static final float JUMP_TIME_LOW = 650f;
    public static final float JUMP_TIME_HIGH = 900f;

    private static final float FOOD_HEALTHY_SPAWN_FACTOR_MORE = 1.3f;
    private static final float FOOD_JUNK_SPAWN_FACTOR_LESS = 0.8f;

    private boolean benefitLongGameTime = false;
    private boolean benefitBiggerPlayer = false;
    private boolean benefitJumpHigh = false;
    private boolean benefitMoreCaloricValue = false;
    private boolean benefitMoreHealthyFood = false;
    private boolean benefitLessJunkFood = false;


    public int getMaxmumGameTime() {
        return benefitLongGameTime ? MAXIMUM_GAME_TIME_LONG : MAXIMUM_GAME_TIME_SHORT;
    }

    public float getPlayerScale() {
        return benefitBiggerPlayer ? 1.5f : 1f;
    }

    public int getCaloricValueTarget() {
        return benefitMoreCaloricValue ? CALORIC_VALUE_HIGH : CALORIC_VALUE_LOW;
    }

    public float getJumpTime() {
        return benefitMoreCaloricValue ? JUMP_TIME_HIGH : JUMP_TIME_LOW;
    }

    public float getHealthySpawnRateFactor() {
        return benefitMoreHealthyFood ? FOOD_HEALTHY_SPAWN_FACTOR_MORE : 1;
    }

    public float getJunkSpawnRateFactor() {
        return benefitLessJunkFood ? FOOD_JUNK_SPAWN_FACTOR_LESS : 1;
    }




    public boolean isBenefitLongGameTime() {
        return benefitLongGameTime;
    }

    public void setBenefitLongGameTime(boolean benefitLongGameTime) {
        this.benefitLongGameTime = benefitLongGameTime;
    }

    public boolean isBenefitBiggerPlayer() {
        return benefitBiggerPlayer;
    }

    public void setBenefitBiggerPlayer(boolean benefitBiggerPlayer) {
        this.benefitBiggerPlayer = benefitBiggerPlayer;
    }

    public boolean isBenefitMoreCaloricValue() {
        return benefitMoreCaloricValue;
    }

    public void setBenefitMoreCaloricValue(boolean benefitMoreCaloricValue) {
        this.benefitMoreCaloricValue = benefitMoreCaloricValue;
    }

    public boolean isBenefitJumpHigh() {
        return benefitJumpHigh;
    }

    public void setBenefitJumpHigh(boolean benefitJumpHigh) {
        this.benefitJumpHigh = benefitJumpHigh;
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

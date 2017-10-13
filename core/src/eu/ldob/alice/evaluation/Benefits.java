package eu.ldob.alice.evaluation;


public class Benefits {

    private static final int MAXIMUM_GAME_TIME_SHORT = 60;
    private static final int MAXIMUM_GAME_TIME_LONG = 80;

    private static final int CALORIC_VALUE_TARGET_LOW = 1880;   // kcal
    private static final int CALORIC_VALUE_TARGET_HIGH = 2350;  // kcal

    private static final int CARBS_TARGET_LOW = 280;            // g
    private static final int CARBS_TARGET_HIGH = 300;           // g

    private static final int FAT_TARGET_LOW = 60;               // g
    private static final int FAT_TARGET_HIGH = 70;              // g

    private static final int PROTEINS_TARGET_LOW = 55;          // g
    private static final int PROTEINS_TARGET_HIGH = 55;         // g

    private static final int VITAMIN_A_TARGET = 800;            // ug
    private static final int VITAMIN_C_TARGET = 100;            // mg
    private static final int CALCIUM_TARGET = 1000;             // mg
    private static final int IRON_TARGET = 10000;               // ug

    private static final float JUMP_TIME_LOW = 650f;
    private static final float JUMP_TIME_HIGH = 900f;

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

    public int getTarget(NutritionType type) {
        switch(type) {
            case CALORIC_VALUE: return this.getCaloricValueTarget();
            case CARBS: return this.getCarbsTarget();
            case FAT: return this.getFatTarget();
            case PROTEINS: return this.getProteinsTarget();
            case VITAMIN_A: return this.getVitaminATarget();
            case VITAMIN_C: return this.getVitaminCTarget();
            case CALCIUM: return this.getCalciumTarget();
            case IRON: return this.getIronTarget();
        }

        throw new RuntimeException("NutritionType not implemented yet");
    }

    public int getCaloricValueTarget() {
        return benefitMoreCaloricValue ? CALORIC_VALUE_TARGET_HIGH : CALORIC_VALUE_TARGET_LOW;
    }

    public int getCarbsTarget() {
        return benefitMoreCaloricValue ? CARBS_TARGET_HIGH : CARBS_TARGET_LOW;
    }

    public int getFatTarget() {
        return benefitMoreCaloricValue ? FAT_TARGET_HIGH : FAT_TARGET_LOW;
    }

    public int getProteinsTarget() {
        return PROTEINS_TARGET_LOW;
    }

    public int getVitaminATarget() {
        return VITAMIN_A_TARGET;
    }

    public int getVitaminCTarget() {
        return VITAMIN_C_TARGET;
    }

    public int getCalciumTarget() {
        return CALCIUM_TARGET;
    }

    public int getIronTarget() {
        return IRON_TARGET;
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

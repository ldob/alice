package eu.ldob.alice.actor.util;


import eu.ldob.alice.evaluation.NutritionType;

public class NutritionFacts implements Cloneable {

    private int caloricValue;
    private int carbs;
    private int fat;
    private int proteins;
    private int vitaminA;
    private int vitaminC;
    private int calcium;
    private int iron;

    public NutritionFacts() {


        this.caloricValue = 0;
        this.carbs = 0;
        this.fat = 0;
        this.proteins = 0;
        this.vitaminA = 0;
        this.vitaminC = 0;
        this.calcium = 0;
        this.iron = 0;
    }

    public NutritionFacts(int caloricValue, int carbs, int fat, int proteins, int vitaminA, int vitaminC, int calcium, int iron) {
        this.caloricValue = caloricValue;
        this.carbs = carbs;
        this.fat = fat;
        this.proteins = proteins;
        this.vitaminA = vitaminA;
        this.vitaminC = vitaminC;
        this.calcium = calcium;
        this.iron = iron;
    }

    public NutritionFacts(NutritionFacts nutritionFacts) {
        this(nutritionFacts, 1);
    }

    public NutritionFacts(NutritionFacts nutritionFacts, int multiplier) {
        this.caloricValue = nutritionFacts.getCaloricValue() * multiplier;
        this.carbs = nutritionFacts.getCarbs() * multiplier;
        this.fat = nutritionFacts.getFat() * multiplier;
        this.proteins = nutritionFacts.getProteins() * multiplier;
        this.vitaminA = nutritionFacts.getVitaminA() * multiplier;
        this.vitaminC = nutritionFacts.getVitaminC() * multiplier;
        this.calcium = nutritionFacts.getCalcium() * multiplier;
        this.iron = nutritionFacts.getIron() * multiplier;
    }

    public void add(NutritionFacts nutritionFacts) {
        this.add(nutritionFacts, 1);
    }

    public void add(NutritionFacts nutritionFacts, int multiplier) {
        this.caloricValue += nutritionFacts.getCaloricValue() * multiplier;
        this.carbs += nutritionFacts.getCarbs() * multiplier;
        this.fat += nutritionFacts.getFat() * multiplier;
        this.proteins += nutritionFacts.getProteins() * multiplier;
        this.vitaminA += nutritionFacts.getVitaminA() * multiplier;
        this.vitaminC += nutritionFacts.getVitaminC() * multiplier;
        this.calcium += nutritionFacts.getCalcium() * multiplier;
        this.iron += nutritionFacts.getIron() * multiplier;
    }

    public int getCaloricValue() {
        return caloricValue;
    }
    public int getCarbs() {
        return carbs;
    }
    public int getFat() {
        return fat;
    }
    public int getProteins() {
        return proteins;
    }
    public int getVitaminA() {
        return vitaminA;
    }
    public int getVitaminC() {
        return vitaminC;
    }
    public int getCalcium() {
        return calcium;
    }
    public int getIron() {
        return iron;
    }

    public int get(NutritionType type) {
        switch(type) {
            case CALORIC_VALUE: return this.caloricValue;
            case CARBS: return this.carbs;
            case FAT: return this.fat;
            case PROTEINS: return this.proteins;
            case VITAMIN_A: return this.vitaminA;
            case VITAMIN_C: return this.vitaminC;
            case CALCIUM: return this.calcium;
            case IRON: return this.iron;
        }

        throw new RuntimeException("NutritionType not implemented yet");
    }
}

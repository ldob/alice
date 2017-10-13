package eu.ldob.alice.evaluation;

public enum NutritionType {

    CALORIC_VALUE("Energie", "kcal"),
    CARBS("Kohlenhydrate", "g"),
    FAT("Fett", "g"),
    PROTEINS("Proteine", "g"),
    VITAMIN_A("Vitamin A", "ug"),
    VITAMIN_C("Vitamin C", "mg"),
    CALCIUM("Kalzium", "mg"),
    IRON("Eisen", "ug");

    private String name;
    private String unit;

    NutritionType(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }
}

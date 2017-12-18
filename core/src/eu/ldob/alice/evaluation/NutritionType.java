package eu.ldob.alice.evaluation;

import eu.ldob.alice.evaluation.formatting.AFormatting;
import eu.ldob.alice.evaluation.formatting.NegativeFormatting;
import eu.ldob.alice.evaluation.formatting.NeutralFormatting;
import eu.ldob.alice.evaluation.formatting.PositiveFormatting;

public enum NutritionType {

    CALORIC_VALUE("Energie", "kcal", new NeutralFormatting()),
    CARBS("Kohlenhydrate", "g", new NeutralFormatting()),
    FAT("Fett", "g", new NegativeFormatting()),
    PROTEINS("Proteine", "g", new PositiveFormatting()),
    VITAMIN_A("Vitamin A", "ug", new PositiveFormatting()),
    VITAMIN_C("Vitamin C", "mg", new PositiveFormatting()),
    CALCIUM("Kalzium", "mg", new PositiveFormatting()),
    IRON("Eisen", "ug", new PositiveFormatting());

    private String name;
    private String unit;
    private AFormatting formatting;

    NutritionType(String name, String unit, AFormatting formatting) {
        this.name = name;
        this.unit = unit;
        this.formatting = formatting;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public AFormatting getFormatting() {
        return formatting;
    }
}

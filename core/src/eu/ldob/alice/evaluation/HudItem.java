package eu.ldob.alice.evaluation;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

import eu.ldob.alice.Constants;

public class HudItem {

    protected final Label lbName;
    protected final Label lbValue;
    protected final Label lbSeparator;
    protected final Label lbTarget;
    protected final Label lbUnit;

    private NutritionType nutritionType;

    protected HudItem(Skin skin, NutritionType nutritionType, Benefits benefits) {
        this(skin, nutritionType.getName(), benefits.getTarget(nutritionType), nutritionType.getUnit());

        this.nutritionType = nutritionType;
    }

    protected HudItem(Skin skin, String name, double target, String unit) {
        lbName = new Label(name, skin);
        lbValue = new Label("", skin);
        lbSeparator = new Label(Constants.SCORE_VALUE_SEPERATOR, skin);
        lbTarget = new Label(String.valueOf(Math.round(target)), skin);
        lbUnit = new Label(unit, skin);

        lbName.setAlignment(Align.right);
        lbValue.setAlignment(Align.right);
        lbSeparator.setAlignment(Align.center);
        lbTarget.setAlignment(Align.left);
        lbUnit.setAlignment(Align.left);
    }

    public void update(float value) {
        lbValue.setText(String.valueOf(MathUtils.round(value)));
    }

    public NutritionType getNutritionType() {
        return this.nutritionType;
    }
}

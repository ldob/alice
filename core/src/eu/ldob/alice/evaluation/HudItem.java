package eu.ldob.alice.evaluation;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import java.util.Map;
import java.util.TreeMap;

import eu.ldob.alice.Constants;
import eu.ldob.alice.evaluation.formatting.AFormatting;
import eu.ldob.alice.util.PixmapUtil;

public class HudItem {

    private Skin skin;
    private Table row;

    protected final Label lbName;
    protected final Label lbValue;
    protected final Label lbSeparator;
    protected final Label lbTarget;
    protected final Label lbUnit;

    private double target;
    private AFormatting formatting;

    private NutritionType nutritionType;

    protected HudItem(Skin skin, NutritionType nutritionType, Benefits benefits, AFormatting formatting) {
        this(skin, nutritionType.getName(), benefits.getTarget(nutritionType), nutritionType.getUnit(), formatting);

        this.nutritionType = nutritionType;
    }

    protected HudItem(Skin skin, String name, double target, String unit, AFormatting formatting) {
        this.skin = skin;
        this.target = target;
        this.formatting = formatting;

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

        row = new Table(skin);
        row.add(lbName).width(180).fillX();
        row.add(lbValue).width(60).fillX();
        row.add(lbSeparator).width(25).fillX();
        row.add(lbTarget).width(70).fillX();
        row.add(lbUnit).width(50).fillX();

        row.setBackground(PixmapUtil.getInstance().getBackground(formatting.getColor(0)));
    }

    protected Table getRow() {
        return row;
    }

    public void update(float value) {
        lbValue.setText(String.valueOf(MathUtils.round(value)));
        row.setBackground(PixmapUtil.getInstance().getBackground(formatting.getColor(value / target)));
    }

    public NutritionType getNutritionType() {
        return this.nutritionType;
    }
}

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

    private Formatting formatting;
    private double target;

    protected final Label lbName;
    protected final Label lbValue;
    protected final Label lbSeparator;
    protected final Label lbTarget;
    protected final Label lbUnit;

    protected HudItem(Skin skin, String name, double target, String unit, Formatting formatting) {

        this.formatting = formatting;
        this.target = target;

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

        // TODO
        /*
        if(formatting == Formatting.NONE) {
            lbName.setColor(Constants.SCORE_DEFAULT_COLOR);
            lbValue.setColor(Constants.SCORE_DEFAULT_COLOR);
            lbSeparator.setColor(Constants.SCORE_DEFAULT_COLOR);
            lbTarget.setColor(Constants.SCORE_DEFAULT_COLOR);
            lbUnit.setColor(Constants.SCORE_DEFAULT_COLOR);
        }
        else if (
                formatting == Formatting.POSITIVE && value > target * 0.9f
                ||
                formatting == Formatting.NEUTRAL && value < target * 0.5f
                ||
                formatting == Formatting.NEUTRAL && value > target * 1.2f
                ||
                formatting == Formatting.NEGATIVE && value > target * 0.8f
                ) {
            lbName.setColor(Constants.SCORE_ERROR_COLOR);
            lbValue.setColor(Constants.SCORE_ERROR_COLOR);
            lbSeparator.setColor(Constants.SCORE_ERROR_COLOR);
            lbTarget.setColor(Constants.SCORE_ERROR_COLOR);
            lbUnit.setColor(Constants.SCORE_ERROR_COLOR);
        }
        else if (
                formatting == Formatting.POSITIVE && value < target * 0.5f
                ||
                formatting == Formatting.NEUTRAL && value < target * 1.1f && value > target * 0.8f
                ||
                formatting == Formatting.NEGATIVE && value < target * 0.5f
                ) {
            lbName.setColor(Constants.SCORE_GOOD_COLOR);
            lbValue.setColor(Constants.SCORE_GOOD_COLOR);
            lbSeparator.setColor(Constants.SCORE_GOOD_COLOR);
            lbTarget.setColor(Constants.SCORE_GOOD_COLOR);
            lbUnit.setColor(Constants.SCORE_GOOD_COLOR);
        }
        else {
            lbName.setColor(Constants.SCORE_WARN_COLOR);
            lbValue.setColor(Constants.SCORE_WARN_COLOR);
            lbSeparator.setColor(Constants.SCORE_WARN_COLOR);
            lbTarget.setColor(Constants.SCORE_WARN_COLOR);
            lbUnit.setColor(Constants.SCORE_WARN_COLOR);
        }
        */
    }

    protected enum Formatting {
        POSITIVE, NEGATIVE, NEUTRAL, NONE
    }
}

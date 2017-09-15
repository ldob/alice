package eu.ldob.alice.mode;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import eu.ldob.alice.Constants;

public class HudItem {

    protected final Label lbName;
    protected final Label lbValue;
    protected final Label lbSeparator;
    protected final Label lbTarget;
    protected final Label lbUnit;

    protected HudItem(Label.LabelStyle style, String name, float target, String unit) {

        lbName = new Label(name, style);
        lbValue = new Label("", style);
        lbSeparator = new Label(Constants.SCORE_VALUE_SEPERATOR, style);
        lbTarget = new Label(String.valueOf(MathUtils.round(target)), style);
        lbUnit = new Label(unit, style);

        lbName.setAlignment(Align.right);
        lbValue.setAlignment(Align.right);
        lbSeparator.setAlignment(Align.center);
        lbTarget.setAlignment(Align.left);
        lbUnit.setAlignment(Align.left);
    }

    public void update(float value, Formatting formatting) {

        lbValue.setText(String.valueOf(MathUtils.round(value)));

        if (
            formatting == Formatting.NEGATIVE
        ) {
            lbName.setColor(Constants.SCORE_ERROR_COLOR);
            lbValue.setColor(Constants.SCORE_ERROR_COLOR);
            lbSeparator.setColor(Constants.SCORE_ERROR_COLOR);
            lbTarget.setColor(Constants.SCORE_ERROR_COLOR);
            lbUnit.setColor(Constants.SCORE_ERROR_COLOR);
        }
        else if (
            formatting == Formatting.NEUTRAL
        ) {
            lbName.setColor(Constants.SCORE_WARN_COLOR);
            lbValue.setColor(Constants.SCORE_WARN_COLOR);
            lbSeparator.setColor(Constants.SCORE_WARN_COLOR);
            lbTarget.setColor(Constants.SCORE_WARN_COLOR);
            lbUnit.setColor(Constants.SCORE_WARN_COLOR);
        }
        else if (
                formatting == Formatting.POSITIVE
        ) {
            lbName.setColor(Constants.SCORE_GOOD_COLOR);
            lbValue.setColor(Constants.SCORE_GOOD_COLOR);
            lbSeparator.setColor(Constants.SCORE_GOOD_COLOR);
            lbTarget.setColor(Constants.SCORE_GOOD_COLOR);
            lbUnit.setColor(Constants.SCORE_GOOD_COLOR);
        }
        else {
            lbName.setColor(Constants.SCORE_DEFAULT_COLOR);
            lbValue.setColor(Constants.SCORE_DEFAULT_COLOR);
            lbSeparator.setColor(Constants.SCORE_DEFAULT_COLOR);
            lbTarget.setColor(Constants.SCORE_DEFAULT_COLOR);
            lbUnit.setColor(Constants.SCORE_DEFAULT_COLOR);
        }
    }

    protected enum Formatting {
        POSITIVE, NEGATIVE, NEUTRAL, NONE;
    }
}

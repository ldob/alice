package eu.ldob.alice.evaluation.formatting;

import com.badlogic.gdx.graphics.Color;

public class NeutralFormatting extends AFormatting {

    @Override
    public Color getColor(double ratio) {

        if(ratio <= 0) { return COLOR_WARN_1; }
        if(ratio <= 0.1) { return COLOR_WARN_2; }
        if(ratio <= 0.2) { return COLOR_WARN_3; }
        if(ratio <= 0.3) { return COLOR_WARN_4; }
        if(ratio <= 0.4) { return COLOR_WARN_5; }
        if(ratio <= 0.5) { return COLOR_FAIR_5; }
        if(ratio <= 0.6) { return COLOR_FAIR_3; }
        if(ratio <= 0.7) { return COLOR_FAIR_1; }
        if(ratio <= 0.8) { return COLOR_GOOD_2; }
        if(ratio <= 0.9) { return COLOR_GOOD_4; }
        if(ratio <= 0.95) { return COLOR_GOOD_5; }
        if(ratio <= 1.05) { return COLOR_GOOD_6; }
        if(ratio <= 1.1) { return COLOR_GOOD_3; }
        if(ratio <= 1.15) { return COLOR_GOOD_1; }
        if(ratio <= 1.2) { return COLOR_FAIR_3; }
        if(ratio <= 1.25) { return COLOR_WARN_5; }
        if(ratio <= 1.3) { return COLOR_WARN_4; }
        if(ratio <= 1.4) { return COLOR_WARN_3; }
        if(ratio <= 1.5) { return COLOR_WARN_2; }

        return COLOR_WARN_1;

    }

}

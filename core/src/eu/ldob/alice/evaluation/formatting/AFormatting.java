package eu.ldob.alice.evaluation.formatting;

import com.badlogic.gdx.graphics.Color;

public abstract class AFormatting {

    protected static final Color COLOR_WARN_1 = new Color(0xe64a1988);
    protected static final Color COLOR_WARN_2 = new Color(0xdf521c88);
    protected static final Color COLOR_WARN_3 = new Color(0xd65a1f88);
    protected static final Color COLOR_WARN_4 = new Color(0xce622288);
    protected static final Color COLOR_WARN_5 = new Color(0xc6692588);
    protected static final Color COLOR_FAIR_5 = new Color(0xbf722888);
    protected static final Color COLOR_FAIR_4 = new Color(0xb6792c88);
    protected static final Color COLOR_FAIR_3 = new Color(0xe64a1988);
    protected static final Color COLOR_FAIR_2 = new Color(0xb6792c88);
    protected static final Color COLOR_FAIR_1 = new Color(0xa18e3388);
    protected static final Color COLOR_GOOD_1 = new Color(0x99963788);
    protected static final Color COLOR_GOOD_2 = new Color(0x919e3a88);
    protected static final Color COLOR_GOOD_3 = new Color(0x919e3a88);
    protected static final Color COLOR_GOOD_4 = new Color(0x86a83e88);
    protected static final Color COLOR_GOOD_5 = new Color(0x82ae4088);
    protected static final Color COLOR_GOOD_6 = new Color(0x7cb34288);

    protected static final Color COLOR_ERROR = new Color(0xdd111188);

    public abstract Color getColor(double ratio);

}

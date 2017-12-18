package eu.ldob.alice.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.HashMap;
import java.util.Map;

public class PixmapUtil {

    private static PixmapUtil instance;

    private PixmapUtil() { }

    public static PixmapUtil getInstance() {
        if(instance == null) {
            instance = new PixmapUtil();
        }

        return instance;
    }

    private Map<Color,TextureRegionDrawable> drawableColorMap = new HashMap<Color, TextureRegionDrawable>();

    public TextureRegionDrawable getBackground(Color color) {

        if(drawableColorMap.containsKey(color)) {
            return drawableColorMap.get(color);
        }

        Pixmap pixmap = new Pixmap(2, 2, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();

        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        drawableColorMap.put(color, drawable);

        return drawable;
    }

}

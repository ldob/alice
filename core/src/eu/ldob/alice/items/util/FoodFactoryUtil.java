package eu.ldob.alice.items.util;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.Map;

import eu.ldob.alice.evaluation.Mode;

public class FoodFactoryUtil {

    public static Vector2 getAcceleration(Mode mode, Map<Acceleration,Vector2> accelerations) {
        Vector2 acceleration;
        float deviation = 30f;      // in percent

        // TODO implement slow food
        boolean moveSlow = false;

        if(moveSlow) {
            deviation = deviation / 1.5f;
        }

        float random = MathUtils.random();
        if(random < 0.33f) {
            acceleration = accelerations.get(moveSlow ? Acceleration.LOW_LEFT : Acceleration.HIGH_LEFT);
        }
        else if(random > 0.66f) {
            acceleration = accelerations.get(moveSlow ? Acceleration.LOW_RIGHT : Acceleration.HIGH_RIGHT);
        }
        else {
            acceleration = accelerations.get(moveSlow ? Acceleration.LOW : Acceleration.HIGH);
        }

        return acceleration.add(new Vector2(acceleration).scl((float) ((0.5 - Math.random()) * deviation / 100.0)));
    }

    public enum Acceleration {
        LOW, LOW_LEFT, LOW_RIGHT, HIGH, HIGH_LEFT, HIGH_RIGHT
    }
}

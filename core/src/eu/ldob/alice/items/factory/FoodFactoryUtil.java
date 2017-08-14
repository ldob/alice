package eu.ldob.alice.items.factory;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.Map;

import eu.ldob.alice.Level;

public class FoodFactoryUtil {

    protected static Vector2 getAcceleration(Level level, Map<Acceleration,Vector2> accelerations) {
        Vector2 acceleration;
        int deviation;

        if(level == Level.HARD) {
            deviation = 30;

            float random = MathUtils.random();
            if(random < 0.33f) {
                acceleration = accelerations.get(Acceleration.HIGH_LEFT);
            }
            else if(random > 0.66f) {
                acceleration = accelerations.get(Acceleration.HIGH_RIGHT);
            }
            else {
                acceleration = accelerations.get(Acceleration.HIGH);
            }
        }
        else if(level == Level.MEDIUM) {
            deviation = 10;
            acceleration = accelerations.get(Acceleration.MEDIUM);
        }
        else {
            deviation = 7;
            acceleration = accelerations.get(Acceleration.LOW);
        }

        return acceleration.add(new Vector2(acceleration).scl((float) ((0.5 - Math.random()) * deviation / 100.0)));
    }

    protected enum Acceleration {
        LOW, MEDIUM, HIGH, HIGH_LEFT, HIGH_RIGHT
    }
}

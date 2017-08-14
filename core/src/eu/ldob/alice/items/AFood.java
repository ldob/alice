package eu.ldob.alice.items;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class AFood {

    public abstract void render(ShapeRenderer renderer);

    protected abstract Vector2 getPosition();
    protected abstract Vector2 getVelocity();
    protected abstract Vector2 getAcceleration();
    protected abstract float getRadius();

    public abstract int getCaloricValue();
    public abstract int getCarbs();
    public abstract int getFat();
    public abstract int getProteins();
    public abstract int getVitaminA();
    public abstract int getVitaminC();
    public abstract int getCalcium();
    public abstract int getIron();

    public void update(float delta) {
        this.getVelocity().mulAdd(this.getAcceleration(), delta);
        this.getPosition().mulAdd(this.getVelocity(), delta);
    }

    public boolean removeFromScreen() {
        return this.getPosition().y < -this.getRadius();
    }

}

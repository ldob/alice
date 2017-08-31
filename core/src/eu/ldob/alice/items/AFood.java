package eu.ldob.alice.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import eu.ldob.alice.Constants;
import eu.ldob.alice.items.food.NutritionFacts;

public abstract class AFood {

    protected abstract Vector2 getPosition();
    protected abstract Vector2 getVelocity();
    protected abstract Vector2 getAcceleration();
    protected abstract Sprite getSprite();


    public abstract NutritionFacts getNutritionFacts();


    public String getName() {
        return "UNKNOWN";
    }

    public void update(float delta) {
        this.getVelocity().mulAdd(this.getAcceleration(), delta * Constants.VELOCITY_SCALE);
        this.getPosition().mulAdd(this.getVelocity(), delta);
    }

    public void draw(SpriteBatch batch) {
        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);

        batch.begin();
        this.getSprite().draw(batch);
        batch.end();
    }

    public boolean removeFromScreen() {
        return this.getPosition().y < 0;
    }

    @Override
    public int hashCode() {
        String name = this.getName();
        int hash = 0;

        for(int i = 0; i < name.length(); i++) {
            hash = (int) name.charAt(i) * 31^(i-1);
        }

        return hash;
    }

    @Override
    public boolean equals(Object o) {

        if(o == null) {
            return false;
        }


        if(this == o) {
            return true;
        }

        if(!(o instanceof AFood)) {
            return false;
        }

        AFood f = (AFood) o;

        return this.getName().equals(f.getName());
    }
}

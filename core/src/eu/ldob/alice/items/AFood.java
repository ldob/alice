package eu.ldob.alice.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import eu.ldob.alice.Constants;
import eu.ldob.alice.items.food.FoodType;
import eu.ldob.alice.items.food.NutritionFacts;

public abstract class AFood {

    protected abstract Vector2 getPosition();
    protected abstract Vector2 getVelocity();
    protected abstract Vector2 getAcceleration();
    protected abstract Texture getTexture();
    protected abstract float getWidth();
    protected abstract float getHeight();

    public abstract FoodType getFoodType();
    public abstract NutritionFacts getNutritionFacts();


    public String getName() {
        return "UNKNOWN";
    }

    public void update(float delta) {
        this.getVelocity().mulAdd(this.getAcceleration(), delta * Constants.FOOD_VELOCITY_SCALE);
        this.getPosition().mulAdd(this.getVelocity(), delta);
    }

    public void draw(Batch batch) {
        batch.draw(this.getTexture(), this.getPosition().x, this.getPosition().y, this.getWidth(), this.getHeight());
    }

    public boolean removeFromScreen() {
        return this.getPosition().y + this.getHeight() < 0;
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

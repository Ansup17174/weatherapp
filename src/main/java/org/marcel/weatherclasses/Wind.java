package org.marcel.weatherclasses;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Wind {

    @NotBlank
    private String direction;

    @Min(0)
    private int velocity;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}

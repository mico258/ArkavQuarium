package animal;

import draw.*;

public abstract class Animal {
    private double xPosition;
    private double yPosition;
    private String image;




    private double speed;

    public Animal(double xPosition, double yPosition, String image) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.image = image;
    }

    public double getxPosition() {
        return xPosition;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}

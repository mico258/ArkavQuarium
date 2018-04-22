package droppable;

public abstract class Droppable {


    private double xPosition;
    private double yPosition;
    private int value;
    private boolean isFood;
    private String image;

    public Droppable(double xPosition, double yPosition, int value, boolean isFood, String image) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.value = value;
        this.isFood = isFood;
        this.image = image;
    }

    public double getxPosition() {
        return this.xPosition;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getyPosition() {
        return this.yPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isFood() {
        return this.isFood;
    }

    public void setFood(boolean isFood) {
        this.isFood = isFood;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public abstract void move();

}

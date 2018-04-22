package droppable;

public class Food extends Droppable {

    private boolean isEaten;

    public Food(double xPosition, double yPosition) {
        super(xPosition, yPosition, -2, true, "food.png");
        isEaten = false;
    }

    public boolean getIsTaken() {
        return this.isEaten;
    }

    public void setIsEaten(boolean isEaten) {
        this.isEaten = isEaten;
    }

    @Override
    public void move() {

    }
}

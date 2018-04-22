package droppable;

public class Koin extends Droppable {

    private boolean isTaken;

    public Koin(double xPosition, double yPosition, int value) {
        super(xPosition, yPosition, value, false, "koin.png");
        isTaken = false;
    }

    public boolean getIsTaken() {
        return this.isTaken;
    }

    public void setIsTaken(boolean isTaken) {
        this.isTaken = isTaken;
    }

    @Override
    public void move() {

    }
}

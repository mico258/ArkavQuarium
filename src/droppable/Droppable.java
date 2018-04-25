package droppable;

/**
 *Class Droppable .
 */
public abstract class Droppable {

    /**
     *position x .
     */
    private double xPosition;
    /**
     *position y .
     */
    private double yPosition;
    /**
     *value .
     */
    private int value;
    /**
     *boolean isFood .
     */
    private boolean isFood;
    /**
     *string image .
     */
    private String image;

    /**
     *class dropable .
     * @param image .
     * @param isFood .
     * @param value .
     * @param xPosition .
     * @param yPosition .
     */

    public Droppable(double xPosition, double yPosition, int value, boolean isFood, String image) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.value = value;
        this.isFood = isFood;
        this.image = image;
    }

    /**
     *getter XPos .
     * @return double .
     */
    public double getxPosition() {
        return this.xPosition;
    }

    /**
     *setter XPos .
     * @param xPosition .
     */
    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    /**
     *getter YPos .
     * @return double .
     */
    public double getyPosition() {
        return this.yPosition;
    }
    /**
     *setter YPos .
     * @param yPosition  .
     */
    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }
    /**
     *getter value .
     * @return int .
     */
    public int getValue() {
        return this.value;
    }
    /**
     *setter Value .
     * @param value  .
     */
    public void setValue(int value) {
        this.value = value;
    }
    /**
     *getter isFood .
     * @return boolean .
     */
    public boolean isFood() {
        return this.isFood;
    }
    /**
     *setter food .
     * @param isFood .
     */
    public void setFood(boolean isFood) {
        this.isFood = isFood;
    }
    /**
     *getter image .
     * @return String .
     */
    public String getImage() {
        return this.image;
    }
    /**
     *setter image .
     * @param image .
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *method move .
     *
     */
    public abstract void move();

}

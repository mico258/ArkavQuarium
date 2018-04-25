package position;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.StrictMath.sin;

/**
 *Class move .
 */

public class Move extends Coordinate {

    /**
     *Guppy Speed .
     */
    public static final double GUPPY_SPEED = 0.5;
    /**
     *Piranha Speed .
     */
    public static final double PIRANHA_SPEED = 0.7;
    /**
     *Snail Speed .
     */
    public static final double SNAIL_SPEED = 0.35;
    /**
     *Coin Speed .
     */
    public static final double COIN_SPEED = 0.25;
    /**
     *Food Speed .
     */
    public static final double FISH_FOOD_SPEED = 0.2;

    /**
     *Move Top .
     */
    public static final int MOVE_TOP = 0;
    /**
     *Move Bottom .
     */
    public static final int MOVE_BOTTOM = 1;
    /**
     *Move Right .
     */
    public static final int MOVE_RIGHT = 2;
    /**
     *Move Left .
     */
    public static final int MOVE_LEFT = 3;

    /**
     *Speed .
     */
    private double speed;

    /**
     *Constructor Move .
     */
    public Move() {
        speed = 0;
    }

    /**
     *Constructor Move .
     * @param y  .
     * @param x .
     */
    public Move(double x, double y) {
        super(x, y);
    }

    /**
     *Constructor Move .
     * @param speed .
     */
    public Move(double speed) {
        this.speed = speed;
    }

    /**
     *Constructor Move .
     * @param x .
     * @param speed .
     * @param y .
     */
    public Move(double x, double y, double speed) {
        super(x, y);
        this.speed = speed;
    }

    /**
     *Getter Speed .
     * @return double .
     */
    public double getSpeed() {
        return speed;
    }

    /**
     *Setter Speed .
     * @param speed .
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     *method Move Top .
     */
    public void moveTop() {
        setY(getY() - speed);
    }

    /**
     *method Move Bottom .
     */
    public void moveBottom() {
        setY(getY() + speed);
    }

    /**
     *method Move Right .
     */
    public void moveRight() {
        setX(getX() + speed);
    }

    /**
     *method Move Left .
     */
    public void moveLeft() {
        setX(getX() - speed);
    }

    /**
     *method Move  .
     * @param y2 .
     * @param x2 .
     */

    public void moveGeneral(double x2, double y2) {
        double a = atan2(y2 - getY(), x2 - getX());
        setX(getX() + (getSpeed() * cos(a) * 1));
        setY(getY() + (getSpeed() * sin(a) * 1));
    }

}

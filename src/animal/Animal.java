/**
 * Package Animal for controll all animal.
 */
package animal;

import position.Move;

/**
 * Class Animal.
 *
 *
 */

public class Animal extends Move {

    /**
     * status lihat ke kiri.
     *
     *
     */
    public static final int LOOKING_LEFT = 0;
    /**
     * status lihat ke kanan.
     *
     *
     */
    public static final int LOOKING_RIGHT = 1;

    /**
     *int look.
     *
     *
     */
    private int lookAt;

    /**
     * Constructor Animal.
     * @param speed .
     * @param x .
     * @param y .
     *
     */
    public Animal(double x, double y, double speed) {
        super(x, y, speed);
        lookAt = LOOKING_LEFT;
    }

    /**
     * getter look.
     * @return lookAt
     *
     */
    public int getLookAt() {
        return lookAt;
    }

    /**
     * setter lookAt.
     *@param lookAt .
     *
     */
    public void setLookAt(int lookAt) {
        this.lookAt = lookAt;
    }

}

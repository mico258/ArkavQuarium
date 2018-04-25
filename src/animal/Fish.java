/**
 * Package Animal.
 */
package animal;

import position.Coordinate;
import linkedlist.LinkedList;
import droppable.Koin;

import java.util.Random;


/**
 * abstract class fish.
 *
 */
public abstract class Fish extends Animal {
    /**
     * state kenyang .
     *
     */
    public static final int STATE_FULL = 700;
    /**
     * state mati .
     *
     */
    public static final int STATE_DEAD = 1000;
    /**
     * harga guppy.
     *
     */
    public static final int GUPPY_PRICE = 5;
    /**
     * harga piranha.
     *
     */
    public static final int PIRANHA_PRICE = 20;

    /**
     * masa hidup ikan.
     *
     */
    private int lifetime;
    /**
     * ikan saat kenyang.
     *
     */
    private int stillFull;
    /**
     * perhitungan kematian ikan.
     *
     */
    private int countingDead;
    /**
     * posisi ikan.
     *
     */
    private Coordinate coordinate;


    /**
     * constructor ikan.
     * @param x .
     * @param speed .
     * @param y  .
     */
    public Fish(double x, double y, double speed) {

        super(x, y, speed);
        setLifetime(0);
        setStillFull(STATE_FULL);
        setCountingDead(STATE_DEAD);
        setLookAt(LOOKING_RIGHT);
        coordinate = new Coordinate(x, y);

    }

    /**
     * getter life time.
     * @return lifetime
     *
     */
    public int getLifetime() {
        return lifetime;
    }
    /**
     * setter lifetime.
     * @param lifetime .
     *
     */
    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }
    /**
     * getter StillFull.
     *
     * @return stillFull
     *
     */
    public int getStillFull() {
        return stillFull;
    }
    /**
     * setter StillFull.
     * @param stillFull .
     *
     */

    public void setStillFull(int stillFull) {
        this.stillFull = stillFull;
    }

    /**
     * getter countingdead.
     * @return countingdead
     *
     */
    public int getCountingDead() {
        return countingDead;
    }

    /**
     * setter countingdead.
     * @param countingDead .
     *
     */
    public void setCountingDead(int countingDead) {
        this.countingDead = countingDead;
    }

    /**
     * getter coordinate.
     * @return coordinate
     *
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * setter coordinate.
     * @param coordinate .
     *
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * method randommove.
     *
     */
    public void randomMove() {
        double aa;
        double bb;
        Random r = new Random();
        aa = 0 + (620) * r.nextDouble();
        bb = 80 + (360 - 80) * r.nextDouble();

        if ((beetweenX(coordinate.getX(), 20) && beetweenY(coordinate.getY(), 20))
                && getX() > 0  && getX() < 640 && getY() > 80 && getY() < 440) {
            coordinate.setX(aa);
            coordinate.setY(bb);
        }

        if (coordinate.getX() < 20 && coordinate.getY() < 20) {
            coordinate.setX(320);
            coordinate.setY(240);
        }

        moveGeneral(coordinate.getX(), coordinate.getY());

        if (getX() < coordinate.getX()) {
            setLookAt(LOOKING_RIGHT);
        } else {
            setLookAt(LOOKING_LEFT);
        }
    }

    /**
     * getter noHungry.
     * @return notHungry
     *
     */
    public boolean notHungry() {
        return stillFull != 0;
    }

    /**
     * drop koin.
     * @param phase  .
     *@return Koin .
     */
    public Koin dropKoin(int phase) {
        return null;
    }

    /**
     * sync.
     * @param listKoin  .
     * @return int .
     *
     */
    public int synchronize(LinkedList<Koin> listKoin) {
        return 0;
    }
}

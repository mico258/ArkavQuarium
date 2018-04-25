package animal;

import linkedlist.LinkedList;
import droppable.Koin;

/**
 *Class Piranha.
 */
public class Piranha extends Fish {

    /**
     *constructor piranha .
     * @param x .
     * @param y .
     */
    public Piranha(double x, double y) {
        super(x, y, PIRANHA_SPEED);
    }

    /**
     *method eat .
     * @param listGuppy .
     * @param listKoin .
     */
    public void eat(LinkedList<Guppy> listGuppy, LinkedList<Koin> listKoin) {
        if (!listGuppy.isEmpty()) {
            int guppyIdx = getNearestGuppy(listGuppy);
            Guppy guppyTemp = listGuppy.get(guppyIdx);

            if (beetweenX(guppyTemp.getX(), 5)) {
                if (beetweenY(guppyTemp.getY(), 10)) {
                    listKoin.add(makeKoin(listGuppy.get(guppyIdx).getPhase()));
                    listGuppy.remove(guppyIdx);
                    setStillFull(STATE_FULL);
                    setCountingDead(STATE_DEAD);
                } else {
                    moveGeneral(guppyTemp.getX(), guppyTemp.getY());
                }
            } else {
                if (getX() < guppyTemp.getX()) {
                    moveGeneral(guppyTemp.getX(), guppyTemp.getY());
                    setLookAt(LOOKING_RIGHT);
                } else if (getX() > guppyTemp.getX()) {
                    moveGeneral(guppyTemp.getX(), guppyTemp.getY());
                    setLookAt(LOOKING_LEFT);
                } else {
                    randomMove();
                }
            }
        } else {
            randomMove();
        }
    }

    /**
     *method makekoin .
     * @param phase .
     * @return Koin .
     */
    public Koin makeKoin(int phase) {
        int x = GUPPY_PRICE * (phase + 1);
        return new Koin(getX(), getY(), x);
    }

    /**
     *getter nearestGuppy .
     * @param listGuppy .
     * @return integer .
     */
    public int getNearestGuppy(LinkedList<Guppy> listGuppy) {
        if (!listGuppy.isEmpty()) {
            int n = listGuppy.size();
            int guppyIdx;
            double distMin;
            double distCurr;

            guppyIdx = 0;
            distMin = distance2Point(listGuppy.get(0).getX(), listGuppy.get(0).getY(),
                    this.getX(), this.getY());

            for (int i = 1; i < n; i++) {
                distCurr = distance2Point(listGuppy.get(i).getX(), listGuppy.get(i).getY(),
                        this.getX(), this.getY());
                if (distCurr <= distMin) {
                    guppyIdx = i;
                    distMin = distCurr;
                }
            }
            return guppyIdx;
        } else {
            return 0;
        }
    }

    /**
     *getter syn .
     * @param listKoin .
     * @return integer .
     */
    public int synchronize(LinkedList<Koin> listKoin) {
        setLifetime((getLifetime() + 1) % 1000);
        if (notHungry()) {
            setStillFull(getStillFull() - 1);
            return 2;
            //fish move randomly
        } else {
            setCountingDead(getCountingDead() - 1);
            if (getCountingDead() == 0) {
                return 0;
                //fish is dead
            } else {
                return 1;
                //fish is dying
            }
        }
    }

    /**
     *getter imagePath .
     * @return String .
     */
    public String getImagePath() {
        String s = PATH_DRAWABLE +  "piranha";

        s += LOOKING_RIGHT == getLookAt() ? "_m" : "";

        s += ".png";

        return s;
    }

}

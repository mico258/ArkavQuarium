package animal;
import linkedlist.LinkedList;
import droppable.Koin;
import droppable.Food;

import static droppable.Koin.BASE_KOIN;
/**
 * Class Guppy .
 */

public class Guppy extends Fish {

    /**
     * koin  .
     */
    public static final int GUPPY_Koin = 3;
    /**
     * phase ke2 .
     */
    public static final int PHASE_2 = 5;
    /**
     * phase ke 3 .
     */
    public static final int PHASE_3 = 10;
    /**
     * phase max .
     */
    public static final int MAX_PHASE = 3;
    /**
     * koin value phase 1 .
     */
    public static final int Koin_VALUE_PHASE_1 = 1;
    /**
     * koin value phase 2 .
     */
    public static final int Koin_VALUE_PHASE_2 = 2;
    /**
     * koin value phase 3 .
     *
     */
    public static final int Koin_VALUE_PHASE_3 = 3;
    /**
     *waktu koin keluar .
     */
    public static final int Koin_PERIODIC = 400;

    /**
     *phase .
     */
    private int phase;
    /**
     *total makanan .
     */
    private int totalEatenFood;
    /**
     *nilai koin .
     */
    private int KoinValue;

    /**
     *constructor guppy .
     * @param y .
     * @param x .
     */

    public Guppy(double x, double y) {
        super(x, y, GUPPY_SPEED);
        setPhase(1);
        setTotalEatenFood(0);
        setKoinValue(Koin_VALUE_PHASE_1);
    }

    /**
     *methode eat .
     * @param listFood .
     */
    public void eat(LinkedList<Food> listFood) {
        if (!listFood.isEmpty()) {
            int foodIdx = getNearestFood(listFood);
            //int foodIdx = 0;
            Food foodTemp = listFood.get(foodIdx);

            if (beetweenX(foodTemp.getX(), 5)) {
                if (beetweenY(foodTemp.getY(), 10)) {
                    listFood.remove(foodIdx);
                    totalEatenFood++;
                    setStillFull(STATE_FULL);
                    setCountingDead(STATE_DEAD);
                    if (totalEatenFood == PHASE_2 || totalEatenFood == PHASE_3) {
                        nextPhase();
                    }
                } else {
                    moveGeneral(foodTemp.getX(), foodTemp.getY());
                }
            } else {
                if (getX() < foodTemp.getX()) {
                    moveGeneral(foodTemp.getX(), foodTemp.getY());
                    setLookAt(LOOKING_RIGHT);
                } else if (getX() > foodTemp.getX()) {
                    moveGeneral(foodTemp.getX(), foodTemp.getY());
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
     *make koin .
     * @param phase .
     * @return Koin .
     */
    public Koin makeKoin(int phase) {
        return new Koin(getX(), getY(), getKoinValue() * BASE_KOIN);
    }

    /**
     *method next phase .
     */
    public void nextPhase() {
        phase++;
        if (phase == 2) {
            setKoinValue(Koin_VALUE_PHASE_2);
        } else if (phase == 3) {
            setKoinValue(Koin_VALUE_PHASE_3);
        }
    }

    /**
     *get Food .
     * @param listFood .
     * @return int food .
     */

    public int getNearestFood(LinkedList<Food> listFood) {
        //return index of nearest Food
        if (!listFood.isEmpty()) {
            int n = listFood.size();
            int foodIdx;
            double distMin;
            double distCurr;

            foodIdx = 0;
            distMin = distance2Point(listFood.get(0).getX(), listFood.get(0).getY(), this.getX(), this.getY());

            for (int i = 1; i < n; i++) {
                distCurr = distance2Point(listFood.get(i).getX(), listFood.get(i).getY(), this.getX(), this.getY());
                if (distCurr <= distMin) {
                    foodIdx = i;
                    distMin = distCurr;
                }
            }
            return foodIdx;
        } else {
            return 0;
        }
    }

    /**
     *sync with koin .
     * @param listKoin .
     * @return int syn .
     */
    public int synchronize(LinkedList<Koin> listKoin) {
        setLifetime((getLifetime() + 1) % 1000);
        if (getLifetime() % Koin_PERIODIC == 0) {
            listKoin.add(makeKoin(0));
        }
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
     * @return string .
     */
    public String getImagePath() {
        String s = PATH_DRAWABLE +  "guppy";

        s += String.valueOf(getPhase());
        s += LOOKING_RIGHT == getLookAt() ? "_m" : "";

        s += ".png";

        return s;
    }

    /**
     *getter phase .
     * @return int .
     */
    public int getPhase() {
        return phase;
    }
    /**
     *setter phase .
     * @param phase .
     */

    public void setPhase(int phase) {
        this.phase = phase;
    }

    /**
     *getter totalfood .
     * @return int .
     */

    public int getTotalEatenFood() {
        return totalEatenFood;
    }

    /**
     *setter totalFood .
     * @param totalEatenFood .
     */

    public void setTotalEatenFood(int totalEatenFood) {
        this.totalEatenFood = totalEatenFood;
    }

    /**
     *getter koinValue .
     * @return int .
     */

    public int getKoinValue() {
        return KoinValue;
    }
    /**
     *setter KoinValue .
     * @param KoinValue .
     */

    public void setKoinValue(int KoinValue) {
        this.KoinValue = KoinValue;
    }
}

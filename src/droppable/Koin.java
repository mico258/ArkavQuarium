package droppable;

import position.Move;

/**
 *Class Koin .
 */
public class Koin extends Move {

    /**
     *
     *koin value .
     */
    public static  int BASE_KOIN = 10;

    /**
     *value .
     */
    private int value;

    /**
     *constructor Koin .
     * @param x .
     * @param y .
     * @param value .
     */
    public Koin(double x, double y, int value) {
        super(x, y, COIN_SPEED);
        this.value = value;
    }

    /**
     *getter Koin .
     * @return int .
     */
    public static int getBaseCoin() {
        return BASE_KOIN;
    }

    /**
     *setter Koin .
     * @param baseKoin .
     */
    public static void setBaseCoin(int baseKoin) {
        BASE_KOIN = baseKoin;
    }

    /**
     *getter value .
     * @return int .
     */
    public int getValue() {
        return value;
    }

    /**
     *setter value .
     * @param value  .
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     *getter imagePath .
     * @return String .
     */
    public String getImagePath() {
        return PATH_DRAWABLE + "koin.png";
    }
}

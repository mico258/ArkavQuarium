package droppable;

import position.Move;

public class Koin extends Move {

    public static int BASE_KOIN = 10;

    private int value;

    public Koin(double x, double y, int value) {
        super(x, y, COIN_SPEED);
        this.value = value;
    }

    public static int getBaseCoin() {
        return BASE_KOIN;
    }

    public static void setBaseCoin(int baseKoin) {
        BASE_KOIN = baseKoin;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getImagePath(){
        return PATH_DRAWABLE + "koin.png";
    }
}

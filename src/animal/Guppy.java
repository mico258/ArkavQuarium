package animal;
import linkedlist.LinkedList;
import draw.*;

public class Guppy extends Fish {

    public static int GUPPY_COIN = 3;
    public static int PHASE_2 = 5;
    public static int PHASE_3 = 10;
    public static int MAX_PHASE = 3;
    public static int COIN_VALUE_PHASE_1 = 1;
    public static int COIN_VALUE_PHASE_2 = 2;
    public static int COIN_VALUE_PHASE_3 = 3;
    public static int COIN_PERIODIC = 400;

    private int phase;
    private int totalEatenFood;
    private int coinValue;

    public Guppy(double x, double y) {
        super(x, y, GUPPY_SPEED);
        setPhase(1);
        setTotalEatenFood(0);
        setCoinValue(COIN_VALUE_PHASE_1);
    }

    public void eat(LinkedList<Food> listFood) {
        if (!listFood.isEmpty()) {
            int foodIdx = getNearestFood(listFood);
            //int foodIdx = 0;
            Food foodTemp = listFood.get(foodIdx);

            if (beetweenX(foodTemp.getX(),5)){
                if (beetweenY(foodTemp.getY(),10)){
                    listFood.remove(foodIdx);
                    totalEatenFood++;
                    setStillFull(STATE_FULL);
                    setCountingDead(STATE_DEAD);
                    if (totalEatenFood == PHASE_2 || totalEatenFood == PHASE_3) {
                        nextPhase();
                    }
                }else moveGeneral(foodTemp.getX(),foodTemp.getY());
            }else{
                if (getX() < foodTemp.getX()){
                    moveGeneral(foodTemp.getX(), foodTemp.getY());
                    setLookAt(LOOKING_RIGHT);
                }else if (getX() > foodTemp.getX()){
                    moveGeneral(foodTemp.getX(), foodTemp.getY());
                    setLookAt(LOOKING_LEFT);
                } else randomMove();
            }
        }else randomMove();
    }
    public Coin makeCoin(int phase) {
        return new Coin(getX(), getY(), getCoinValue() * BASE_COIN);
    }

    public void nextPhase() {
        phase++;
        if(phase == 2) {
            setCoinValue(COIN_VALUE_PHASE_2);
        } else if(phase == 3) {
            setCoinValue(COIN_VALUE_PHASE_3);
        }
    }

    public int getNearestFood(LinkedList<Food> listFood) {
        //return index of nearest Food
        if (!listFood.isEmpty()){
            int n = listFood.size();
            int foodIdx;
            double distMin, distCurr;

            foodIdx = 0;
            distMin = distance2Point(listFood.get(0).getX(), listFood.get(0).getY(), this.getX(), this.getY());

            for (int i = 1; i < n; i++){
                distCurr = distance2Point(listFood.get(i).getX(), listFood.get(i).getY(), this.getX(), this.getY());
                if (distCurr <= distMin) {
                    foodIdx = i;
                    distMin = distCurr;
                }
            }
            return foodIdx;
        }else return 0;
    }

    public int synchronize(LinkedList<Coin> listCoin) {
        setLifetime((getLifetime() + 1) % 1000);
        if (getLifetime() % COIN_PERIODIC == 0) {
            listCoin.add(makeCoin(0));
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

    public String getImagePath(){
        String s = PATH_DRAWABLE +  "guppy";

        s += String.valueOf(getPhase());
        s += LOOKING_RIGHT == getLookAt() ? "_m" : "";

        s += ".png";

        return s;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getTotalEatenFood() {
        return totalEatenFood;
    }

    public void setTotalEatenFood(int totalEatenFood) {
        this.totalEatenFood = totalEatenFood;
    }

    public int getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(int coinValue) {
        this.coinValue = coinValue;
    }
}

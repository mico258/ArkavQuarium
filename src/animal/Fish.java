package animal;

import position.Coordinate;
import linkedlist.LinkedList;
import droppable.Koin;

import java.util.Random;



public abstract class Fish extends Animal {

    public static int STATE_FULL = 700;
    public static int STATE_DEAD = 1000;
    public static int GUPPY_PRICE = 5;
    public static int PIRANHA_PRICE = 20;

    private int lifetime;
    private int stillFull;
    private int countingDead;
    private Coordinate coordinate;



    public Fish(double x, double y, double speed) {
        super(x, y, speed);
        setLifetime(0);
        setStillFull(STATE_FULL);
        setCountingDead(STATE_DEAD);
        setLookAt(LOOKING_RIGHT);
        coordinate = new Coordinate(x,y);
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public int getStillFull() {
        return stillFull;
    }

    public void setStillFull(int stillFull) {
        this.stillFull = stillFull;
    }

    public int getCountingDead() {
        return countingDead;
    }

    public void setCountingDead(int countingDead) {
        this.countingDead = countingDead;
    }


    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void randomMove(){
        double aa,bb;
        Random r = new Random();
        aa = 0 + (620) * r.nextDouble();
        bb = 80 + (360 - 80) * r.nextDouble();

        if ((beetweenX(coordinate.getX(), 20) && beetweenY(coordinate.getY(), 20)) && getX() > 0  && getX() < 640 && getY() > 80 && getY() < 440) {
            coordinate.setX(aa);
            coordinate.setY(bb);
        }

        if (coordinate.getX() < 20 && coordinate.getY() < 20){
            coordinate.setX(320);
            coordinate.setY(240);
        }

        moveGeneral(coordinate.getX(), coordinate.getY());

        if (getX() < coordinate.getX()){
            setLookAt(LOOKING_RIGHT);
        }else setLookAt(LOOKING_LEFT);
    }

    public boolean notHungry(){
        return stillFull != 0;
    }

    public Koin dropKoin(int phase){return null;}

    public int synchronize(LinkedList<Koin> listKoin){return 0;}
}

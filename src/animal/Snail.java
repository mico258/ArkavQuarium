package animal;

import droppable.Koin;
import linkedlist.LinkedList;

public class Snail extends Animal {


    public Snail(double x, double y) {
        super(x, y, SNAIL_SPEED);
    }

    public int eat(LinkedList<Koin> listKoin) {
        if (!listKoin.isEmpty()) {
            int KoinIdx = getNearestKoin(listKoin);
            Koin KoinTemp = listKoin.get(KoinIdx);
            if (beetweenX(KoinTemp.getX(),5)){
                if (beetweenY(KoinTemp.getY(),10)){
                    int x =  listKoin.get(KoinIdx).getValue();
                    listKoin.remove(KoinIdx);
                    return x;
                }
            }else{
                if (getX() < KoinTemp.getX()){
                    moveRight();
                    setLookAt(LOOKING_RIGHT);
                }else if (getX() > KoinTemp.getX()){
                    moveLeft();
                    setLookAt(LOOKING_LEFT);
                }
            }
        }
        return 0;
    }

    int getNearestKoin(LinkedList<Koin> listKoin) {
        if (!listKoin.isEmpty()){
            int n = listKoin.size();
            int KoinIdx;
            double distMin, distCurr;

            KoinIdx = 0;
            distMin = distance2Point(listKoin.get(KoinIdx).getX(), listKoin.get(KoinIdx).getY(), getX(), getY());

            for (int i = 1; i < n; i++){
                distCurr = distance2Point(listKoin.get(i).getX(), listKoin.get(i).getY(), getX(), getY());
                if (distCurr <= distMin) {
                    KoinIdx = i;
                    distMin = distCurr;
                }
            }
            return KoinIdx;
        } else {
            return 0;
        }
    }

    public String getImagePath(){
        if (getLookAt() == LOOKING_RIGHT) return PATH_DRAWABLE + "snail_m.png";
        else return PATH_DRAWABLE + "snail.png";
    }
}

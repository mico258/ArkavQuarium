package droppable;

import position.Move;

public class Food extends Move {

    public Food() {
        super(FISH_FOOD_SPEED);
    }

    public Food(double x, double y) {
        super(x, y, FISH_FOOD_SPEED);
    }

    public String getImagePath(){
        return PATH_DRAWABLE + "food.png";
    }
}

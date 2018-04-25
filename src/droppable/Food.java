package droppable;

import position.Move;

/**
 *class food .
 */
public class Food extends Move {

    /**
     * constructor Food .
     *
     */
    public Food() {
        super(FISH_FOOD_SPEED);
    }

    /**
     *constructor food .
     * @param y .
     * @param x .
     */
    public Food(double x, double y) {
        super(x, y, FISH_FOOD_SPEED);
    }

    /**
     *getter image .
     * @return String .
     */
    public String getImagePath(){
        return PATH_DRAWABLE + "food.png";
    }
}

package droppable;

import static org.junit.Assert.*;

import org.junit.Test;

public class FoodTest {

    @Test
    public void getPosition() throws Exception {
        Food foodTest = new Food(100, 100);
        assertEquals(100, foodTest.getX(),0.00);
        assertEquals(100, foodTest.getY(),0.00);
    }

    @Test
    public void setPosition() throws Exception {
        Food foodTest = new Food(100, 100);
        foodTest.setX(200);
        foodTest.setY(50);
        assertEquals(200, foodTest.getX(), 0.00);
        assertEquals(50, foodTest.getY(), 0.00);
    }
}
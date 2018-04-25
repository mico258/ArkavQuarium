package animal;

import org.junit.Test;

import static org.junit.Assert.*;

public class SnailTest {

    @Test
    public void getPosition() throws Exception {
        Snail snailTest = new Snail(100,100);
        assertEquals(100, snailTest.getX(), 0.00);
        assertEquals(100, snailTest.getY(), 0.00);
    }

    @Test
    public void setPosition() throws Exception {
        Snail snailTest = new Snail(100, 100);
        snailTest.setX(200);
        snailTest.setY(50);
        assertEquals(200, snailTest.getX(), 0.00);
        assertEquals(50, snailTest.getY(), 0.00);
    }
}
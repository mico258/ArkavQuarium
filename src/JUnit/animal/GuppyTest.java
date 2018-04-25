package animal;

import org.junit.Test;

import static org.junit.Assert.*;

public class GuppyTest {

    @Test
    public void getPosition() throws Exception {
        Guppy guppyTest = new Guppy(100,100);
        assertEquals(100, guppyTest.getX(), 0.00);
        assertEquals(100, guppyTest.getY(), 0.00);
    }

    @Test
    public void setPosition() throws Exception {
        Guppy guppyTest = new Guppy(100, 100);
        guppyTest.setX(200);
        guppyTest.setY(50);
        assertEquals(200, guppyTest.getX(), 0.00);
        assertEquals(50, guppyTest.getY(), 0.00);
    }

    @Test
    public void getPhase() throws Exception {
        Guppy guppyTest  = new Guppy(100,100);
        assertEquals(1, guppyTest.getPhase());
    }

    @Test
    public void setPhase() throws Exception {
        Guppy guppyTest  = new Guppy(100,100);
        guppyTest.setPhase(2);
        assertEquals(2, guppyTest.getPhase());
    }

    @Test
    public void getKoinValue() throws Exception {
        Guppy guppyTest = new Guppy(100,100);
        assertEquals(1, guppyTest.getKoinValue());
    }

    @Test
    public void setKoinValue() throws Exception {
        Guppy guppyTest = new Guppy(100, 100);
        guppyTest.setKoinValue(100);
        assertEquals(100, guppyTest.getKoinValue());
    }

    @Test
    public void getTotalEatenFood() throws Exception {
        Guppy guppyTest = new Guppy(100, 100);
        assertEquals(0, guppyTest.getTotalEatenFood());
    }

    @Test
    public void setTotalEatenFood() throws Exception {
        Guppy guppyTest = new Guppy(100, 100);
        guppyTest.setTotalEatenFood(200);
        assertEquals(200, guppyTest.getTotalEatenFood());
    }
}
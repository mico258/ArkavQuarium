package droppable;

import org.junit.Test;

import static org.junit.Assert.*;

public class KoinTest {

    @Test
    public void getPosition() throws Exception {
        Koin koinTest = new Koin(100, 100, 100);
        assertEquals(100, koinTest.getX(),0.00);
        assertEquals(100, koinTest.getY(),0.00);
    }

    @Test
    public void setPosition() throws Exception {
        Koin koinTest = new Koin(100, 100, 100);
        koinTest.setX(200);
        koinTest.setY(50);
        assertEquals(200, koinTest.getX(), 0.00);
        assertEquals(50, koinTest.getY(), 0.00);
    }

    @Test
    public void getKoinValue() throws Exception {
        Koin koinTest = new Koin(100,100, 200);
        assertEquals(200, koinTest.getValue());
    }

    @Test
    public void setKoinValue() throws Exception {
        Koin koinTest = new Koin(100, 100, 10);
        koinTest.setValue(200);
        assertEquals(200, koinTest.getValue());
    }
}
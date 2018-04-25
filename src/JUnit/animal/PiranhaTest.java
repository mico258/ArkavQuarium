package animal;

import org.junit.Test;

import static org.junit.Assert.*;

public class PiranhaTest {

    @Test
    public void getPosition() throws Exception {
        Piranha piranhaTest = new Piranha(100,100);
        assertEquals(100, piranhaTest.getX(), 0.00);
        assertEquals(100, piranhaTest.getY(), 0.00);
    }

    @Test
    public void setPosition() throws Exception {
        Piranha piranhaTest = new Piranha(100, 100);
        piranhaTest.setX(200);
        piranhaTest.setY(50);
        assertEquals(200, piranhaTest.getX(), 0.00);
        assertEquals(50, piranhaTest.getY(), 0.00);
    }
}
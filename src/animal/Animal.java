package animal;

import position.Move;

public class Animal extends Move{

    public static int LOOKING_LEFT = 0;
    public static int LOOKING_RIGHT = 1;

    private int lookAt;

    public Animal(double x, double y, double speed) {
        super(x, y, speed);
        lookAt = LOOKING_LEFT;
    }

    public int getLookAt() {
        return lookAt;
    }

    public void setLookAt(int lookAt) {
        this.lookAt = lookAt;
    }

}

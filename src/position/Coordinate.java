package position;

/**
 *Constructor Coordinate.
 */
public class Coordinate {
    /**
     *attribut x.
     *
     */
    private double x;
    /**
     *attribut y.
     *
     */
    private double y;

    /**
     *attribut Path.
     *
     */
    protected final String PATH_DRAWABLE =
            "C:\\Users\\ASUS ROG\\Desktop\\ArkavQuarium\\src\\draw\\";

    /**
     *Contructor Coordinate .
     */
    public Coordinate() {
        x = 0;
        y = 0;
    }

    /**
     *Contructor Coordinate .
     * @param y .
     * @param x .
     */
    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     *getter x .
     * @return double .
     */
    public double getX() {
        return x;
    }

    /**
     *setter x .
     * @param x  .
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     *getter y .
     * @return double .
     */
    public double getY() {
        return y;
    }

    /**
     *setter y .
     * @param y  .
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     *getter distance2point .
     * @param x1 .
     * @param x2 .
     * @param y1 .
     * @param y2 .
     * @return double .
     */

    public double distance2Point(double x1, double y1, double x2, double y2) {
        double distance;
        distance = Math.sqrt((Math.pow(x2 - x1, 2)) + (Math.pow(y2 - y1, 2)));
        return distance;
    }

    /**
     *getter area between x .
     * @param cX .
     * @param radius .
     * @return boolean .
     */
    public boolean beetweenX(double cX, double radius) {
        return ((cX <= (x + radius)) && (cX >= (x - radius)));
    }

    /**
     *getter area between y .
     * @param cY .
     * @param radius .
     * @return boolean .
     */
    public boolean beetweenY(double  cY, double radius) {
        return ((cY <= (y + radius)) && (cY >= (y - radius)));
    }

}

package position;

public class Coordinate {
    private double x;
    private double y;

    protected final String PATH_DRAWABLE = "C:\\Users\\ASUS ROG\\Desktop\\ArkavQuarium\\src\\draw\\";

    /**
     *
     */
    public Coordinate() {
        x = 0;
        y = 0;
    }

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distance2Point(double x1, double y1, double x2, double y2){
        double distance;
        distance = Math.sqrt((Math.pow(x2-x1,2)) + (Math.pow(y2-y1,2)));
        return distance;
    }

    public boolean beetweenX(double cX, double radius){
        return ((cX <= (x + radius)) && (cX >= (x - radius)));
    }

    public boolean beetweenY(double  cY, int radius){
        return ((cY <= (y + radius)) && (cY >= (y - radius)));
    }

}

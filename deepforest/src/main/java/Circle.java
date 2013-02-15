/**
 * @author oleksiy sayankin
 */
public class Circle {
    private Coord coord;
    private double r;

    public Circle(Coord coord, double r){
        this.coord = coord;
        this.r = r;
    }

    public double getX() {
        return coord.x();
    }

    public double getY() {
        return coord.y();
    }

    public double getR() {
        return r;
    }

    public void set(double x, double y, double r){
        coord.setX(x);
        coord.setY(y);
        this.r = r;
    }
}

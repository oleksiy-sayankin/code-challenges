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

    @Override
    public String toString(){
        return "{" + coord + ", r = " + r + "}";
    }

    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        if(!(other instanceof  Circle)){
            return false;
        }
        if(other == this){
            return true;
        }
        Circle otherCircle = (Circle)other;
        return this.coord.equals(otherCircle.coord) && MathHelper.equalsZero(this.r - otherCircle.r);
    }
}

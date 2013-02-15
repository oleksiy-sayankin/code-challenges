/**
 * @author oleksiy sayankin
 */
public class Coord {
    private double x;
    private double y;

    public Coord(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }


    public double x(){
        return x;
    }
    public double y(){
        return y;
    }

    @Override
    public boolean equals(Object other){
        if(other ==  null) {
            return false;
        }
        if(!(other instanceof Coord)){
            return false;
        }
        if(this == other){
            return true;
        }
        Coord otherCoord = (Coord) other;
        return MathHelper.equalsZero(otherCoord.x - this.x) && MathHelper.equalsZero(otherCoord.y() - this.y);
    }

    @Override
    public int hashCode(){
        return (int)x + (int)y;
    }

    @Override
    public String toString(){
        return "[ " + x + ",  " + y + "]";
    }

}

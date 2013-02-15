/**
 * @author oleksiy sayankin
 */
public final class MathHelper {
    private MathHelper(){}
    public static boolean equalsZero(double value) {
        return Math.abs(value) < Constants.ERROR;
    }
}

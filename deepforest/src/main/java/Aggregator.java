import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 @author oleksiy sayankin
 */
public class Aggregator {
    private List<CircleSegment> segments = new ArrayList<CircleSegment>();

    public void add(CircleSegment segment){
        CircleSegment normalizedSegment = new CircleSegment(segment);
        normalizedSegment.normalize();
        segments.add(normalizedSegment);
    }

    public List<CircleSegment> freeSegments(){
        if(segments.isEmpty()){
            return null;
        }
        List<CircleSegment> result = new ArrayList<CircleSegment>();
        double[] allPoints = allPoints();
        double start;
        double end;
        for(int i = 0; i <= allPoints.length - 2; i++){
            start = allPoints[i];
            end = allPoints[i + 1];
            addSegmentIfAllowed(result, start, end);
        }
        start = allPoints[allPoints.length - 1];
        end = allPoints[0] + 2 * Math.PI;
        addSegmentIfAllowed(result, start, end);
        return result;
    }

    private void addSegmentIfAllowed(List<CircleSegment> result, double start, double end) {
        double midpoint;
        midpoint = (start + end) / 2;
        if(isFreePoint(midpoint)){
            result.add(new CircleSegment(start, end));
        }
    }


    private double[] allPoints(){
        double[] result = new double[segments.size() * 2];
        int i = 0;
        for(CircleSegment segment : segments){
            result[i] = segment.getStart();
            i++;
            result[i] = segment.getEnd();
            i++;
        }
        Arrays.sort(result);
        return result;
    }

    private boolean isFreePoint(double point){
        for(CircleSegment segment : segments){
            if(segment.contains(point)) return false;
        }
        return true;
    }
}

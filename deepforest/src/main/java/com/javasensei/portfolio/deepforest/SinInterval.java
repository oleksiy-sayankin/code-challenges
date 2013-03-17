package com.javasensei.portfolio.deepforest;

/**
 * @author oleksiy sayankin
 */
public class SinInterval {
    private Sin startSin;
    private Sin endSin;

    public Sin getStartSin() {
        return startSin;
    }

    public void setStartSin(Sin startSin) {
        this.startSin = startSin;
    }

    public Sin getEndSin() {
        return endSin;
    }

    public void setEndSin(Sin endSin) {
        this.endSin = endSin;
    }

    public SinInterval(Sin startSin, Sin endSin) {
        if (Quadrant.FORTH.compareTo(startSin.getQuadrant()) < 0) {
            throw new IllegalArgumentException("Acceptable only 1th, 2nd, 3d and 4th quadrants. Start sin quadrant = " + startSin.getQuadrant());
        }
        if (Quadrant.FORTH.compareTo(endSin.getQuadrant()) < 0) {
            throw new IllegalArgumentException("Acceptable only 1th, 2nd, 3d and 4th quadrants. End sin quadrant = " + endSin.getQuadrant());
        }
        this.startSin = startSin.copy();
        this.endSin = endSin.copy();
        normalize();
    }

    public boolean contains(Sin sin) {
        Sin normalizedSin = sin.normalized();
        Sin shiftedOver2PiSin = normalizedSin.shiftedOver2Pi();
        boolean normalizedResult = trivialContains(normalizedSin);
        boolean shiftedOver2PiResult = trivialContains(shiftedOver2PiSin);
        return normalizedResult || shiftedOver2PiResult;
    }


    public boolean trivialContains(Sin sin) {
        return startSin.compareTo(sin) <= 0 && endSin.compareTo(sin) >= 0;
    }

    private void normalize() {
        if (startSin.compareTo(endSin) > 0) {
            switch (endSin.getQuadrant()) {
                case FIRST:
                    endSin.setQuadrant(Quadrant.FIFTH);
                    break;
                case SECOND:
                    endSin.setQuadrant(Quadrant.SIXTH);
                    break;
                case THIRD:
                    endSin.setQuadrant(Quadrant.SEVENTH);
                    break;
                case FORTH:
                    endSin.setQuadrant(Quadrant.EIGHT);
                    break;
            }
        }
    }

    public Sin innerSin() {
        Quadrant startQuadrant = startSin.getQuadrant();
        Quadrant endQuadrant = endSin.getQuadrant();
        double innerSinValue = 0;
        if (startQuadrant == endQuadrant) {
            innerSinValue = (startSin.getSinValue() + endSin.getSinValue()) / 2d;
            return new Sin(innerSinValue, startQuadrant);
        }

        int delta = Math.abs(Quadrant.getDelta(startQuadrant, endQuadrant));

        if (delta > 1) {
            Quadrant innerQuadrant = Quadrant.getInner(startQuadrant, endQuadrant);
            return new Sin(innerQuadrant);
        }
        double leftDistance = startSin.distanceToRightAxe();
        double rightDistance = endSin.distanceToLeftAxe();
        if(leftDistance < rightDistance){
            new Sin((endSin.getSinValue() + endSin.leftAxe()) / 2, endQuadrant);
        }
        return new Sin((startSin.getSinValue() + startSin.rightAxe()) / 2, startQuadrant);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof SinInterval)) {
            return false;
        }
        SinInterval otherSinInterval = (SinInterval) other;
        return this.startSin.equals(otherSinInterval.getStartSin()) && this.endSin.equals(otherSinInterval.getEndSin());
    }

    @Override
    public int hashCode() {
        return startSin.hashCode() * 31 + endSin.hashCode();
    }


    @Override
    public String toString() {
        return "{Start = " + startSin + ", End = " + endSin + "}";
    }
}

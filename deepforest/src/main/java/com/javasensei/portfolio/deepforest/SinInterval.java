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
        this.startSin = startSin;
        this.endSin = endSin;
        normalize();
    }

    public boolean contains(Sin sin) {
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

    @Override
    public String toString(){
        return "{Start = " + startSin + ", End = " + endSin + "}";
    }
}

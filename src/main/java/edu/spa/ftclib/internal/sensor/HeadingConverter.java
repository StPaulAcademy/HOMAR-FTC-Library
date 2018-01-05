package edu.spa.ftclib.internal.sensor;

/**
 * Created by Gabriel on 2018-01-02.
 */

public class HeadingConverter {
    private double lastHeading, thisHeading;
    private int rotations;
    public void update(double unconvertedHeading) {
        thisHeading = ((unconvertedHeading % (2*Math.PI))+2*Math.PI)%(2*Math.PI);    //Convert to a number in [0, 2pi)
        if (lastHeading > 3*Math.PI/2 && thisHeading < Math.PI/2) rotations++;
        else if (thisHeading > 3*Math.PI/2 && lastHeading < Math.PI/2) rotations--;
        lastHeading = thisHeading;
    }

    public double getConvertedHeading() {
        return thisHeading+2*Math.PI*rotations;
    }
}

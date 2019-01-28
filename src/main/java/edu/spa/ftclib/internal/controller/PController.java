package edu.spa.ftclib.internal.controller;

/**
 * Created by Gabriel on 2017-12-28.
 * A proportional controller. It sets KI and KD to zero, so that one only needs to worry about the proportional part.
 */

public class PController extends PIDController {
    /**
     * The constructor for the controller. Creates a PID controller with KD=KI=0.
     * @param KP The gain for the proportional part of the controller
     */
    public PController(double KP) {
        super(KP, 0, 0);
    }

    public PController(PController original, boolean copyState) {
        super(original, copyState);
    }

    public PController(PController original) {
        this(original, false);
    }
}

package edu.spa.ftclib.internal.controller;

/**
 * Created by Gabriel on 2017-12-28.
 * A PID Controller, but with the derivative gain set to zero, so that only the proportional and integral parts need to be worried about.
 */

public class PIController extends PIDController {
    /**
     * The constructor for the controller, sets KD to zero
     * @param KP The gain for the proportional part of the controller
     * @param KI The gain for the integral part of the controller
     */
    public PIController(double KP, double KI) {
        super(KP, KI, 0);
    }

    public PIController(PIController original, boolean copyState) {
        super(original, copyState);
    }

    public PIController(PIController original) {
        this(original, false);
    }
}

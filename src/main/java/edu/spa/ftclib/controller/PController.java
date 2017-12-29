package edu.spa.ftclib.controller;

/**
 * Created by Gabriel on 2017-12-28.
 */

public class PController extends PIDController {
    public PController(double KP) {
        super(KP, 0, 0);
    }
}

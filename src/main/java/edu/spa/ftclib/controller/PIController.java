package edu.spa.ftclib.controller;

/**
 * Created by Gabriel on 2017-12-28.
 */

public class PIController extends PIDController {
    public PIController(double KP, double KI) {
        super(KP, KI, 0);
    }
}

package edu.spa.ftclib.old_controller;

/**
 * Created by Gabriel on 2017-12-28.
 * Uses inputs (often from sensors) to help a system reach and maintain a specific target value.
 * It does this by receiving a target from the user, doing a lot of math, and sending the system an output.
 * The system then uses this output to change an attribute such as velocity or course in order to stay on or reach the target.
 */

public interface Controller {
    /**
     * Recieves a target value from the user.
     * @param target The value the system is trying to reach or maintain
     */
    void setTarget(double target);

    /**
     * Gets the target value the system is currently trying to reach or maintain.
     * @return The current target value
     */
    double getTarget();

    /**
     * After doing a lot of math, the controller uses the inputs to calculate a value which the system is able to understand
     * and use to change an attribute such as velocity or course.
     * @return a math
     */
    double output();
}

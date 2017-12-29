package edu.spa.ftclib.controller;

/**
 * Created by Gabriel on 2017-12-29.
 * A controller which is used when the action has an endpoint, such as making a turn or driving a specific distance.
 */

public interface FinishableController extends Controller {
    /**
     * Determines if the action is complete.
     * @return If the system is done
     */
    boolean finished();
}

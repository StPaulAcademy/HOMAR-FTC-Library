package edu.spa.ftclib.controller;

/**
 * Created by Gabriel on 2017-12-28.
 * A controller which finds its own inputs and then needs to be called frequently to update itself.
 */

public interface UpdateableController extends Controller {
    /**
     * Finds the inputs it needs and then does the fancy math to send a usable value back to the system.
     */
    void update();
}

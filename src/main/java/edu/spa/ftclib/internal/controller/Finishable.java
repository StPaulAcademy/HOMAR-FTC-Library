package edu.spa.ftclib.internal.controller;

//Created by Gabriel on 2018-01-02.

/**
 * Used for all controllers that reach a value and then stop and are considered finished.
 */

public interface Finishable {
    /**
     * Returns whether the controller has finished or not
     * @return If the controller has finished
     */
    boolean finished();
}

package edu.spa.ftclib.internal.activator;

//Created by Gabriel on 2017-12-28.

/**
 * Implement this in classes that can be activated and deactivated.
 */

public interface Activable {
    /**
     * Sets thing being activated to correct state using "TRUE" and "FALSE" commands
     * @param activated whether or not the thing being activated should be activated or not
     */
    void setActivated(boolean activated);

    /**
     * Returns whether the thing being activates is currently activated
     * @return whether the thing being activated is currently activated
     */
    boolean getActivated();

    /**
     * Sets the motor or servo to its activated state
     */
    void activate();

    /**
     * Sets the motor or servo to its deactivated state
     */
    void deactivate();
}

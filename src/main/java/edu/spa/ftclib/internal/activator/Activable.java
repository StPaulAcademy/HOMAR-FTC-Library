package edu.spa.ftclib.internal.activator;

/**
 * Created by Gabriel on 2017-12-28.
 * Implement this in classes that can be activated and deactivated.
 */

public interface Activable {
    /**
     *
     * @param activated whether or not the thing being activated should be activated or not
     */
    void setActivated(boolean activated);

    /**
     *
     * @return whether the thing being activated is currently activated
     */
    boolean getActivated();

    void activate();
    void deactivate();
}

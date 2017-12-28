package edu.spa.ftclib.activator;

/**
 * Created by Gabriel on 2017-12-28.
 * Implement this in classes that can be activated and deactivated.
 */

public interface Activable {
    void setActivated(boolean activated);
    boolean getActivated();
}

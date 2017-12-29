package edu.spa.ftclib.toggle;

/**
 * Created by Gabriel on 2017-10-31.
 * Use to implement a 0, 1, 2, ..., (maxToggle-1), 0, 1, 2, ... toggle that toggles (increments or revert to 0) when a button is pressed
 */

public class ToggleInt {
    private int toggle = 0; //Current state of the toggle
    private boolean previouslyActuated = false; //Was the actuator (whatever is passed to input) true last time?
    private final int maxToggle;
    public ToggleInt(int maxToggle) {   //Pass this the number of values you want the toggle to have. When the toggle is about to reach this number, it will go back to 0.
        this.maxToggle = maxToggle;
    }
    public void input(boolean currentlyActuated) {  //Call this method in your main op-mode loop; pass it the state of whatever actuator (eg. button) you want to use to control the toggle
        if (currentlyActuated && !previouslyActuated) toggle();
        previouslyActuated = currentlyActuated;
    }
    public void toggle() {  //Toggle the toggle (generally you shouldn't need this)
        toggle++;
        toggle %= maxToggle;
    }
    public void setToggle(int toggle) { //Set the toggle to a specific value (generally you shouldn't need this)
        this.toggle = toggle;
    }
    public int output() {   //Get the value of the toggle; call this whenever you need to use the toggle to control something
        return toggle;
    }
}

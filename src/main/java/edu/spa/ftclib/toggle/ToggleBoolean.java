package edu.spa.ftclib.toggle;

/**
 * Created by Gabriel on 10/31/2017.
 * Use to implement a true/false toggle that toggles when a button is pressed.
 */

public class ToggleBoolean {
    private boolean toggle = false; //Current state of the toggle
    private boolean previouslyActuated = false; //Was the actuator (whatever is passed to input) true last time?
    public void input(boolean currentlyActuated) {  //Call this method in your main op-mode loop; pass it the state of whatever actuator (eg. button) you want to use to control the toggle
        if (currentlyActuated && !previouslyActuated) toggle();
        previouslyActuated = currentlyActuated;
    }
    public void toggle() {  //Toggle the toggle (generally you shouldn't need this)
        toggle = !toggle;
    }
    public void setToggle(boolean toggle) { //Set the toggle to a specific value (generally you shouldn't need this)
        this.toggle = toggle;
    }
    public boolean output() {   //Get the value of the toggle; call this whenever you need to use the toggle to control something
        return toggle;
    }
}

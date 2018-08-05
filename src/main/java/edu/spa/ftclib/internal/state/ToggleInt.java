package edu.spa.ftclib.internal.state;

/**
 * Created by Gabriel on 2017-10-31.
 * Use to implement a 0, 1, 2, ..., (maxToggle-1), 0, 1, 2, ... toggle that toggles (increments or revert to 0) when a button is pressed
 * An example of when you might want to use this is when you have multiple motors that you might want the same button to control.
 * Use a switch in your code and for each value of the toggle, set a different motor to be used.
 */

public class ToggleInt {
    private int toggle; //Current state of the toggle
    private boolean previouslyPressed = false; //Was the input device (whatever is passed to input) true last time?
    private final int maxToggle; //the number of states that you want to toggle through

    /**
     *
     * @param maxToggle the number of states that you want to toggle through
     */
    public ToggleInt(int maxToggle) {   //Pass this the number of values you want the toggle to have. When the toggle is about to reach this number, it will go back to 0.
        this(maxToggle, 0);
    }

    public ToggleInt(int maxToggle, int startingToggle) {
        this.maxToggle = maxToggle;
        toggle = startingToggle;
    }

    /**
     * Call this method in your main op-mode loop
     * @param currentlyPressed the state of whatever actuator (eg. button) you want to use to control the toggle
     */
    public void input(boolean currentlyPressed) {
        //if the button is being pressed now and wasn't being pressed last time, change the value of the toggle
        if (currentlyPressed && !previouslyPressed) toggle();
        //change previoulyPressed to reflect the current state of the toggle
        previouslyPressed = currentlyPressed;
    }

    /**
     * Where the actual toggling happens. Generally you shouldn't need this
     */
    public void toggle() {
        toggle++;
        toggle %= maxToggle;
    }

    /**
     *
     * @param toggle the current state of the toggle
     */
    public void setToggle(int toggle) { //Set the toggle to a specific value (generally you shouldn't need this)
        this.toggle = toggle;
    }

    /**
     *
     * @return the current state of the toggle
     */
    public int output() {   //Get the value of the toggle; call this whenever you need to use the toggle to control something
        return toggle;
    }
}

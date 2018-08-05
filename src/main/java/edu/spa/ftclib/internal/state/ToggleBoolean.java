package edu.spa.ftclib.internal.state;

/**
 * Created by Gabriel on 2017-10-31.
 * Use to implement a true/false toggle that toggles when a button is pressed.
 */

public class ToggleBoolean {
    private boolean toggle = false; //Current state of the toggle
    private boolean previouslyPressed = false; //Was the input device (whatever is passed to input) true last time?

    public ToggleBoolean() {
    }

    public ToggleBoolean(boolean startingToggle) {
        toggle = startingToggle;
    }

    /**
     * Call this method in your main op-mode loop
     * @param currentlyPressed the state of the input device you want to use to control the toggle
     */
    public void input(boolean currentlyPressed) {
        //if the button is currently being pressed and and wasn't being pressed the last time through the loop, change the toggle
        if (currentlyPressed && !previouslyPressed) toggle();
        previouslyPressed = currentlyPressed; //change previouslyPressed to reflect the current state of the toggle
    }

    /**
     * Changes the value of the toggle variable to false if it was true and true if it was false
     */
    public void toggle() {  //Toggle the toggle (generally you shouldn't need this)
        toggle = !toggle;
    }

    /**
     *
     * @param toggle holds the current state of the toggle
     */
    public void setToggle(boolean toggle) { //Set the toggle to a specific value (generally you shouldn't need this)
        this.toggle = toggle;
    }

    /**
     *
     * @return the current state of the toggle
     */
    public boolean output() {   //Get the value of the toggle; call this whenever you need to use the toggle to control something
        return toggle;
    }
}

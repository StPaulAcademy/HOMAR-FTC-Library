package edu.spa.ftclib.internal.state;

/**
 * Created by Gabriel on 2017-10-31.
 * Use to implement a toggle that toggles between values in the toggleValues array when a button is pressed
 */

public class ToggleDouble {
    private int toggleIndex;    //Current state of the toggle
    private boolean previouslyPressed = false; //Was the input device (whatever is passed to input) true last time?
    private final double[] toggleValues; //creates the array of values to toggle through

    /**
     *
     * @param toggleValues the array of values to toggle through
     */
    public ToggleDouble(double[] toggleValues) {
        this(toggleValues, 0);
    }

    public ToggleDouble(double[] toggleValues, int startingIndex) {    //Pass this an array of the values you want the toggle to cycle between. It will start at element 0, work its way through sequentially, then go back to element 0.
        this.toggleValues = toggleValues;
        toggleIndex = startingIndex;
    }

    /**
     *
     * Call this method in your main op-mode loop
     *
     * @param currentlyPressed the current state of the button that controls the toggle
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
        toggleIndex++;
        toggleIndex %= toggleValues.length;
    }

    /**
     *
     * @param toggleIndex the index of the array that the toggle is currently set to
     */
    public void setToggleIndex(int toggleIndex) {   //Set the toggle to a specific value, specified by the index of that value in the toggleValues array (generally you shouldn't need this)
        this.toggleIndex = toggleIndex;
    }

    /**
     *
     * @return the index of the array that the toggle is currently set to
     */
    public double output() {    //Get the value of the toggle; call this whenever you need to use the toggle to control something
        return toggleValues[toggleIndex];
    }
}

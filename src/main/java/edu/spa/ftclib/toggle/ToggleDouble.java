package edu.spa.ftclib.toggle;

/**
 * Created by Gabriel on 2017-10-31.
 * Use to implement a toggle that toggles between values in the toggleValues array when a button is pressed
 */

public class ToggleDouble {
    private int toggleIndex = 0;    //Current state of the toggle
    private boolean previouslyActuated = false; //Was the actuator (whatever is passed to input) true last time?
    private final double[] toggleValues;
    public ToggleDouble(double[] toggleValues) {    //Pass this an array of the values you want the toggle to cycle between. It will start at element 0, work its way through sequentially, then go back to element 0.
        this.toggleValues = toggleValues;
    }
    public void input(boolean currentlyActuated) {  //Call this method in your main op-mode loop; pass it the state of whatever actuator (eg. button) you want to use to control the toggle
        if (currentlyActuated && !previouslyActuated) toggle();
        previouslyActuated = currentlyActuated;
    }
    public void toggle() {  //Toggle the toggle (generally you shouldn't need this)
        toggleIndex++;
        toggleIndex %= toggleValues.length;
    }
    public void setToggleIndex(int toggleIndex) {   //Set the toggle to a specific value, specified by the index of that value in the toggleValues array (generally you shouldn't need this)
        this.toggleIndex = toggleIndex;
    }
    public double output() {    //Get the value of the toggle; call this whenever you need to use the toggle to control something
        return toggleValues[toggleIndex];
    }
}

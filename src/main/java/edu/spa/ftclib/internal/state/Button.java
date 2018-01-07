package edu.spa.ftclib.internal.state;

/**
 * Created by 19Gabrielmk on 11/20/2017.
 * Use to keep track of when a button is pressed, released, held, clicked, etc.
 */

public class Button {
    private boolean previouslyPressed = false; //Was the input device (whatever is passed to input) true last time?
    private boolean pressed = false;
    private boolean onPress = false;
    private boolean onRelease = false;

    /**
     * Call this method in your main op-mode loop; pass it the state of whatever input device (eg. a physical button) you want to use to control the button
     * @param currentlyPressed Is the input device (a physical button or something) pressed?
     */
    public void input(boolean currentlyPressed) {
        pressed = currentlyPressed;
        if (currentlyPressed && !previouslyPressed) onPress = true;
        if (!currentlyPressed && previouslyPressed) onRelease = true;
        previouslyPressed = currentlyPressed;
    }

    /**
     * Test for this if you want to write code that will run once when the button is pressed.
     * @return Whether the button has just been pressed
     */
    public boolean onPress() {
        if (onPress) {
            onPress = false;
            return true;
        }
        return false;
    }

    /**
     * Test for this if you want to write code that will run once when the button is released.
     * @return Whether the button has just been released
     */
    public boolean onRelease() {
        if (onRelease) {
            onRelease = false;
            return true;
        }
        return false;
    }

    /**
     * Test for this if you want to write code that will run whenever the button is pressed (not just once).
     * @return Whether the button is pressed or not
     */
    public boolean isPressed() {
        return pressed;
    }
}

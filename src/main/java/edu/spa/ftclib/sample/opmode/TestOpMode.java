package edu.spa.ftclib.sample.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import edu.spa.ftclib.sample.TestClass;

/**
 * Created by Gabriel on 2018-01-09.
 */

@Disabled
@Autonomous(name = "Test Op-Mode", group = "sample")

public class TestOpMode extends OpMode {
    /**
     * User defined init method
     * <p>
     * This method will be called once when the INIT button is pressed.
     */
    @Override
    public void init() {
        telemetry.addData("test in init", TestClass.test());
    }

    /**
     * User defined loop method
     * <p>
     * This method will be called repeatedly in a loop while this op mode is running
     */
    @Override
    public void loop() {
        telemetry.addData("test in loop", TestClass.test());
    }
}

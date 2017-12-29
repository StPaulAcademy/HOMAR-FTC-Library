package edu.spa.ftclib.controller;

import com.qualcomm.robotcore.hardware.IntegratingGyroscope;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by Gabriel on 2017-12-28.
 * A controller when using an integrating Gyroscope, much as the built-in BNO055 on the REV Hub.
 * It gets the heading from the sensor for the controller to use.
 */

public class IntegratingGyroscopeController extends HeadingController {
    /**
     * Creates the IntegratingGyroscope.
     */
    IntegratingGyroscope gyro;

    /**
     * Constructs the gyro controller.
     * @param gyro What you named your gyro in your hardwareMap
     * @param algorithm What kind of controller you want to use
     *                  @see PIDController
     *                  @see PIController
     *                  @see PController
     */
    public IntegratingGyroscopeController(IntegratingGyroscope gyro, ControlAlgorithm algorithm) {
        super(algorithm);
        this.gyro = gyro;
    }

    /**
     * Gets the current heading from the sensor.
     * @return The robot heading so that it can be passed to the controller
     */
    @Override
    public double getSensorValue() {
        return gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.RADIANS).firstAngle;
    }
}

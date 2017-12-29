package edu.spa.ftclib.controller;

import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;

/**
 * Created by Gabriel on 2017-12-28.
 * A controller when using a gyro sensor, much as the modern robotics one.
 * It converts the value from the gyro to radians for the controller to use.
 */

public class GyroSensorController extends HeadingController {
    /**
     * Creates the gyro sensor.
     */
    GyroSensor gyro;

    /**
     * Constructs the gyro controller.
     * @param gyro What you named your gyro in your hardwareMap
     * @param algorithm What kind of controller you want to use
     *                  @see PIDController
     *                  @see PIController
     *                  @see PController
     */
    public GyroSensorController(GyroSensor gyro, ControlAlgorithm algorithm) {
        super(algorithm);
        this.gyro = gyro;
    }

    /**
     * Converts the value in degrees from the sensor to radians so that the controller can use it.
     * @return The heading from the gyro in radians
     */
    @Override
    public double getSensorValue() {
        return gyro.getHeading()*-Math.PI/180;
    }
}

package edu.spa.ftclib.old_controller;

import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by Gabriel on 2017-12-28.
 */

public class GyroSensorController extends HeadingController {
    GyroSensor gyro;
    public GyroSensorController(GyroSensor gyro, ControlAlgorithm algorithm) {
        super(algorithm);
        this.gyro = gyro;
    }

    @Override
    public double getSensorValue() {
        return gyro.getHeading()*-Math.PI/180;
    }
}

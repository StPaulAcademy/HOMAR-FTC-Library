package edu.spa.ftclib.controller;

import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;

/**
 * Created by Gabriel on 2017-12-28.
 */

public class GyroSensorController extends SensorController {
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

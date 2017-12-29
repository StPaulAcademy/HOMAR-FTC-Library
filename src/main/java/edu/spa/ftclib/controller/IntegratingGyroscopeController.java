package edu.spa.ftclib.controller;

import com.qualcomm.robotcore.hardware.IntegratingGyroscope;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by Gabriel on 2017-12-28.
 */

public class IntegratingGyroscopeController extends SensorController {
    IntegratingGyroscope gyro;
    public IntegratingGyroscopeController(IntegratingGyroscope gyro, ControlAlgorithm algorithm) {
        super(algorithm);
        this.gyro = gyro;
    }

    @Override
    public double getSensorValue() {
        return gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.RADIANS).firstAngle;
    }
}

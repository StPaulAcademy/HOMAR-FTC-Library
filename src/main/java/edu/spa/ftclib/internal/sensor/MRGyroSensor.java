package edu.spa.ftclib.internal.sensor;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * Created by Gabriel on 2018-01-02.
 */

public class MRGyroSensor implements DerivativeSensor {
    private ModernRoboticsI2cGyro gyro;
    public MRGyroSensor(ModernRoboticsI2cGyro gyro) {
        this.gyro = gyro;
    }
    @Override
    public double getValue() {
        return gyro.getIntegratedZValue();
    }

    @Override
    public double getDerivative() {
        return gyro.getAngularVelocity(AngleUnit.RADIANS).zRotationRate;
    }
}

package edu.spa.ftclib.sensor;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;

/**
 * Created by Gabriel on 2018-01-02.
 */

public class MRGyroSensor implements Sensor {
    private ModernRoboticsI2cGyro gyro;
    MRGyroSensor(ModernRoboticsI2cGyro gyro) {
        this.gyro = gyro;
    }
    @Override
    public double getValue() {
        return gyro.getIntegratedZValue();
    }
}

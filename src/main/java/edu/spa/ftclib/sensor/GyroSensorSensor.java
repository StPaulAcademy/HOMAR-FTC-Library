package edu.spa.ftclib.sensor;

import com.qualcomm.robotcore.hardware.GyroSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by Gabriel on 2018-01-02.
 */

public class GyroSensorSensor implements Sensor {
    GyroSensor gyro;
    HeadingConverter converter = new HeadingConverter();
    GyroSensorSensor(GyroSensor gyro) {
        this.gyro = gyro;
    }
    @Override
    public double getValue() {
        converter.update(-gyro.getHeading()*Math.PI/180);
        return converter.getConvertedHeading();
    }
}

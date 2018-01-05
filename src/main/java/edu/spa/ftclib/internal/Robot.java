package edu.spa.ftclib.internal;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Gabriel on 2017-12-29.
 * We find it helpful to have a single "hardware robot" class with all the sensors, servos, etc. that make up a robot, so we don't have to initialize them in every op-mode.
 * It's also helpful to include the hardwareMap and telemetry.
 * If you're using the drivetrain part of our library, you'll also want a drivetrain.
 * You may find it helpful to extend this class to create your own "hardware robot" class.
 */

public class Robot {
    public Telemetry telemetry;
    public HardwareMap hardwareMap;

    public Robot(Telemetry telemetry, HardwareMap hardwareMap) {
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;
    }
}

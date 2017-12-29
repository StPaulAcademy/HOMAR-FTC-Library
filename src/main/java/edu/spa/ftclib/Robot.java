package edu.spa.ftclib;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import edu.spa.ftclib.drivetrain.Drivetrain;

/**
 * Created by Gabriel on 2017-12-29.
 * We find it helpful to have a single "hardware robot" class with all the sensors, servos, etc. that make up a robot, so we don't have to initialize them in every op-mode.
 * It's also helpful to include the hardwareMap and telemetry.
 * If you're using the drivetrain part of our library, you'll also want a drivetrain.
 * You may find it helpful to extend this class to create your own "hardware robot" class.
 */

public class Robot {
    public Drivetrain drivetrain;   //TODO: maybe don't include this?
    public Telemetry telemetry;
    public HardwareMap hardwareMap;

    Robot(Drivetrain drivetrain, Telemetry telemetry, HardwareMap hardwareMap) {
        this.drivetrain = drivetrain;
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;
    }
}

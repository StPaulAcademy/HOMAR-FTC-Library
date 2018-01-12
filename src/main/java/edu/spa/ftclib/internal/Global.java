package edu.spa.ftclib.internal;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Gabriel on 2018-01-12.
 * A container for a {@link HardwareMap} and a {@link Telemetry} object.
 * These are normally only accessible inside an {@link com.qualcomm.robotcore.eventloop.opmode.OpMode}, but sometimes, for testing, it is helpful to be able to access them elsewhere without passing the reference.
 * Set the field or fields you want to use within your OpMode's init method, and then use Global.hardwareMap and Global.telemetry in any code that runs afterwards.
 * This should probably only be used for testing, and you should probably make sure the field you want to use is non-null before you use it.
 * If you want to use telemetry or a {@link HardwareMap} in permanent (non-testing) code, you should probably pass it as an argument to your constructor or method.
 */

public class Global {
    public static HardwareMap hardwareMap;
    public static Telemetry telemetry;
}

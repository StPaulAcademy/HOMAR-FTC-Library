package edu.spa.ftclib.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Gabriel on 2017-12-27.
 * A four-wheel, standard Mecanum drivetrain.
 */

public class MecanumDrivetrain extends HolonomicFourWheelDrivetrain {
    public MecanumDrivetrain(DcMotor[] motors) {
        super(motors, new double[] {
                Math.PI/4, Math.PI/4, -Math.PI/4, -Math.PI/4
        });
    }

    @Override
    double calculateWheelCoefficient(double course, double wheelAngle) {
        return Math.cos(course)-Math.sin(course)/Math.tan(wheelAngle);
    }
}

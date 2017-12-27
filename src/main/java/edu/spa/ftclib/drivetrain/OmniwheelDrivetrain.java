package edu.spa.ftclib.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Gabriel on 2017-12-27.
 * A four-wheel, standard omnidirectional wheel drivetrain.
 */

public class OmniwheelDrivetrain extends HolonomicFourWheelDrivetrain {
    public OmniwheelDrivetrain(DcMotor[] motors) {
        super(motors, new double[] {
                Math.PI/4, -Math.PI/4, 3*Math.PI/4, -3*Math.PI/4
        });
    }

    @Override
    double calculateWheelCoefficient(double course, double wheelAngle) {
        return Math.cos(wheelAngle-course);
    }
}

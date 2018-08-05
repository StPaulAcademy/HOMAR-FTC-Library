package edu.spa.ftclib.internal.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Gabriel on 2017-12-27.
 * A four-wheel, standard Mecanum drivetrain.
 */

public class MecanumDrivetrain extends HolonomicFourWheelDrivetrain {

    /**
     *
     * @param motors the array of motors that you give the constructor so that it can find the hardware
     */
    public MecanumDrivetrain(DcMotor[] motors) {
        super(motors, new double[] {
                -3*Math.PI/4, 3*Math.PI/4, -Math.PI/4, Math.PI/4
        });
    }

    /**
     *
     * @param course the angle that you want the robot to move along
     * @param wheelAngle the angle of the actual moving part of the wheel
     * @return a number between zero and one, which says what percentage of the speed a wheel should move at. Is then multiplied by the velocity
     */
    @Override
    double calculateWheelCoefficient(double course, double wheelAngle) {
        return (Math.cos(course)-Math.sin(course)/Math.tan(wheelAngle))*Math.signum(wheelAngle); //fancy math that calculates the wheel coefficient
    }
}

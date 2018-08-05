package edu.spa.ftclib.internal.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Gabriel on 2017-12-27.
 * A four-wheel, standard omnidirectional wheel drivetrain.
 */

public class OmniwheelDrivetrain extends HolonomicFourWheelDrivetrain {

    /**
     * @param motors the array of motors that you give the constructor so that it can find the hardware
     */
    public OmniwheelDrivetrain(DcMotor[] motors, boolean diagonal) {    //TODO: instead of diagonal/not, take a number for wheel angle (default can be diagonal)
        super(motors, diagonal ? new double[]{
                Math.PI / 4, -Math.PI / 4, 3 * Math.PI / 4, -3 * Math.PI / 4 //the wheel angles on an omniwheel drivetrain
        } : new double[]{
                0, -Math.PI/2, -Math.PI, Math.PI/2 //the wheel angles on an omniwheel drivetrain
        });
    }

    /**
     * @param course     the angle that you want the robot to move along
     * @param wheelAngle the array of angles that the actual moving parts of the wheels are at
     * @return a number between zero and one, which says what percentage of the speed a wheel should move at. Is then multiplied by the velocity
     */
    @Override
    double calculateWheelCoefficient(double course, double wheelAngle) {
        return Math.cos(wheelAngle-course); //simple math to calculate the wheel coefficient
    }
}

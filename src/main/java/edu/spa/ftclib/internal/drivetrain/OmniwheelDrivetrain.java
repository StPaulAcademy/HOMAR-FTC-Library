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
    public OmniwheelDrivetrain(DcMotor[] motors) {  //This constructor assumes your wheels are mounted diagonally. If they are instead mounted parallel and perpendicular to what you want to define as "forwards," you can use the other constructor with a wheelAngle of Math.PI.
        this(motors, 3*Math.PI/4);
    }
    public OmniwheelDrivetrain(DcMotor[] motors, double wheelAngle) {   //wheelAngle is the angle of the first wheel. The wheels should be in counterclockwise order starting with the wheel immediately counterclockwise of the "front" of your robot (if there is a wheel mounted right at the front of your robot, it should be last).
        super(motors, new double[]{
                wheelAngle, wheelAngle + Math.PI / 2, wheelAngle + Math.PI, wheelAngle + 3 * Math.PI / 2
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

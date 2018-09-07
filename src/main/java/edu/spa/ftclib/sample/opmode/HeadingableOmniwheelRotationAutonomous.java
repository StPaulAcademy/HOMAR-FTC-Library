package edu.spa.ftclib.sample.opmode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMUImpl;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import edu.spa.ftclib.internal.controller.ErrorTimeThresholdFinishingAlgorithm;
import edu.spa.ftclib.internal.controller.FinishableIntegratedController;
import edu.spa.ftclib.internal.controller.PIDController;
import edu.spa.ftclib.internal.drivetrain.HeadingableOmniwheelDrivetrain;
import edu.spa.ftclib.internal.sensor.IntegratingGyroscopeSensor;

/**
 * Created by Michaela on 1/3/2018.
 * Tested and found fully functional by Gabriel on 2018-8-6.
 */

@Disabled
@Autonomous(name = "Headingable Omniwheel Rotation Autonomous", group = "sample")

public class HeadingableOmniwheelRotationAutonomous extends LinearOpMode {
    public DcMotor drive1;
    public DcMotor drive2;
    public DcMotor drive3;
    public DcMotor drive4;

    public HeadingableOmniwheelDrivetrain drivetrain;

    public FinishableIntegratedController controller;

    public BNO055IMUImpl imu;

    /**
     * Override this method and place your code here.
     * <p>
     * Please do not swallow the InterruptedException, as it is used in cases
     * where the op mode needs to be terminated early.
     *
     * @throws InterruptedException
     */
    @Override
    public void runOpMode() throws InterruptedException {   //Notice that this is almost the exact same code as in HeadingableOmniwheelRotationAutonomous.
        drive1 = hardwareMap.get(DcMotor.class, "drive1");
        drive2 = hardwareMap.get(DcMotor.class, "drive2");
        drive3 = hardwareMap.get(DcMotor.class, "drive3");
        drive4 = hardwareMap.get(DcMotor.class, "drive4");

        imu = hardwareMap.get(BNO055IMUImpl.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        //Add calibration file?
        parameters.loggingEnabled = true;   //For debugging
        parameters.loggingTag = "IMU";      //For debugging
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();  //Figure out why the naive one doesn't have a public constructor
        imu.initialize(parameters);
        while (!imu.isGyroCalibrated());

        PIDController pid = new PIDController(1.5, 0.05, 0);
        pid.setMaxErrorForIntegral(0.002);

        controller = new FinishableIntegratedController(new IntegratingGyroscopeSensor(imu), pid, new ErrorTimeThresholdFinishingAlgorithm(Math.PI/50, 1));
        drivetrain = new HeadingableOmniwheelDrivetrain(new DcMotor[]{drive1, drive2, drive3, drive4}, controller);
        for (DcMotor motor : drivetrain.motors) motor.setDirection(DcMotor.Direction.REVERSE);  //Depending on the design of the robot, you may need to comment this line out.

        waitForStart();

        drivetrain.setTargetHeading(Math.PI/2);
        drivetrain.rotate();
        sleep(1000);

        drivetrain.setTargetHeading(-Math.PI/2);
        while (drivetrain.isRotating()) {
            drivetrain.updateHeading();
            telemetry.addData("Heading", drivetrain.getCurrentHeading());
            telemetry.update();
        }
        sleep(1000);

        drivetrain.setTargetHeading(0);
        while (opModeIsActive()) drivetrain.updateHeading();
    }
}

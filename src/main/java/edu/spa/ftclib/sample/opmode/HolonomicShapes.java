package edu.spa.ftclib.sample.opmode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMUImpl;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import edu.spa.ftclib.internal.controller.ErrorTimeThresholdFinishingAlgorithm;
import edu.spa.ftclib.internal.controller.FinishableIntegratedController;
import edu.spa.ftclib.internal.controller.PIDController;
import edu.spa.ftclib.internal.drivetrain.Drivetrain;
import edu.spa.ftclib.internal.drivetrain.Extrinsicable;
import edu.spa.ftclib.internal.drivetrain.Headingable;
import edu.spa.ftclib.internal.drivetrain.HeadingableMecanumDrivetrain;
import edu.spa.ftclib.internal.drivetrain.HeadingableOmniwheelDrivetrain;
import edu.spa.ftclib.internal.drivetrain.HolonomicFourWheelDrivetrain;
import edu.spa.ftclib.internal.drivetrain.MecanumDrivetrain;
import edu.spa.ftclib.internal.drivetrain.OmniwheelDrivetrain;
import edu.spa.ftclib.internal.sensor.IntegratingGyroscopeSensor;

/**
 * Created by Gabriel on 2018-6-10.
 * Tested and found functional with headingable Mecanum by Gabriel on 2018-8-4.
 * A demo of the positionable aspect of the holonomic classes that also shows how these classes can be used polymorphically
 * Technically tele-op, but only to choose the robot type (and thus can easily be made autonomous)
 * Controls: X for an omniwheel robot with no IMU, Y for a Mecanum robot with no IMU, A for an omniwheel robot with an IMU, B for a Mecanum robot with an imu
 */

@Disabled
@TeleOp(name = "Holonomic Shapes", group = "sample")

public class HolonomicShapes extends LinearOpMode {
    private HolonomicFourWheelDrivetrain drivetrain;
    private BNO055IMUImpl imu;
    private boolean spin;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Drivetrain", drivetrain);
        telemetry.addData("Status", "Ready to start");
        telemetry.update();

        waitForStart();
        telemetry.addData("Status", "Waiting for a button to be pressed");
        telemetry.update();
        while (!gamepad1.x && !gamepad1.y && !gamepad1.a && !gamepad1.b) ;

        if (gamepad1.x) drivetrain = getDrivetrainForType(HolonomicDriveType.OMNIWHEEL);
        else if (gamepad1.y) drivetrain = getDrivetrainForType(HolonomicDriveType.MECANUM);
        else if (gamepad1.a)
            drivetrain = getDrivetrainForType(HolonomicDriveType.HEADINGABLE_OMNIWHEEL);
        else drivetrain = getDrivetrainForType(HolonomicDriveType.HEADINGABLE_MECANUM);
        spin = drivetrain instanceof Headingable && drivetrain instanceof Extrinsicable;  //Show off if we can
        if (spin) ((Extrinsicable) drivetrain).setExtrinsic(true);


        double driveDistance = drivetrain.getTicksPerUnit() * 2;
        double circleSeconds = 5;

        drivetrain.setVelocity(0.25);
        for (int i = 0; i < 4; i++) {   //Square by encoders
            spin();
            telemetry.addData("Status", "positioning square " + i);
            telemetry.update();
            drivetrain.setCourse(-Math.PI / 2 - Math.PI / 2 * i);
            drivetrain.setTargetPosition(driveDistance);
            drivetrain.position();
        }
        spin();
        drivetrain.setVelocity(0);

        sleep(2000);
        if (spin) drivetrain.setRotation(0.05);
        sleep(3000);

        drivetrain.setVelocity(0.5);
        ElapsedTime timer = new ElapsedTime();  //Circle by time
        while (timer.seconds() < circleSeconds) {
            telemetry.addData("Status", "positioning circle " + timer.seconds() / circleSeconds);
            telemetry.update();
            drivetrain.setCourse(-Math.PI / 2 - 2 * Math.PI * timer.seconds() / circleSeconds);
        }
        drivetrain.setVelocity(0);
        sleep(3000);
        drivetrain.setRotation(0);
    }

    private void spin() {
        telemetry.addData("Status", "spinning");
        telemetry.update();
        if (!spin) return;
        drivetrain.setVelocity(0);
        ((Headingable) drivetrain).setTargetHeading(((Headingable) drivetrain).getTargetHeading() + Math.PI / 4);
        ((Headingable) drivetrain).rotate();
        drivetrain.setVelocity(0.25);
    }

    private HolonomicFourWheelDrivetrain getDrivetrainForType(HolonomicDriveType type) {    //This method basically encapsulates configuration information for four different robots; it can be edited based on robot hardware configuration. A lot of code is repeated to demonstrate that the four different configurations are completely independent (apart from the IMU)
        DcMotor[] motors;
        switch (type) {
            case OMNIWHEEL:
                motors = new DcMotor[]{
                        hardwareMap.get(DcMotor.class, "drive1"),
                        hardwareMap.get(DcMotor.class, "drive2"),
                        hardwareMap.get(DcMotor.class, "drive3"),
                        hardwareMap.get(DcMotor.class, "drive4")
                };
                for (DcMotor motor : motors) motor.setDirection(DcMotor.Direction.REVERSE);
                return new OmniwheelDrivetrain(motors);
            case MECANUM:
                motors = new DcMotor[]{
                        hardwareMap.get(DcMotor.class, "driveFrontLeft"),
                        hardwareMap.get(DcMotor.class, "driveFrontRight"),
                        hardwareMap.get(DcMotor.class, "driveBackLeft"),
                        hardwareMap.get(DcMotor.class, "driveBackRight")
                };
                for (DcMotor motor : motors) motor.setDirection(DcMotor.Direction.REVERSE);
                return new MecanumDrivetrain(motors);
            case HEADINGABLE_OMNIWHEEL:
                initializeImu();
                motors = new DcMotor[]{
                        hardwareMap.get(DcMotor.class, "drive1"),
                        hardwareMap.get(DcMotor.class, "drive2"),
                        hardwareMap.get(DcMotor.class, "drive3"),
                        hardwareMap.get(DcMotor.class, "drive4")
                };
                for (DcMotor motor : motors) motor.setDirection(DcMotor.Direction.REVERSE);

                PIDController pidO = new PIDController(1.5, 0.05, 0);
                pidO.setMaxErrorForIntegral(0.002);
                FinishableIntegratedController controllerO = new FinishableIntegratedController(new IntegratingGyroscopeSensor(imu), pidO, new ErrorTimeThresholdFinishingAlgorithm(Math.PI / 25, 1));

                return new HeadingableOmniwheelDrivetrain(motors, controllerO);
            case HEADINGABLE_MECANUM:
                initializeImu();
                motors = new DcMotor[]{
                        hardwareMap.get(DcMotor.class, "driveFrontLeft"),
                        hardwareMap.get(DcMotor.class, "driveFrontRight"),
                        hardwareMap.get(DcMotor.class, "driveBackLeft"),
                        hardwareMap.get(DcMotor.class, "driveBackRight")
                };
                for (DcMotor motor : motors) motor.setDirection(DcMotor.Direction.REVERSE);

                PIDController pidM = new PIDController(1.5, 0.05, 0);
                pidM.setMaxErrorForIntegral(0.002);
                FinishableIntegratedController controllerM = new FinishableIntegratedController(new IntegratingGyroscopeSensor(imu), pidM, new ErrorTimeThresholdFinishingAlgorithm(Math.PI / 25, 1));
                HolonomicFourWheelDrivetrain drivetrain = new HeadingableMecanumDrivetrain(motors, controllerM);

                for (DcMotor motor : motors) ((DcMotorEx) motor).setTargetPositionTolerance((int)(drivetrain.getTicksPerUnit()/10));    //Lower the precision of the positioning (robot-specific)

                return drivetrain;
        }
        return null;    //This should never happen (all cases are accounted for and return a drivetrain)
    }

    private void initializeImu() {
        imu = hardwareMap.get(BNO055IMUImpl.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        //Add calibration file?
        parameters.loggingEnabled = true;   //For debugging
        parameters.loggingTag = "IMU";      //For debugging
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();  //Figure out why the naive one doesn't have a public constructor
        imu.initialize(parameters);
        while (!imu.isGyroCalibrated()) ;
    }

    private enum HolonomicDriveType {
        MECANUM, OMNIWHEEL, HEADINGABLE_MECANUM, HEADINGABLE_OMNIWHEEL
    }
}

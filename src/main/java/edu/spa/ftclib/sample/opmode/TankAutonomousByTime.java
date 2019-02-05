package edu.spa.ftclib.sample.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import edu.spa.ftclib.internal.drivetrain.TankDrivetrain;
import edu.spa.ftclib.sample.robot.BNO055HolonomicBot;

/**
 * Created by Gabriel on 2019-02-03
 * An introduction to using simple drivetrains. Written for beginners; no sensors or advanced coding techniques used. Once you understand the concepts, a lot of this can be done in fewer lines of code by using more methods, loops, etc.
 * Tested and found fully functional by Gabriel on 2019-2-4.
 */

@Autonomous(name = "Tank Autonomous By Time", group = "sample")
public class TankAutonomousByTime extends LinearOpMode {
    TankDrivetrain drivetrain;
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor left = hardwareMap.get(DcMotor.class, "driveLeft");
        DcMotor right = hardwareMap.get(DcMotor.class, "driveRight");
        right.setDirection(DcMotor.Direction.REVERSE);
        drivetrain = new TankDrivetrain(left, right);
        waitForStart();

        telemetry.addData("phase", "Movement forwards and backwards");
        telemetry.addData("status", "running");
        telemetry.update();
        drivetrain.setVelocity(1);    //Forwards
        sleep(1000);
        drivetrain.setVelocity(-1);   //Backwards
        sleep(1000);
        drivetrain.setVelocity(0);
        telemetry.addData("status", "done with phase");
        telemetry.update();
        sleep(2000);

        telemetry.addData("phase", "Rotation in place");
        telemetry.addData("status", "running");
        telemetry.update();
        drivetrain.setRotation(1);          //Counterclockwise
        sleep(1000);
        drivetrain.setRotation(-1);         //Clockwise
        sleep(1000);
        drivetrain.setRotation(0);
        telemetry.addData("status", "done with phase");
        telemetry.update();
        sleep(2000);

        telemetry.addData("phase", "Rotation while moving");
        telemetry.addData("status", "running");
        telemetry.update();
        drivetrain.setRotation(0.125);
        drivetrain.setVelocity(0.25);
        sleep(1000);
        drivetrain.setRotation(-0.125);
        drivetrain.setVelocity(-0.25);
        sleep(1000);
        drivetrain.setRotation(-0.125);
        drivetrain.setVelocity(0.25);
        sleep(1000);
        drivetrain.setRotation(0.125);
        drivetrain.setVelocity(-0.25);
        sleep(1000);
        drivetrain.setRotation(0);
        drivetrain.setVelocity(0);
        telemetry.addData("status", "done with phase");
        telemetry.update();
        sleep(2000);

        telemetry.addData("phase", "Manual motor control (here we do a wave-type thing)");
        telemetry.addData("status", "running");
        telemetry.update();
        double timePerWave = 3;
        int waves = 4;
        for (int i = 0; i < waves; i++) {
            ElapsedTime timer = new ElapsedTime();
            while (timer.seconds() < timePerWave) {
                double leftPower = Math.cos(2*Math.PI*timer.seconds()/timePerWave)/4;
                double rightPower = Math.sin(2*Math.PI*timer.seconds()/timePerWave)/4;
                drivetrain.left.setPower(leftPower);
                drivetrain.right.setPower(rightPower);
            }
        }
        telemetry.addData("status", "done with op-mode");
    }
}

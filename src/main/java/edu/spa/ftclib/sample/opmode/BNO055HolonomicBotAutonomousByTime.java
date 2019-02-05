package edu.spa.ftclib.sample.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import edu.spa.ftclib.sample.robot.BNO055HolonomicBot;

/**
 * Created by Gabriel on 2019-02-03
 * An introduction to using drivetrains and Robots. Written for beginners; no sensors or advanced coding techniques used. Once you understand the concepts, a lot of this can be done in fewer lines of code by using more methods, loops, etc.
 */

@Autonomous(name = "BNO055 Holonomic Bot Autonomous By Time", group = "sample")
public class BNO055HolonomicBotAutonomousByTime extends LinearOpMode {
    BNO055HolonomicBot robot;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new BNO055HolonomicBot(telemetry, hardwareMap);
        waitForStart();

        telemetry.addData("phase", "Movement forwards, backwards, and side to side");
        telemetry.addData("status", "running");
        telemetry.update();
        robot.drivetrain.setVelocity(1);
        robot.drivetrain.setCourse(0);              //Forwards
        sleep(1000);
        robot.drivetrain.setCourse(Math.PI/2);      //Left
        sleep(1000);
        robot.drivetrain.setCourse(Math.PI);        //Backwards
        sleep(1000);
        robot.drivetrain.setCourse(3*Math.PI/2);    //Right (-Math.PI/2 would work too)
        sleep(1000);
        robot.drivetrain.setVelocity(0);
        telemetry.addData("status", "done with phase");
        telemetry.update();
        sleep(2000);

        telemetry.addData("phase", "Diagonal movement");
        telemetry.addData("status", "running");
        telemetry.update();
        robot.drivetrain.setVelocity(0.5);
        robot.drivetrain.setCourse(Math.PI/4);      //Diagonal forwards/left
        sleep(1000);
        robot.drivetrain.setCourse(3*Math.PI/4);    //Diagonal backwards/left
        sleep(1000);
        robot.drivetrain.setCourse(5*Math.PI/4);    //Diagonal backwards/right (-3*Math.PI/4 would work too)
        sleep(1000);
        robot.drivetrain.setCourse(7*Math.PI/4);    //Diagonal forwards/right (-Math.PI/4 would work as well)
        sleep(1000);
        robot.drivetrain.setVelocity(0);
        telemetry.addData("status", "done with phase");
        telemetry.update();
        sleep(2000);

        telemetry.addData("phase", "Movement in any direction (here we go in a circle)");
        telemetry.addData("status", "running");
        telemetry.update();
        ElapsedTime timer = new ElapsedTime();
        robot.drivetrain.setVelocity(0.25);
        double timeForLoop = 5;
        while (timer.seconds() < timeForLoop) {
            double course = 2*Math.PI*timer.seconds()/timeForLoop;
            robot.drivetrain.setCourse(course);
        }
        robot.drivetrain.setVelocity(0);
        telemetry.addData("status", "done with phase");
        telemetry.update();
        sleep(2000);

        telemetry.addData("phase", "Rotation in place");
        telemetry.addData("status", "running");
        telemetry.update();
        robot.drivetrain.setRotation(1);          //Counterclockwise
        sleep(1000);
        robot.drivetrain.setRotation(-1);         //Clockwise
        sleep(1000);
        robot.drivetrain.setRotation(0);
        telemetry.addData("status", "done with phase");
        telemetry.update();
        sleep(2000);

        telemetry.addData("phase", "Rotation while moving");
        telemetry.addData("status", "running");
        telemetry.update();
        robot.drivetrain.setRotation(0.25);
        robot.drivetrain.setVelocity(0.5);
        robot.drivetrain.setCourse(0);
        sleep(1000);
        robot.drivetrain.setRotation(-0.25);
        robot.drivetrain.setCourse(Math.PI/2);
        sleep(1000);
        robot.drivetrain.setRotation(0.25);
        robot.drivetrain.setCourse(Math.PI);
        sleep(1000);
        robot.drivetrain.setRotation(-0.25);
        robot.drivetrain.setCourse(3*Math.PI/2);
        sleep(1000);
        robot.drivetrain.setRotation(0);
        robot.drivetrain.setVelocity(0);
        telemetry.addData("status", "done with op-mode");
    }
}

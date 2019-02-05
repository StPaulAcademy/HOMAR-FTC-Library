<img src="https://github.com/StPaulAcademy/HOMAR-FTC-Library/blob/master/doc/HOMAR_Logo.png?raw=true" alt="HOMAR Logo" width="333" height="269"></img>
# HOMAR-FTC-Library
Quick links: [HOMAR website](https://stpaulacademy.github.io/HOMAR-FTC-Library/); [Javadoc reference](https://stpaulacademy.github.io/HOMAR-FTC-Library/doc/javadoc/); [GitHub Page](https://github.com/StPaulAcademy/HOMAR-FTC-Library); [Spartan Robotics website](https://sites.google.com/spa.edu/spartan-robotics/). Keep reading for a description and installation instructions.
## Welcome!
This is the home of HOMAR (Holonomic Omniwheel and Mecanum, Autonomy, and Response), a third-party library that simplifies many of the programming tasks in the [*FIRST* Tech Challenge](https://www.firstinspires.org/robotics/ftc). HOMAR was created by Gabriel Konar-Steenberg and Michaela Polley, working on [St. Paul Academy and Summit School](https://www.spa.edu) FTC teams #11117 and #12660 Spartan Robotics.
## Creation story
**In the beginning, there was only chaos…**  
After our first season, we realized there were a lot of repetitive programming tasks that could be coded once and for all so we didn't have to messily keep rewriting them. This included things like servos that only ever moved to two positions, buttons and toggles for tele-op, and the somewhat involved mathematical algorithms behind the PID controller and holonomic (omniwheel and Mecanum) drivetrains. We decided to create a library to do all of that in a nicely-organized, object-oriented, modular, and extensible way, recognizing that we would need to add more bits and pieces to the library as time went on. In the spirit of gracious professionalism and in the belief that we do our best work when working together, we decided to publish our code so other teams could use it and improve it.
## Tasks HOMAR makes easier
Here is a list of all the things HOMAR can do:
* Drivetrains
    * Object-oriented class structure to encapsulate all the motors, etc. in a drivetrain, reduce clutter, and standardize usage
    * Built-in support for tank, omniwheel, and Mecanum drivetrains
    * Extensibility so you can create your own drivetrains while keeping the benefits of our drivetrain code
    * Integration with the controller package (see below) to provide precise control of heading
    * Integration with encoders to provide precise control of position
    * An innovation we call "extrinsic course control" that makes it easy for a holonomic robot to go in the same direction no matter which way it is facing
* Controllers
    * Ready-to-use PID controller code (we learned calculus so you don't have to! :))
    * Integration with the drivetrain package (see above) and the sensor package (see below), including:
        * Support for sensors (e.g. an integrating gyro) that provide both a value and the derivative of that value over time
        * Code for tasks (e.g. rotating a robot) that need some way of determining how close to the target value is close enough (we call this a "finishing algorithm")
    * As always, an extensible framework that allows you to build your own controllers, finishing algorithms, etc.
* Sensors
    * An object-oriented structure that makes it easy to integrate sensors with the controller package (see above)
    * Built-in support for many heading sensors, including the Modern Robotics gyro sensor and the IMU in the REV Expansion Hub
    * A helper class that begins to tame the myriad number of ways to refer to angles
    * Of course the ability to add your own sensors (which we will likely use soon to add support for ultrasonic sensors)
* Tele-op controls
    * An extremely useful button class that makes it very simple to complete a task either when a gamepad button is pushed or when it is released
    * A set of toggle classes that allow drivers to use a button to toggle robot functions (either on/off, between a number of integer values, or between a number of floating-point constants)
    * A set of "activators" that simplify the common task of "activating" a servo, continuous rotation servo, or motor by moving it to a specific position or spinning it at a specific speed and then "deactivating" it by moving it to a rest position or turning it off
* Miscellaneous
    * A PID tuner op-mode you can adapt to tune PID controllers for your drivetrains
    * A class to hold instances of the hardwareMap and telemetry so you don't have to keep passing them around during testing
    * Simple logging code to write files containing log data (there's only so much you can fit onto that telemetry screen)
    * A robot class to be used as a base to extend and add drivetrains, etc.
    * A way to disable the @Disabled annotation for testing
* Samples! Sometimes the best documentation is an example in code, so we've included dozens of samples demonstrating how to use our library

If this sounds useful to your team, go ahead and install it! Keep in mind that this library is a work-in-progress. The core features are fairly well-developed (i.e. they work), but we plan to add more samples, improve the Javadoc reference, write more tutorials, and add even more features.
## Installation
### Using Git
**If you're already using [Git](https://en.wikipedia.org/wiki/Git) to manage your code, we recommend this method, as it will make it easy to install updates to the library (which we plan to publish frequently) and relatively easy for your teammates to use the library once you've installed it.**

#### Part A: Once Per Repository
To install HOMAR in your team's repository, follow these steps:
1. Go to the command line and `cd` to your project folder
1. Test by running `git status`; you should see some information about your Git setup
1. Run `git submodule init`
1. Run `git submodule add https://github.com/StPaulAcademy/HOMAR-FTC-Library HOMAR-FTC-Library`
1. Open your project in Android Studio and choose `File -> Sync with File System`
1. Choose `File -> Sync Project with Gradle Files`
1. Click “Add root” in the box that appears about an “unregistered VCS root”
1. Open `settings.gradle` and add this line: `include 'HOMAR-FTC-Library'`
1. Open the `build.release.gradle` file under `TeamCode` (or wherever you will be writing your code) and add this line: `implementation project(':HOMAR-FTC-Library')` within the curly brackets
1. Repeat step 9 for the `build.release.gradle` file under `FtcRobotController`
1. Repeat step 6
1. To test, go to `HOMAR-FTC-Library/java/edu.spa.ftclib/sample/opmode` and copy `TestOpMode.java` to `TeamCode/java/org.firstinspires.ftc.teamcode` (or wherever your store your op-modes)
1. Open the copy of `TestOpMode.java` you just made and verify that there are no import errors. As an additional test, you can try running `TestOpMode` on a robot.
1. Finally, commit and push your changes. You’re done!

#### Part B: Once Per Computer
Once you have installed HOMAR in your team’s repository, you’ll need to follow these steps for every other computer you will be programming on (not counting the one you used in part A):
1. Once you or a teammate has completed Part A from your repository, update your project to get those changes
1. Choose `File -> Sync Project with Gradle Files`; there should be an error about not being able to find `:HOMAR-FTC-Library`
1. Go to the command line and `cd` to your project folder
1. Test by running `git status`; you should see some information about your Git setup
1. Run `git submodule init`; this should output `Submodule 'HOMAR-FTC-Library' … registered for path 'HOMAR-FTC-Library'`
1. Delete the `HOMAR-FTC-Library` folder in your project folder
1. Run `git submodule update`
1. Open your project in Android Studio and choose `File -> Sync with File System`
1. Click “Add root” in the box that appears about an “unregistered VCS root”
1. Choose `File -> Sync Project with Gradle Files`
1. If the Gradle sync in step 10 fails, restart Android Studio and redo the Gradle sync.
1. Click on `Git: …` at the bottom-right of the Android Studio window, then click `HOMAR-FTC-Library`
1. Click on `Master` under `Local Branches`, then click `Checkout`
1. Test by opening `TestOpMode.java` in `TeamCode/java/org.firstinspires.ftc.teamcode` (or wherever your store your op-modes) and verifying that there are no import errors
1. Try to commit and try to update — neither should detect any changes

#### Updating
If you're using Git from the command line, you can update HOMAR by `cd`-ing to the HOMAR-FTC-Library folder and running `git pull`.

If you're using Android Studio's built-in version control tools, the `Update Project` command will automatically update HOMAR as well as your own code. We're not planning to introduce any catastrophic updates; however, we wouldn't want to break some finely-tuned code right before one of your competitions. We're researching the best way to disable this auto-updating; in the meantime, here are a few workarounds so you can update on your schedule, not ours:
* If you use `VCS -> Git -> Pull…` instead of the `Update Project` command, you can choose to update only your own repository
* If you want to install an older version of HOMAR temporarily, you can look up the hash of the most recent commit you want (go [here](https://github.com/StPaulAcademy/HOMAR-FTC-Library/commits/master) and click the clipboard icon of the commit you want to copy the hash), then click on `Git: …` at the bottom-right of the Android Studio window, then `HOMAR-FTC-Library`, then `Checkout Tag or Revision…`, then paste in your hash. To go back to the most recent version, follow steps 12-13 under "Part B: Once Per Computer" (above) and then update.
* If neither of those work for you, you can always install the library the old-fashioned way without using Git (see below)


### Not using Git
**If you aren't using Git, don't know what Git is, or are using Android Studio's version control tools and don't want to deal with avoiding auto-updating, you should use this method.**

 1. Go to (https://github.com/StPaulAcademy/HOMAR-FTC-Library), click `Clone or download`, and click `Download ZIP`
 1. Unzip the resulting download. You should have a folder containing `src`, `README.md`, etc.; rename this folder `HOMAR-FTC-Library`
 1. Move the folder you just renamed into your FTC project folder (the one that contains `TeamCode`, `FtcRobotController`, etc.)
 1. Follow steps 5-13 under “Part A: Once per repository” above, skipping step 7
 
 To update the library, repeat steps 1-3 (step 3 will have you replace the old library folder with the new one, so you should back up this folder if you think you might want to go back to it). Then open your project in Android Studio and choose `File -> Sync with File System`; finally, choose `File -> Sync Project with Gradle Files`. There should be no need to repeat step 4.

## Documentation

The official Javadoc reference is, perhaps more than the rest of the library, a work-in-progress, so check back if you don't see what you need. Also try looking at our code samples, included with the library, which should demonstrate how to use most (if not all) its features. Finally, Spartan Robotics has a website at https://sites.google.com/spa.edu/spartan-robotics/ where you can find more general FTC tutorials and more information about our robotics teams.

You can find the Javadoc reference for the most recent version of the library at https://stpaulacademy.github.io/HOMAR-FTC-Library/doc/javadoc/. If you have installed the library, you can find the reference for your version in the `/doc/javadoc` folder within the library folder you downloaded.

The files in the `/doc` folder aren't meant to be edited directly. If you have made changes to the Javadoc comments within the library code and would like these to be reflected in the HTML Javadoc reference, you can use Android Studio's built-in terminal to run the command `./gradlew HOMARJavadoc` (Mac/Linux) or `gradlew HOMARJavadoc` (Windows). (On Mac/Linux, you may have to first give yourself execute permissions with the command `chmod +x gradlew`.)

## Changelog
### 1.1 (released 2019-2-5):
* **Fixed** Non-Git installation instructions and added detail to Git installation instructions
* **Fixed** TankDrivetrain positioning formula
* **Fixed** Inexplicable line of PIDController.java
* **Added** Derivative limiting implementation for PID controller
* **Added** Integral limiting functionality for PID controller
* **Added** Ability to set a heading offset for Extrinsicable drivetrains
* **Added** Copy constructors for PIDController, PIController, PController, and ErrorTimeThresholdFinishingAlgorithm
* **Added** Getters and setters for ErrorTimeThresholdFinishingAlgorithm
* **Added** TankAutonomousByTime sample
* **Added** BNO055HolonomicBotAutonomousByTime sample

…and miscellaneous minor changes
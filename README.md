# HOMAR-FTC-Library
Quick links: [HOMAR website](https://stpaulacademy.github.io/HOMAR-FTC-Library/); [Javadoc reference](https://stpaulacademy.github.io/HOMAR-FTC-Library/doc/javadoc/); [Spartan Robotics website](https://sites.google.com/spa.edu/spartan-robotics/). Keep reading for a description and installation instructions.
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
**If you're already using [Git](https://en.wikipedia.org/wiki/Git) to manage your code, we recommend this method, as it will make it easy to install updates to the library (which we plan to publish frequently).**

To be written.
### Not using Git
**If you aren't using Git (or if you don't know what Git is), you should use this method.**

Also to be written.
## Reference
You can find our Javadoc reference at https://stpaulacademy.github.io/HOMAR-FTC-Library/doc/javadoc/. The reference is, perhaps more than the rest of the library, a work-in-progress, so check back if you don't see what you need. Also try looking at our code samples, included with the library, which should demonstrate how to use most (if not all) its features. Finally, Spartan Robotics has a website at https://sites.google.com/spa.edu/spartan-robotics/ where you can find more general FTC tutorials and more information about our robotics teams.
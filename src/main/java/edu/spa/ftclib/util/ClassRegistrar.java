package edu.spa.ftclib.util;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;

/**
 * Created by Gabriel on 2018-01-04.
 * May be used to register an op-mode class marked with {@link com.qualcomm.robotcore.eventloop.opmode.Autonomous} or {@link com.qualcomm.robotcore.eventloop.opmode.TeleOp}.
 * Automatically gets the op-mode name and group.
 * <p />
 * We use this to test our samples, which we must disable (with {@link com.qualcomm.robotcore.eventloop.opmode.Disabled}) so they don't all automatically install for you.
 * Unless you have a special case like that, you probably shouldn't use this class; you should just not disable the op-modes you want to run and let FTC's code automatically register them.
 * <p />
 * To use, go to {@code FtcOpModeRegister.java} in FtcRobotController -> java -> org.firstinspires.ftc.robotcontroller -> internal.
 * In the {@code register} method, create a new ClassRegistrar with the given {@link OpModeManager}.
 * Then you can call {@link #registerClass} for each op-mode class you want to enable.
 * If you want to enable a whole package, use {@link PackageRegistrar}.
 * <p />
 * Inspired by {@link org.firstinspires.ftc.robotcore.internal.opmode.AnnotatedOpModeClassFilter}.
 * @see PackageRegistrar
 */

public class ClassRegistrar {
    private OpModeManager manager;
    ClassRegistrar(OpModeManager manager) {
        this.manager = manager;
    }

    /**
     * Register a given class as an op-mode.
     * The class must extend {@link OpMode}, but other than that it is assumed you have made sure it is actually a valid op-mode.
     * The {@link com.qualcomm.robotcore.eventloop.opmode.Disabled} annotation is ignored.
     * If the class does not have a {@link TeleOp} or {@link Autonomous} annotation, the op-mode is registered as tele-op.
     * @param clazz The class to register
     */
    //So I don't actually know Java reflection very well. I'm just good at following patterns (hence the "inspired by AnnotatedOpModeClassFilter"). Tell me if I'm doing anything wrong. Here we go…
    public void registerClass(Class<OpMode> clazz) {
        OpModeMeta.Flavor flavor = OpModeMeta.Flavor.TELEOP;
        String name = "";
        String group = OpModeMeta.DefaultGroup;
        if (clazz.isAnnotationPresent(TeleOp.class)) {
            TeleOp annotation = clazz.getAnnotation(TeleOp.class);
            name = annotation.name();
            group = annotation.group();
        }
        else if (clazz.isAnnotationPresent(Autonomous.class)) {
            flavor = OpModeMeta.Flavor.AUTONOMOUS;
            Autonomous annotation = clazz.getAnnotation(Autonomous.class);
            name = annotation.name();
            group = annotation.group();
        }
        if (name.trim().equals("")) name = clazz.getSimpleName();
        manager.register(new OpModeMeta(name, flavor, group), clazz);
    }
}

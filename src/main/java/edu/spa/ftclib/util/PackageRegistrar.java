package edu.spa.ftclib.util;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.util.ClassUtil;

import org.firstinspires.ftc.robotcore.internal.opmode.ClassFilter;
import org.firstinspires.ftc.robotcore.internal.opmode.ClassManager;

/**
 * Created by Gabriel on 2018-01-04.
 * May be used to register a package of op-modes marked with {@link com.qualcomm.robotcore.eventloop.opmode.Disabled}.
 * We use this to test our samples, which we must disable so they don't all automatically install for you.
 * Unless you have a special case like that, you probably shouldn't use this class; you should just not disable the op-modes you want to run.
 * <p />
 * To use, go to {@code FtcOpModeRegister.java} in FtcRobotController -> java -> org.firstinspires.ftc.robotcontroller -> internal.
 * In the {@code register} method, create a new PackageRegistrar with the given {@link OpModeManager}.
 * Then you can call {@link #registerPackage} for each package of op-modes you want to enable.
 * If you only want to enable a single class, use {@link ClassRegistrar}.
 * <p />
 * Inspired by {@link org.firstinspires.ftc.robotcore.internal.opmode.AnnotatedOpModeClassFilter}.
 * @see ClassRegistrar
 */

public class PackageRegistrar {
    private OpModeManager manager;
    public PackageRegistrar(OpModeManager manager) {
        this.manager = manager;
    }
    public void registerPackage(String packageName) {
        ClassManager.getInstance().registerFilter(new PackageClassFilter(packageName, manager));
        ClassManager.getInstance().processAllClasses();
    }

    /**
     * A class that will be given every class that could be an op-mode.
     * @see ClassFilter
     */
    class PackageClassFilter implements ClassFilter {
        String packageName;
        ClassRegistrar classRegistrar;
        private PackageClassFilter(String packageName, OpModeManager manager) {
            this.packageName = packageName;
            classRegistrar = new ClassRegistrar(manager);
        }

        /**
         * Clears the result of any previous filtering in preparation for further filtering
         */
        @Override
        public void filterAllClassesStart() {
            //Nothing to clear
        }

        @Override
        public void filterOnBotJavaClassesStart() {
            //Nothing to clear
        }

        /**
         * Don't call me, I'll call you.
         *
         * @param clazz Look me in the mirror.
         */
        @Override
        //So I don't actually know Java reflection very well. I'm just good at following patterns (hence the "inspired by AnnotatedOpModeClassFilter"). Tell me if I'm doing anything wrong. Here we go…
        @SuppressWarnings("unchecked")  //The "ClassUtil.inheritsFrom" part does actually make sure that "clazz" is an OpMode. The compiler just doesn't know that.
        public void filterClass(Class clazz) {
            if (clazz.getPackage().getName().equals(packageName) && ClassUtil.inheritsFrom(clazz, OpMode.class)) classRegistrar.registerClass((Class<OpMode>) clazz);   //Register each class that is both in the given package and a subclass of OpMode
        }

        @Override
        public void filterOnBotJavaClass(Class clazz) {
            filterClass(clazz);
        }

        /**
         * Called when a filtering cycle is complete
         */
        @Override
        public void filterAllClassesComplete() {
            //Nothing to finish
        }

        @Override
        public void filterOnBotJavaClassesComplete() {
            //Nothing to finish
        }
    }
}

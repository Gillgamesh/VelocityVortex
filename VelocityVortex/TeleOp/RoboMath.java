package org.firstinspires.ftc.teamcode.VelocityVortex.TeleOp;

/**
 * Contains static helper functions
 */

public class RoboMath {
    public static double sensAdjust(double x, double sens) {
        return sens * Math.pow(x, 3) + (1-sens) * x;
    }
    public static double sensAdjust(double x) {
        return sensAdjust(x, 0.5);
    }
    //adjust motor speed by a factor proportional to distance
    public static double gradualSpeed(int curTicks, int tarTicks) {
        curTicks = Math.abs(curTicks);
        tarTicks = Math.abs(tarTicks);
        double dif = tarTicks-curTicks;
        double ratio = dif/tarTicks;
        //the funtion used for this is min(sqrt(x)+0.15,1). Integrating this gives you higher average speed than linear, but still levels off fairly smoothly.
        return Math.min(Math.sqrt(ratio)+0.15
                ,1);

    }
}

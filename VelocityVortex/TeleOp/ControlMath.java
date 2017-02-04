package org.firstinspires.ftc.teamcode.VelocityVortex.TeleOp;

/**
 * Contains static helper functions
 */

public class ControlMath {
    public static double sensAdjust(double x, double sens) {
        return sens * Math.pow(x, 3) + (1-sens) * x;
    }
    public static double sensAdjust(double x) {
        return sensAdjust(x, 0.5);
    }
}

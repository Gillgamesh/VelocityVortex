package org.firstinspires.ftc.teamcode.VelocityVortex;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Driver class for initating and controlling different modules of the robot.
 */

public class VVRobot {
    public DriveTrain dt    = null;
    public Shooter sh       = null;
    public Lift lift        = null;
    public ButtonPresser bp = null;
    //add all elements to initiate them.
    public HardwareModule[] parts = {dt, sh};

    public void init(HardwareMap hwMap) {
        for (HardwareModule part: parts) part.init(hwMap);

    }


    public void stop() {
        for (HardwareModule part: parts) part.stop();
    }
}

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
    public DriveTrain dt    = new DriveTrain();
    public Shooter sh       = new Shooter();
    public Lift lift        = new Lift();
    public ButtonPresser bp = new ButtonPresser();
    //add all elements to initiate them.
    public HardwareModule[] parts = {sh};

    public void init(HardwareMap hwMap) {
        for (HardwareModule part: parts) part.init(hwMap);

    }


    public void stop() {
        for (HardwareModule part: parts) part.stop();
    }
}

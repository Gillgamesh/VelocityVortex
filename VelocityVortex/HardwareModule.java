package org.firstinspires.ftc.teamcode.VelocityVortex;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 *This interface forces the inclusion of particular methods so that all hardware modules (i.e.
 * drivetrain or shooter) have methods that will be run on all of them.
 */

public interface HardwareModule {
    public void init(HardwareMap parts);
    public  void stop();
}

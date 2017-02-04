package org.firstinspires.ftc.teamcode.VelocityVortex;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Shooter: Controls hardware configuration for a flywheel shooter + any mechanical parts related to the hopper
 *
 */


public class Shooter implements HardwareModule {

    //shooter motors
    public DcMotor shooterRight = null;
    public DcMotor shooterLeft  = null;

    public void init(HardwareMap map) {
        //save another reference
        HardwareMap hwMap = map;
        //initiate motors
        shooterLeft = hwMap.dcMotor.get("lt_shoot");
        shooterRight = hwMap.dcMotor.get ("rt_shoot");

        //reverse direction based on polarity and location (one spins clockwise, the other counter)

        shooterRight.setDirection((DcMotor.Direction.REVERSE));
        shooterLeft.setDirection((DcMotor.Direction.REVERSE));



        //encoders? (prolly not)

        //no:
        shooterLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooterRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //set motor power to 0 (just incase)

        shooterLeft.setPower(0);
        shooterRight.setPower(0);


    }

    //these methods effectively just wrap setPower and provide no additional functionality

    //stop the shooter by setting motor power to 0
    public void stop() {
        shooterLeft.setPower(0);
        shooterRight.setPower(0);
    }

    //turn on the shooter at a given power (without encoders)
    public void shoot(double left, double right) {
        shooterLeft.setPower(Range.clip(left,-1,1));
        shooterRight.setPower(Range.clip(right,-1,1));

    }

    public void shoot(double speed) {
        shoot(speed, speed);
    }
    public void shoot() {
        shoot(1,1);
    }
}




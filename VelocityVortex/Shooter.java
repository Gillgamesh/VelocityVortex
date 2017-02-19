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

    //INSTANCE VARIABLES for speed
    public int SHOOTER_SPEED = 1;
    public int ACQ_SPEED = 1;
    //shooter motors
    public DcMotor shooterRight = null;
    public DcMotor shooterLeft  = null;
    public DcMotor acq = null;

    public void init(HardwareMap map) {
        //save another reference
        HardwareMap hwMap = map;
        //initiate motors
        shooterLeft = hwMap.dcMotor.get("lt_shoot");
        shooterRight = hwMap.dcMotor.get ("rt_shoot");
        acq = hwMap.dcMotor.get("acq");

        //reverse direction based on polarity and location (one spins clockwise, the other counter)

        shooterRight.setDirection((DcMotor.Direction.FORWARD));
        shooterLeft.setDirection((DcMotor.Direction.FORWARD));



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
        acq.setPower(0);
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
        shoot(SHOOTER_SPEED,SHOOTER_SPEED);
    }

    //control speed of the acquirer to input clipped
    public void acquire(double n) {
        acq.setPower(Range.clip(n,-1,1));
    }

    public void autoFire(double shoot, double acquire) {
        shoot(shoot);
        acquire(acquire);

    }
}




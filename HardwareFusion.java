package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See FusionProto_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class HardwareFusion
{
    /* Public OpMode members. */
    //left motors
    public DcMotor  frontLeft   = null;
    public DcMotor  backLeft    = null;
    //right motors
    public DcMotor  frontRight  = null;
    public DcMotor  backRight   = null;
    //shooter motors
    public DcMotor shooterRight = null;
    public DcMotor shooterLeft  = null;
//    acqrier/lift motors
    public DcMotor liftMotor         = null;
    public DcMotor acquirer     = null;
    //left servos
    public Servo    armRight     = null;
    public Servo    armLeft     = null;

    public static final double INITIAL_SERVO       =  0.0 ;
    /*
    public static final double ARM_IN_POWER    =  0.45 ;
    public static final double ARM_OUT_POWER  = -0.45 ;
    */

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareFusion(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;


        // Define and Initialize Motors
        frontLeft = hwMap.dcMotor.get("fl_drive");
        backLeft = hwMap.dcMotor.get("bl_drive");
        frontRight = hwMap.dcMotor.get("fr_drive");
        backRight = hwMap.dcMotor.get("br_drive");
        shooterLeft = hwMap.dcMotor.get("lt_shoot");
        shooterRight = hwMap.dcMotor.get ("rt_shoot");
        liftMotor = hwMap.dcMotor.get("lift");
        acquirer = hwMap.dcMotor.get("acq");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        shooterRight.setDirection((DcMotor.Direction.REVERSE));
        shooterLeft.setDirection((DcMotor.Direction.REVERSE));
        liftMotor.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);

        shooterLeft.setPower(0);
        shooterRight.setPower(0);


        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooterLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooterRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
        armRight = hwMap.servo.get("armRight");
        armLeft = hwMap.servo.get("armLeft");
        armRight.setPosition(INITIAL_SERVO);
        armLeft.setPosition(-INITIAL_SERVO);
    }


        /***
         *
         * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
         * periodic tick.  This is used to compensate for varying processing times for each cycle.
         * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
         *
         * @param periodMs  Length of wait cycle in mSec.
         */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}


package org.firstinspires.ftc.teamcode.VelocityVortex;




import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * DriveTrain: Controls the hardwre configuration for the drivetrain and provides basic methods
 * This is a 4 Wheel Tank drive using regular wheels.
 */

public class DriveTrain implements HardwareModule {
    //CONSTANTS FOR ENCODERS:

    static final double     TICKS_PER_REV    = 1240 ; //andymark neverest motors
    static final double     DRIVE_GEAR_RATIO    = 1.0 ;
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    //how many ticks it takes to travel 1 inch.
    static final double     TICKS_PER_INCH         = (TICKS_PER_REV * DRIVE_GEAR_RATIO) / (WHEEL_DIAMETER_INCHES * 3.1415);

    //Declare motors for drive train
    //left motors
    private DcMotor  frontLeft   = null;
    private DcMotor  backLeft    = null;
    //right motors
    private DcMotor  frontRight  = null;
    private DcMotor  backRight   = null;
    //initiate the hardware into the proper set up
    public void init(HardwareMap map) {
        HardwareMap hwMap = map;

        //find the devices based off configuration hwmaps
        frontLeft = hwMap.dcMotor.get("fl_drive");
        backLeft = hwMap.dcMotor.get("bl_drive");
        frontRight = hwMap.dcMotor.get("fr_drive");
        backRight = hwMap.dcMotor.get("br_drive");

        //set the direction of the motors, need to be changed depending on polarity.
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        //encoders?

        //no:
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        //setPower to 0:
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);


    }


    //stop all the motors:
    public void stop() {
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }


    //drive methods:
    public void drive(double fl, double bl, double fr, double br) {
        //sets the power for all motors to specified values
        frontLeft.setPower(Range.clip(fl,-1,1));
        backLeft.setPower(Range.clip(bl,-1,1));
        frontRight.setPower(Range.clip(fr,-1,1));
        
        backRight.setPower(Range.clip(br,-1,1));
    }
    public void drive(double left, double right) {
        drive(left, left, right, right);
    }
    public void drive(double speed) {
        drive(speed, speed, speed, speed);
    }

    //drive the bot a certain amount of inches forward
    public void posDrive(int left, int right) {
        //first, find out the distance that needs to be travelled in ticks, as the answer is in inches
        int rtTicks = (int) (TICKS_PER_INCH * right);
        int ltTicks = (int) (TICKS_PER_INCH * left);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }




}



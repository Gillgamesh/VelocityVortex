package org.firstinspires.ftc.teamcode.VelocityVortex;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Lift: Controls the hardware configuration for the lift and arm and provides basic methods for editing them
 * The lift is 6 stage linear slides connected by cascaded cables, run on a Neverest 60 motor to maximize torque.
 */


public class Lift implements HardwareModule {
    //instance variables for motor/servo references
    private DcMotor  lift        = null;
    private Servo    armRight    = null;
    private Servo    armLeft     = null;

    //constants:
    //adjust these 2 accordingly
    public final double RIGHT_REST = 0.5;
    public final double LEFT_REST = 0.5;

    public void init(HardwareMap hmap) {
        HardwareMap hwMap = hmap;
        //save hwmap reference to variable
        lift= hwMap.dcMotor.get("lift");
        armRight = hwMap.servo.get("armRight");
        armLeft = hwMap.servo.get("armLeft");

        //reverse direction based off polarity and location on robot:
        lift.setDirection(DcMotor.Direction.REVERSE);

        //set the initial power to 0:
        lift.setPower(0);

        //run with encoders? (prolly not)

        //no:
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //SERVOS: These are side mountend in order to form an L bracket fork lift. They have to swing from rest position (0 or 1) to
        //reverse the direction of the left servo.
        armRight.setDirection(Servo.Direction.REVERSE);
        armLeft.setDirection(Servo.Direction.FORWARD);
        //the middle position
        armRight.setPosition(RIGHT_REST);
        armLeft.setPosition(LEFT_REST);



    }
    public void moveUp(double speed) {
        lift.setPower(Range.clip(speed,-1,1));
    }
    //this method is empty because you do not want to release the servos from their position.
    public void stop() {
        lift.setPower(0);

    }

    //CONTROL ARMS:
    //set the pos of the arm
    public void moveArm(double offset) {
        armRight.setPosition(RIGHT_REST+offset);
        armLeft.setPosition(LEFT_REST+offset);

    }
}

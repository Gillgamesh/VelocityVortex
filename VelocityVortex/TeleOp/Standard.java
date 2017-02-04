package org.firstinspires.ftc.teamcode.VelocityVortex.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.VelocityVortex.VVRobot;

/**
 ** Standard Teleop-- Based off first controls. Control scheme below:
 */
@TeleOp(name="Feb17 Standard Teleop", group="Iterative Op Mode")
public class Standard extends OpMode {
    VVRobot robot = new VVRobot();
    double left;
    double right;
    double shooter;
    double lift;

    /*
 * Code to run ONCE when the driver hits INIT
 */
    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("Say", "Robot Initiated");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        telemetry.addData("Say", "Robot Started");
    }

    @Override
    public void loop() {

        //DRIVER 1:
        //sticks are inverted by default
        left = -1* ControlMath.sensAdjust(gamepad1.left_stick_y);
        right = -1 * ControlMath.sensAdjust(gamepad1.right_stick_y);
        shooter = ControlMath.sensAdjust(gamepad1.right_trigger);

        //DRIVER 2:
        lift = ControlMath.sensAdjust(gamepad2.right_trigger-gamepad1.left_trigger);



        //Drivetrain:
        robot.dt.drive(left, right);

        //Shooter:
        robot.sh.shoot(shooter);

        //lift:
        //robot.lift.moveUp(lift);


        //telemetry:
        telemetry.addData("left",  "%.2f", left);
        telemetry.addData("right", "%.2f", right);
        telemetry.addData("shooter", "%.2f", shooter);


    }
    /*
    * Code to run ONCE after the driver hits STOP
    */
    @Override
    public void stop() {
    }


}

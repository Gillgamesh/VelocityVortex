package org.firstinspires.ftc.teamcode.VelocityVortex.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.VelocityVortex.VVRobot;

/**
 ** Standard Teleop-- Based off first controls. Control scheme below:
 */
@TeleOp(name="Feb17 Standard Teleop", group="Iterative Op Mode")
public class Standard extends OpMode {
    //objects:
    VVRobot robot = new VVRobot();

    //motorspeeds
    double left;
    double right;
    double shooter;
    double acq;
    double bp;
    double lift;
    //servo position
    double armOff = 0.0;

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
        left = -1* RoboMath.sensAdjust(gamepad1.left_stick_y);
        right = -1 * RoboMath.sensAdjust(gamepad1.right_stick_y);
        shooter = RoboMath.sensAdjust(gamepad1.right_trigger);
        bp = -1 * RoboMath.sensAdjust(gamepad2.right_stick_y);
        acq = RoboMath.sensAdjust(gamepad1.left_trigger);
        //servo offset:
        if (gamepad2.a) armOff+=0.025;
        if (gamepad2.y) armOff-=0.025;
        armOff = Range.clip(armOff,-0.5,0.5);


        //DRIVER 2:
        lift = gamepad2.right_trigger-gamepad2.left_trigger;




        //CONTROLLING THE ROBOT:
        //Drivetrain:
        robot.dt.drive(left, right);

        //Shooter/acq:
        robot.sh.shoot(shooter);
        robot.sh.acquire(acq);

        //lift:
        robot.lift.moveUp(lift);

        //lift arm:
        robot.lift.moveArm(armOff);


        //button presser:
        robot.bp.press(bp);



        //telemetry:
        telemetry.addData("acq", "%.2f", acq);
        telemetry.addData("left",  "%.2f", left);
        telemetry.addData("right", "%.2f", right);
        telemetry.addData("shooter", "%.2f", shooter);
        telemetry.addData("color", "%s", robot.bp.getRGB());
        telemetry.addData("bp", "%s", bp);
        telemetry.update();

    }
    /*
    * Code to run ONCE after the driver hits STOP
    */
    @Override
    public void stop() {
    }


}

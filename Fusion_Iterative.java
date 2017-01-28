/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

/**
 * This file provides basic Telop driving for a Pushbot robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwareFusionProto class.
 *
 * This particular OpMode executes a basic Tank Drive Teleop for a PushBot
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Pushbot: Fusion 4 Wheel", group="Iterative Op Mode")
//@Disabled
public class Fusion_Iterative extends OpMode{

    /* Declare OpMode members. */
    HardwareFusion robot       = new HardwareFusion();
                                                         // could also use HardwarePushbotMatrix class.
    double          leftOffset  = 0.0 ;                  // Servo offset position
    double          rightOffset = 0.0 ;
    final double    ARM_SPEED  = 0.05 ;                 // sets rate to move servo
    final double SENSITVITY = 0.5;                      //controls deadzone
    double acq = 1.0;
    boolean isAcq = false;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
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
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     * Additional Methods: sensAdjsut(double): returns adjusted sensitivity based on ax^3+(1-a)x,
     * where a is sensitivty from 0-1, 0 being linear.
     */
    private double sensAdjust(double x) {
        return SENSITVITY * Math.pow(x, 3) + (1-SENSITVITY) * x;
    }
    @Override
    public void loop() {
        double left;
        double right;
        double shooter;
        double lift;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left = -1* sensAdjust(gamepad1.left_stick_y);
        right = -1 * sensAdjust(gamepad1.right_stick_y);
        shooter = sensAdjust(gamepad1.right_trigger);
        // operator:
        acq = sensAdjust(gamepad2.right_trigger) - sensAdjust(gamepad2.left_trigger);
        if (gamepad2.a) acq += .05;
        if (gamepad2.y) acq -= .05;
        //
        robot.frontRight.setPower(right);
        robot.backRight.setPower(right);

        robot.frontLeft.setPower(left);
        robot.backLeft.setPower(left);

        robot.shooterRight.setPower(shooter);
        robot.shooterLeft.setPower(shooter);

        robot.acquirer.setPower(acq);
        if (gamepad2.b) isAcq = false;
        if (gamepad2.x) isAcq = true;
        acq = Range.clip(acq,0.0,1.0);
//        if (isAcq) {
//            robot.acquirer.setPower(acq);
//        }
//        else {
//            robot.acquirer.setPower(0);
//        }
        // Use gamepad left & right sticks to open and close the claw
        rightOffset += sensAdjust(gamepad2.right_stick_y )* ARM_SPEED;
        leftOffset += sensAdjust(gamepad2.right_stick_y) * ARM_SPEED;
        // Move both servos to new position.  Assume servos are mirror image of each other.
        rightOffset = Range.clip(rightOffset, 0, 1);
        leftOffset = Range.clip(leftOffset, 0, 1);
        robot.armRight.setPosition(-(robot.INITIAL_SERVO + rightOffset));
        robot.armLeft.setPosition(robot.INITIAL_SERVO + leftOffset);

         // Send telemetry message to signify robot running;
        telemetry.addData("claw_right",  "Offset = %.2f", rightOffset);
        telemetry.addData("claw_left",  "Offset = %.2f", leftOffset);
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

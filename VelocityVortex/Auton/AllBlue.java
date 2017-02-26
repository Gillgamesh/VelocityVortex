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
package org.firstinspires.ftc.teamcode.VelocityVortex.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.VelocityVortex.VVRobot;

/**
 * Standard configuration for blue: shoot, drive to the right, then go for beacons.
 */

@Autonomous(name="AllBlue", group="Linear Op Mode")

public class AllBlue extends LinearOpMode {

    /* Declare OpMode members. */
    VVRobot         robot   = new VVRobot();
    private ElapsedTime     runtime = new ElapsedTime();


    static final double     SPEED = 0.7;
    static final double     TURN_SPEED    = 0.5;


    @Override
    public void runOpMode(){

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        //initiate the robot.
        robot.init(hardwareMap);

        //first, shoot for 5 seconds
        runtime.reset();
        robot.sh.autoFire(0.95,1.0);
        while (runtime.seconds() < 3.5) {
            telemetry.addData("Shooter/Acq: ", "%s", ""+0.95+""+1.0);
        }
        robot.sh.stop();
        //posdrive: right (inches), left (inches), timeout (max time in s), and speed
        robot.dt.posDrive(this, 40,40, 5.0, SPEED); //40 inch forward, 5s
        robot.dt.posDrive(this, 18,-18,3.0, TURN_SPEED);//turn 90 degrees right, 3s
        robot.dt.posDrive(this, 45,45,5.5,SPEED);//45 inch forward, 5.5s
        robot.dt.posDrive(this, -18, 18, 3.0, TURN_SPEED);//90 degrees left, 3s

        //you should now be about 5 inches from the white line. Move the presser to the correct beacon:
        robot.dt.posDrive(this,3.5,3.5, 1.0, SPEED);
        //if the first button will be blue:
        if (robot.bp.isBlue()) {
            //press the button:
            runtime.reset();
            while (runtime.seconds() < 0.25) robot.bp.press();
            while (runtime.seconds() < 0.5) robot.bp.retract();
            robot.bp.stop();
            //wait a couple miliseconds to let the button switch color
            sleep(250);
            robot.dt.posDrive(this,5.25,5.25,1.0,SPEED);
        }
        //else move on and press the next part
        else{
            robot.dt.posDrive(this,5.25,5.25,1.0,SPEED);//drive forward 5.25 to press the next button,1s
            //press the button:
            runtime.reset();
            while (runtime.seconds() < 0.25) robot.bp.press();
            while (runtime.seconds() < 0.5) robot.bp.retract();
            robot.bp.stop();
            //wait a couple miliseconds to let the button switch color
            sleep(250);
        }
        //if after the press the button somehow ended up being the wrong color (code this later, see how reliable these numbers are

        //move 44 inches, repeat previous codeblock. Will method this after further testing
        robot.dt.posDrive(this,44,44,5.5,SPEED); //5.5 inches
        //PART 2 COPY AND PASTE
        if (robot.bp.isBlue()) {
            //press the button:
            runtime.reset();
            while (runtime.seconds() < 0.25) robot.bp.press();
            while (runtime.seconds() < 0.5) robot.bp.retract();
            robot.bp.stop();
            //wait a couple miliseconds to let the button switch color
            sleep(250);
            robot.dt.posDrive(this,5.25,5.25,1.0,SPEED);
        }
        //else move on and press the next part
        else{
            robot.dt.posDrive(this,5.25,5.25,1.0,SPEED);//drive forward 5.25 to press the next button,1s
            //press the button:
            runtime.reset();
            while (runtime.seconds() < 0.25) robot.bp.press();
            while (runtime.seconds() < 0.5) robot.bp.retract();
            robot.bp.stop();
            //wait a couple miliseconds to let the button switch color
            sleep(250);
        }

        //drive back to hit the yoga ball.
        robot.dt.posDrive(this,-47.5,-47.5,5.5,SPEED);//move -47.5 inches back, 5.5s max
        robot.dt.posDrive(this, -20, 20, 3.0, TURN_SPEED);//90 degrees left, 3s
        robot.dt.posDrive(this, 45, 45, 5.5, SPEED);

        robot.stop();
    }
}

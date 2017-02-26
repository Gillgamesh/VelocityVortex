package org.firstinspires.ftc.teamcode.VelocityVortex;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DigitalChannelController;


/**
 * ButtonPresser: Controls hardware configuration for button servo and methods for sensors
 *
 */


public class ButtonPresser implements HardwareModule {
    //beacon presser servo
    private Servo presser;
    //speed of presser
    private double SPEED = 0.135;
    private double REST_OFF = 0.865;


    //the color sensor object has all methods for getting the color, while the core device interface provides auxiliary stuff
    private ColorSensor color;
    private DeviceInterfaceModule dim;

    //copied from the example code, thanks FIRST:

    // we assume that the LED pin of the RGB sensor is connected to
    // digital port 5 (zero indexed).
    static final int LED_CHANNEL = 5;
    //should the LED be on?
    boolean isLEDon = true;


    public void init(HardwareMap hmap) {
        HardwareMap hwMap = hmap;
        //first, map the given devices to reference variables:
        //servo:
        presser = hwMap.servo.get("presser");
        //core device interface module:
        dim = hwMap.deviceInterfaceModule.get("cdim");
        //color sensor object:
        color = hwMap.colorSensor.get("colorSensor");

        //turn the sensor's LED on:
        //i.e.: for the core device module, set the LED channel to true so it shows.
        dim.setDigitalChannelMode(LED_CHANNEL, DigitalChannelController.Mode.OUTPUT);
        dim.setDigitalChannelState(LED_CHANNEL, isLEDon);
        //set the speed of the servo to 0.
        presser.setDirection(Servo.Direction.FORWARD);
        setSpeed(0.5);
        stop();
    }

    //SET/GET SPEED:
    public double getSpeed() {
        return presser.getPosition();
    }
    public void setSpeed(double speed) {
        SPEED = speed;
    }


    //PRESS/RETRACT BUTTON:
    public void press(double speed) {
        presser.setPosition(REST_OFF+speed);
    }

    public void retract(double speed) {
        presser.setPosition(REST_OFF-speed);
    }

    //    going for regular speed press/retract
    public void press() {
        press(SPEED);
    }
    public void retract() {
        retract(SPEED);
    }

    public void autoPress(double speed, double time) {
        ElapsedTime runtime = new ElapsedTime();
        press(speed);
        while (runtime.seconds() < time) {

        }
        stop();
    }
    public void autoPress(double time) {
        autoPress(SPEED, time);
    }
    public void autoRetract(double speed, double time) {
        ElapsedTime runtime = new ElapsedTime();
        retract(speed);
        while (runtime.seconds() < time) {

        }
        stop();

    }

    public void stop() {
        presser.setPosition(REST_OFF);

    }


    public void light(boolean b) {
        isLEDon= b;
        dim.setDigitalChannelState(LED_CHANNEL, isLEDon);
    }


    //methods for color sensor itself:

    public boolean isRed() {
        return (color.red()  > color.blue());
    }
    public boolean isBlue() {
        return (color.blue()> color.red());
    }



    //control bringing the servo out:

    public String getRGB() {
        return "R:"+color.red()+"-G:"+color.green()+"-B:"+color.blue();
    }

}

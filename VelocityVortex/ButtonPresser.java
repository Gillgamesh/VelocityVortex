package org.firstinspires.ftc.teamcode.VelocityVortex;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DigitalChannelController;


/**
 * ButtonPresser: Controls hardware configuration for button servo and methods for sensors
 *
 */


public class ButtonPresser implements HardwareModule {
    //beacon presser servo
    private CRServo presser;
    //speed of presser
    private double SPEED = 0.25;


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
        presser = hwMap.crservo.get("presser");
        //core device interface module:
        dim = hwMap.deviceInterfaceModule.get("cdim");
        //color sensor object:
        color = hwMap.colorSensor.get("colorSensor");

        //turn the sensor's LED on:
        //i.e.: for the core device module, set the LED channel to true so it shows.
        dim.setDigitalChannelMode(LED_CHANNEL, DigitalChannelController.Mode.OUTPUT);
        dim.setDigitalChannelState(LED_CHANNEL, isLEDon);
        //set the speed of the servo to 0.
        setSpeed(0);
    }

    //SET/GET SPEED:
    public double getSpeed() {
        return SPEED;
    }
    public void setSpeed(double speed) {
        SPEED = speed;
    }


    //PRESS/RETRACT BUTTON:
    public void press(double speed) {
        presser.setDirection(DcMotorSimple.Direction.FORWARD);
        presser.setPower(speed);
    }

    public void retract(double speed) {
        presser.setDirection(DcMotorSimple.Direction.REVERSE);
        presser.setPower(speed);
    }

    //    going for regular speed press/retract
    public void press() {
        press(SPEED);
    }
    public void retract() {
        retract(SPEED);
    }

    public void stop() {
        presser.setPower(0);
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

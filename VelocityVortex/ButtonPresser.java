package org.firstinspires.ftc.teamcode.VelocityVortex;

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
    public Servo presser;

    //the color sensor object has all methods for getting the color, while the core device interface provides auxiliary stuff
    private ColorSensor color;
    private DeviceInterfaceModule dim;

    //copied from the example code, thanks FIRST:

    // we assume that the LED pin of the RGB sensor is connected to
    // digital port 5 (zero indexed).
    static final int LED_CHANNEL = 5;
    static final double REST     = 0  ;
    static final int FULL = 6; //how long it can possibly extend out.
    static final double ROTMOVE  = 1.0/FULL; //rotations per inch

    //this stores the Hue-Saturation-Color Value information in hex
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
        dim.setDigitalChannelState(LED_CHANNEL, isLEDon);


        //retract the servo:
        retract();
    }
    //methods for color sensor itself:

    public boolean isRed() {
        return (color.red() > color.blue());
    }
    public boolean isBlue() {
        return (color.blue() > color.red());
    }



    //control bringing the servo out:

    //calculates how much you need to rotate the thing using a constant
    public double distToRot(double inches) {
        return inches * ROTMOVE;
    }
    public void press(double inches) {
        presser.setPosition(inches);
    }
    //going for full press (6 inches):
    public void press() {
        press(FULL);
    }
    public void retract() {
        presser.setPosition(REST);
    }

    public void stop() {

    }
}

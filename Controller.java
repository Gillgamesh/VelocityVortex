package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by root on 1/4/17.
 */

public class Controller {
    //instance variables
    private Gamepad GAMEPAD;
    private double SENSITVITY;
    private int MODE;
    //this is just a reference to  see what each controller scheme is
    private String[] MODES = {"traditional"};

    public Controller() {
        SENSITVITY =  0.5;
        MODE = 0;

    }
    public Controller(Gamepad g, int mode, int sens) {
        MODE = mode;
        SENSITVITY = sens;
        GAMEPAD = g;
    }
//helpers
    private double sensAdjust(double x) {
        return SENSITVITY * Math.pow(x, 3) + (1-SENSITVITY) * x;
    }
}

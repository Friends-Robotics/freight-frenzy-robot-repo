package org.firstinspires.ftc.teamcode.teamhardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class TeamHardwareMap {

    protected HardwareMap hardwareMap;

    public ElapsedTime runTime = new ElapsedTime();

    public TeamHardwareMap(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        initialise();
    }

    protected abstract void initialise();

}
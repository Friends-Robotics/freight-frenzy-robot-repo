package org.firstinspires.ftc.teamcode.teamhardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class armOnlyHardwareMap extends TeamHardwareMap {

    public armOnlyHardwareMap(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public DcMotor hexMotor1;

    @Override
    protected void initialise() {

        hexMotor1 = hardwareMap.get(DcMotor.class, "Core_Hex_Motor_1");
    }
}

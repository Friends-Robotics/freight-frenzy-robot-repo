package org.firstinspires.ftc.teamcode.teamhardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriverMotorsOnlyTeamHardwareMap extends TeamHardwareMap {

    public DriverMotorsOnlyTeamHardwareMap(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public DcMotor rightMotor;
    public DcMotor leftMotor;

    @Override
    protected void initialise() {
        rightMotor = hardwareMap.get(DcMotor.class, "HD_Hex_Motor_1");
        leftMotor = hardwareMap.get(DcMotor.class, "HD_Hex_Motor_2");

        // Setup motor direction as forwards
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        // Set zero power behavior: means when there is no power going to motor it breaks instead of free wheeling
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set default power to motors as zero
        rightMotor.setPower(0);
        leftMotor.setPower(0);

        // Set motor mode: reset the motor ticks to 0
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set motors to use encoder
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}

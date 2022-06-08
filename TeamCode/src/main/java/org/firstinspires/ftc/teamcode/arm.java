package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.teamhardware.AllMotorsAndSensorsTeamHardwareMap;
import org.firstinspires.ftc.teamcode.teamhardware.DriverMotorsOnlyTeamHardwareMap;
import org.firstinspires.ftc.teamcode.teamhardware.TeamHardwareMap;
import org.firstinspires.ftc.teamcode.teamhardware.armOnlyHardwareMap;


@TeleOp(name="arm only", group="Linear Opmode")
public class arm extends LinearOpMode {


    private armOnlyHardwareMap teamHardwareMap;

    double previousValue = 0;

    @Override
    public void runOpMode() {
        teamHardwareMap = new armOnlyHardwareMap(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //config for motor
        teamHardwareMap.hexMotor1.setMotorType(new MotorConfigurationType());
        teamHardwareMap.hexMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        while(opModeIsActive())
        {
            // left joystick y axis
            double gamepadinputLeft_Y = gamepad1.left_stick_y;

            teamHardwareMap.hexMotor1.setPower(gamepadinputLeft_Y);

        }


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();


    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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


@TeleOp(name="Arm Only", group="Linear Opmode")
@Disabled
public class ArmOnlyOpMode extends LinearOpMode {


    private armOnlyHardwareMap teamHardwareMap;

    double previousValue = 0;

    @Override
    public void runOpMode() {
        teamHardwareMap = new armOnlyHardwareMap(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();


        while(opModeIsActive())
        {
            // left joystick y axis
            double gamepadinputLeft_Y = gamepad1.left_stick_y;
            double gamepadinputRight_Y = gamepad1.right_stick_y;

            if (-gamepadinputLeft_Y > 0)
            {
                try {
                    teamHardwareMap.hexMotor1.setPower((-gamepadinputLeft_Y) + 0.1);
                }
                catch(Exception ex)
                {
                    teamHardwareMap.hexMotor1.setPower(-gamepadinputLeft_Y);
                }
            }
            if (-gamepadinputLeft_Y < 0)
            {
                try {
                    teamHardwareMap.hexMotor1.setPower((-gamepadinputLeft_Y/4) + 0.1);
                }
                catch(Exception ex)
                {
                    teamHardwareMap.hexMotor1.setPower(-gamepadinputLeft_Y/4);
                }
            }
            else
            {
                teamHardwareMap.hexMotor1.setPower(0.1);
            }



            if(gamepadinputRight_Y >= 0)
            {
                teamHardwareMap.hexMotor2.setPower(gamepadinputRight_Y * 0.5);
            }
            else
            {
                teamHardwareMap.hexMotor2.setPower(gamepadinputRight_Y);
            }


            telemetry.addData("Left Y value", gamepadinputLeft_Y);
            telemetry.addData("Right Y value", gamepadinputRight_Y);
            telemetry.update();

        }





    }
}

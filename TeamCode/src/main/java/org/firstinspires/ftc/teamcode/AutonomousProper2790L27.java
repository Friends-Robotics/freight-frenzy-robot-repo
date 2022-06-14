package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.teamhardware.AllMotorsAndSensorsTeamHardwareMap;

@Autonomous(name = "27cm90L27cm", group = "tests")
public class AutonomousProper2790L27 extends LinearOpMode {

    private AllMotorsAndSensorsTeamHardwareMap teamHardwareMap;

    @Override
    public void runOpMode() throws InterruptedException {

        teamHardwareMap = new AllMotorsAndSensorsTeamHardwareMap(hardwareMap);

        ElapsedTime timer = new ElapsedTime();
        waitForStart();

        timer.reset();
        boolean stage1Fin = false;
        boolean stage2Fin = false;
        boolean stage3Fin = false;
        while (opModeIsActive()) {
            telemetry.addData("Encoder (left)", teamHardwareMap.leftMotor.getCurrentPosition());
            telemetry.addData("Encoder (right)", teamHardwareMap.rightMotor.getCurrentPosition());
            telemetry.update();
            teamHardwareMap.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            teamHardwareMap.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            if (!stage1Fin) {
                if (teamHardwareMap.leftMotor.getCurrentPosition() <= -MathsMethods.InchesToMainMotorTicks(27)) {
                    teamHardwareMap.leftMotor.setPower(0);
                    teamHardwareMap.rightMotor.setPower(0);
                    teamHardwareMap.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    teamHardwareMap.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    stage1Fin = true;
                    continue;
                } else {
                    teamHardwareMap.leftMotor.setPower(-0.5);
                    teamHardwareMap.rightMotor.setPower(-0.5);
                    continue;
                }
            }
            teamHardwareMap.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            teamHardwareMap.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            if (!stage2Fin) {
                if (teamHardwareMap.rightMotor.getCurrentPosition() <= -MathsMethods.DegreesToMainMotorTicks(90)) {
                    teamHardwareMap.rightMotor.setPower(0);
                    teamHardwareMap.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    teamHardwareMap.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    stage2Fin = true;
                    continue;
                } else {
                    teamHardwareMap.rightMotor.setPower(-0.5);
                    continue;
                }
            }
            teamHardwareMap.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            teamHardwareMap.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            if (!stage3Fin) {
                if (teamHardwareMap.leftMotor.getCurrentPosition() <= -MathsMethods.InchesToMainMotorTicks(27)) {
                    teamHardwareMap.leftMotor.setPower(0);
                    teamHardwareMap.rightMotor.setPower(0);
                    stage3Fin = true;
                    continue;
                } else {
                    teamHardwareMap.leftMotor.setPower(-0.5);
                    teamHardwareMap.rightMotor.setPower(-0.5);
                    continue;
                }
            }
        }
    }
}
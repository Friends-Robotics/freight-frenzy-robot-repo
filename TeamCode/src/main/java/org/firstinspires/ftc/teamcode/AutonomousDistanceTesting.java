package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.teamhardware.AllMotorsAndSensorsTeamHardwareMap;

@Autonomous(name = "Distance Testing", group = "tests")
@Disabled
public class AutonomousDistanceTesting extends LinearOpMode {

    private AllMotorsAndSensorsTeamHardwareMap teamHardwareMap;

    @Override
    public void runOpMode() throws InterruptedException {

        teamHardwareMap = new AllMotorsAndSensorsTeamHardwareMap(hardwareMap);

        ElapsedTime timer = new ElapsedTime();
        waitForStart();

        int inchesToTravel = 0;
        while (!gamepad1.cross) {
            if (gamepad1.circle) {
                inchesToTravel++;
                while (gamepad1.circle) {}
            }
            if (gamepad1.square) {
                inchesToTravel--;
                while (gamepad1.square) {}
            }
            telemetry.addData("Distance in inches", inchesToTravel);
            telemetry.addData("Use square and circle on gamepad to change number of inches", ".");
            telemetry.addData("Press cross on gamepad to go", ".");
            telemetry.update();
        }
        telemetry.update();

        timer.reset();
        while (opModeIsActive()) {
            if (teamHardwareMap.leftMotor.getCurrentPosition() >= MathsMethods.InchesToMainMotorTicks(inchesToTravel)) {
                teamHardwareMap.leftMotor.setPower(0);
                teamHardwareMap.rightMotor.setPower(0);
            }
            else {
                teamHardwareMap.leftMotor.setPower(0.5);
                teamHardwareMap.rightMotor.setPower(0.5);
            }
            telemetry.addData("Encoder value (left)", teamHardwareMap.leftMotor.getCurrentPosition());
            telemetry.addData("Encoder value (right)", teamHardwareMap.rightMotor.getCurrentPosition());
            telemetry.update();

            // ROUGHLY 720 encoder ticks of the main driving motor = a full circumference of the wheel travelled (diameter = 9cm, therefore circumference = 9*pi = 28.274cm)
            // aka roughly 720 ticks = 28.274cm
            // 1488 ticks to travel one tile width
        }
    }
}
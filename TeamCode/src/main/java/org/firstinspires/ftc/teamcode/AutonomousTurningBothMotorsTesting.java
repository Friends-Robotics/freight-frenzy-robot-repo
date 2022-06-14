package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.teamhardware.AllMotorsAndSensorsTeamHardwareMap;

@Autonomous(name = "Turning Testing (both motors)", group = "tests")
@Disabled
public class AutonomousTurningBothMotorsTesting extends LinearOpMode {

    private AllMotorsAndSensorsTeamHardwareMap teamHardwareMap;

    /// This will turn the robot 180 degrees using both motors (pivot is midpoint between both standard wheels)
    @Override
    public void runOpMode() throws InterruptedException {

        teamHardwareMap = new AllMotorsAndSensorsTeamHardwareMap(hardwareMap);

        ElapsedTime timer = new ElapsedTime();
        waitForStart();

        timer.reset();
        while (opModeIsActive()) {
            if (teamHardwareMap.rightMotor.getCurrentPosition() >= MathsMethods.DegreesToMainMotorTicks(180) / 2) {
                teamHardwareMap.rightMotor.setPower(0);
                teamHardwareMap.leftMotor.setPower(0);
            }
            else {
                teamHardwareMap.rightMotor.setPower(0.5);
                teamHardwareMap.leftMotor.setPower(-0.5);
            }
            telemetry.addData("Encoder value (left)", teamHardwareMap.leftMotor.getCurrentPosition());
            telemetry.addData("Encoder value (right)", teamHardwareMap.rightMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}
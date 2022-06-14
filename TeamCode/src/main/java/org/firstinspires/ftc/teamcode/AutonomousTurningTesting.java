package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.teamhardware.AllMotorsAndSensorsTeamHardwareMap;

@Autonomous(name = "Turning Testing", group = "tests")
@Disabled
public class AutonomousTurningTesting extends LinearOpMode {

    private AllMotorsAndSensorsTeamHardwareMap teamHardwareMap;

    /// This will turn the robot 180 degrees using only the right motor (pivot is left standard wheel)
    @Override
    public void runOpMode() throws InterruptedException {

        teamHardwareMap = new AllMotorsAndSensorsTeamHardwareMap(hardwareMap);

        ElapsedTime timer = new ElapsedTime();
        waitForStart();

        timer.reset();
        while (opModeIsActive()) {
            if (teamHardwareMap.rightMotor.getCurrentPosition() >= MathsMethods.DegreesToMainMotorTicks(180)) {
                teamHardwareMap.rightMotor.setPower(0);
            }
            else {
                teamHardwareMap.rightMotor.setPower(0.5);
            }
            telemetry.addData("Encoder value (left)", teamHardwareMap.leftMotor.getCurrentPosition());
            telemetry.addData("Encoder value (right)", teamHardwareMap.rightMotor.getCurrentPosition());
            telemetry.update();

            // 22 inches of movement of one side's motor makes a 90 degree turn
        }
    }
}
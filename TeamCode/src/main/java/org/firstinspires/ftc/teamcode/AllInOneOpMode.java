package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teamhardware.AllMotorsAndSensorsTeamHardwareMap;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="All in one (tank)", group="Linear Opmode")
public class AllInOneOpMode extends LinearOpMode {

    private AllMotorsAndSensorsTeamHardwareMap teamHardwareMap;

    double previousValue = 0;

    @Override
    public void runOpMode() {
        teamHardwareMap = new AllMotorsAndSensorsTeamHardwareMap(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();


        while (opModeIsActive()) {
            double gradualIncreaseRate = 0.1;

            double gamepadInputLeft = gamepad1.left_stick_y;
            double gamepadInputRight = gamepad1.right_stick_y;

            double oldLeftMotorPower = teamHardwareMap.leftMotor.getPower();
            double oldRightMotorPower = teamHardwareMap.rightMotor.getPower();

            double newLeftMotorPower = oldLeftMotorPower;
            double newRightMotorPower = oldRightMotorPower;

            if (gamepadInputLeft == 0)
            {
                newLeftMotorPower = 0;
            }
            else if (oldLeftMotorPower < gamepadInputLeft) {
                newLeftMotorPower += gradualIncreaseRate;
            }
            else if (oldLeftMotorPower > gamepadInputLeft) {
                newLeftMotorPower -= gradualIncreaseRate;
            }

            if (gamepadInputRight == 0)
            {
                newRightMotorPower = 0;
            }
            else if (oldRightMotorPower < gamepadInputRight) {
                newRightMotorPower += gradualIncreaseRate;
            }
            else if (oldRightMotorPower > gamepadInputRight) {
                newRightMotorPower -= gradualIncreaseRate;
            }

            if (gamepad1.right_trigger > 0) {
                newRightMotorPower = -gamepad1.right_trigger;
                newLeftMotorPower = -gamepad1.right_trigger;
            }
            if (gamepad1.left_trigger > 0) {
                newRightMotorPower = gamepad1.left_trigger;
                newLeftMotorPower = gamepad1.left_trigger;
            }

            // Send calculated power to wheels
            teamHardwareMap.leftMotor.setPower(newLeftMotorPower);
            teamHardwareMap.rightMotor.setPower(newRightMotorPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + teamHardwareMap.runTime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Input", "X: (%.2f); Y: (%.2f)", gamepadInputLeft, gamepadInputRight);
            telemetry.addData("Motors", "Left: (%.2f); Right: (%.2f)", newLeftMotorPower, newRightMotorPower);
            telemetry.update();

            ////////////

            if (gamepad1.right_bumper)
            {
                teamHardwareMap.hexMotor1.setPower(1);
            }
            if (gamepad1.left_bumper)
            {
                teamHardwareMap.hexMotor1.setPower(-1);
            }
            else
            {
                teamHardwareMap.hexMotor1.setPower(0.1);
            }

            if (gamepad1.triangle) {
                teamHardwareMap.hexMotor2.setPower(1);
            }
            else if (gamepad1.cross) {
                teamHardwareMap.hexMotor2.setPower(-1);
            }
            else {
                teamHardwareMap.hexMotor2.setPower(0);
            }

            ////////////

            if (gamepad1.circle) {
                teamHardwareMap.continuousServo1.setPower(1);
            }
            else if (gamepad1.square) {
                teamHardwareMap.continuousServo1.setPower(-1);
            }
            else {
                teamHardwareMap.continuousServo1.setPower(0);
            }

            ////////////

            telemetry.update();
        }
    }

}
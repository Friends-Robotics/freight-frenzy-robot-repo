package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teamhardware.DriverMotorsOnlyTeamHardwareMap;


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

@TeleOp(name="Differential Drive", group="Linear Opmode")
@Disabled
public class DifferentialDrive_OpMode extends LinearOpMode {

    private DriverMotorsOnlyTeamHardwareMap teamHardwareMap;

    double previousValue = 0;

    @Override
    public void runOpMode() {
        teamHardwareMap = new DriverMotorsOnlyTeamHardwareMap(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();

        while (opModeIsActive()) {
            double[] gamepadInputs = new double[2];
            gamepadInputs[0] = gamepad1.left_stick_x;
            gamepadInputs[1] = gamepad1.left_stick_y;
            // Send calculated power to wheels

            gamepadInputs = MathsMethods.JoystickToDifferential(gamepadInputs[0], gamepadInputs[1]);
            teamHardwareMap.rightMotor.setPower(gamepadInputs[0]);
            teamHardwareMap.leftMotor.setPower(gamepadInputs[1]);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + teamHardwareMap.runTime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Input", "X: (%.2f); Y: (%.2f)", gamepadInputs[0], gamepadInputs[1]);
            telemetry.update();
        }
    }

}


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

@TeleOp(name="Everything (diff)", group="Linear Opmode")
public class EverythingDiffOpMode extends LinearOpMode {

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

            ////////////

            // left joystick y axis
            double gamepadinputLeft_Y = gamepad2.left_stick_y;
            double gamepadinputRight_Y = gamepad2.right_stick_y;

            if (gamepadinputLeft_Y > 0)
            {
                try {
                    teamHardwareMap.hexMotor1.setPower((gamepadinputLeft_Y) + 0.1);
                }
                catch(Exception ex)
                {
                    teamHardwareMap.hexMotor1.setPower(gamepadinputLeft_Y);
                }
            }
            if (gamepadinputLeft_Y < 0)
            {
                try {
                    teamHardwareMap.hexMotor1.setPower((gamepadinputLeft_Y/3) + 0.1);
                }
                catch(Exception ex)
                {
                    teamHardwareMap.hexMotor1.setPower(gamepadinputLeft_Y/3);
                }
            }
            else
            {
                teamHardwareMap.hexMotor1.setPower(0.1);
            }


            teamHardwareMap.hexMotor2.setPower(gamepadinputRight_Y);


            telemetry.addData("Left Y value", gamepadinputLeft_Y);
            telemetry.addData("Right Y value", gamepadinputRight_Y);
            telemetry.update();
        }
    }

}
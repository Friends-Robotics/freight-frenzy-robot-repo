package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.teamhardware.DriverMotorsOnlyTeamHardwareMap;
import org.firstinspires.ftc.teamcode.teamhardware.TeamHardwareMap;
import java.lang.Math;


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

@TeleOp(name="Diff Op Mode", group="Linear Opmode")
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

            teamHardwareMap.leftMotor.setPower(gamepadInputs[0]);
            teamHardwareMap.leftMotor.setPower(gamepadInputs[1]);



            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + teamHardwareMap.runTime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Input", "X: (%.2f); Y: (%.2f)", gamepadInputs[0], gamepadInputs[1]);
            telemetry.update();
        }
    }

    public double[] JoystickToDifferential(double x, double y) {
        if (x == 0 && y == 0) {
            return (new double[]{0, 0});
        }

        double minJoyStick = -1;
        double maxJoyStick = 1;
        double minSpeed = -1;
        double maxSpeed = 1;

        //find hypotenuse
        double hyp = Math.sqrt(x * x + y * y);

        //find angle in radians
        double ang = Math.acos(Math.abs(x) / y);

        double degAng = ang * 180 / Math.PI;

     /* Now angle indicates the measure of turn
	    Along a straight line, with an angle o, the turn co-efficient is same
	    this applies for angles between 0-90, with angle 0 the coeff is -1
	    with angle 45, the co-efficient is 0 and with angle 90, it is 1 */

        double turnCE = -1 + (degAng / 90) * 2;
        double turn = turnCE * Math.abs(Math.abs(y) - Math.abs(x));
        // java cant round to a certain dp :(
        turn *= 100;
        turn = Math.round(turn);
        turn /= 100;

        double movement = Math.max(Math.abs(y), Math.abs(x));

        double rawLeft;
        double rawRight;

        if ((x >= 0 && y >= 0) || (x < 0 && y < 0)) {
            rawLeft = movement;
            rawRight = turn;
        } else {
            rawRight = movement;
            rawLeft = turn;
        }


        //Reverse polarity
        if (y < 0) {
            rawLeft = 0 - rawLeft;
            rawRight = 0 - rawRight;
        }

        return( new double[] {rawRight, rawLeft});
    }
}


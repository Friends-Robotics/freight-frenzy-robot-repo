/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.teamhardware.DriverMotorsOnlyTeamHardwareMap;
import org.firstinspires.ftc.teamcode.teamhardware.TeamHardwareMap;


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

@TeleOp(name="Tank Drive Gradual", group="Linear Opmode")
@Disabled
public class TankDriveGradual_OpMode extends LinearOpMode {

    private DriverMotorsOnlyTeamHardwareMap teamHardwareMap;

    @Override
    public void runOpMode() {
        teamHardwareMap = new DriverMotorsOnlyTeamHardwareMap(hardwareMap);

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

            if (oldLeftMotorPower < gamepadInputLeft) {
                newLeftMotorPower += gradualIncreaseRate;
            }
            if (oldLeftMotorPower > gamepadInputLeft) {
                newLeftMotorPower -= gradualIncreaseRate;
            }

            if (oldRightMotorPower < gamepadInputRight) {
                newRightMotorPower += gradualIncreaseRate;
            }
            if (oldRightMotorPower > gamepadInputRight) {
                newRightMotorPower -= gradualIncreaseRate;
            }

            if (gamepad1.circle) {
                newLeftMotorPower = 0;
                newRightMotorPower = 0;
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
        }
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.teamhardware.AllMotorsAndSensorsTeamHardwareMap;

@TeleOp(name = "Concept: Scan Servo", group = "Concept")
public class servo extends LinearOpMode {

    static final double INCREMENT   = 0.01;     // amount to slew servo each CYCLE_MS cycle
    static final int    CYCLE_MS    =   50;     // period of each cycle
    static final double MAX_POS     =  1.0;     // Maximum rotational position
    static final double MIN_POS     =  0.0;     // Minimum rotational position

    // Define class members
    double  position = (MAX_POS - MIN_POS) / 2; // Start at halfway position
    boolean rampUp = true;

    private AllMotorsAndSensorsTeamHardwareMap teamHardwareMap;


    @Override
    public void runOpMode() {

        teamHardwareMap = new AllMotorsAndSensorsTeamHardwareMap(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        teamHardwareMap.continuousServo1.setPosition(0);
        idle();
        teamHardwareMap.continuousServo1.setPosition(0.5);
        idle();
        teamHardwareMap.continuousServo1.setPosition(0.75);
        idle();
        teamHardwareMap.continuousServo1.setPosition(1);
        idle();

        teamHardwareMap.continuousServo1.setPosition(0.5);
        idle();
        teamHardwareMap.continuousServo1.setPosition(0.5);
        idle();
        teamHardwareMap.continuousServo1.setPosition(0.5);
        idle();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        teamHardwareMap.runTime.reset();



/*
        // Scan servo till stop pressed.
        while(opModeIsActive()){

            // slew the servo, according to the rampUp (direction) variable.
            if (rampUp) {
                // Keep stepping up until we hit the max value.
                position += INCREMENT ;
                if (position >= MAX_POS ) {
                    position = MAX_POS;
                    rampUp = !rampUp;   // Switch ramp direction
                }
            }
            else {
                // Keep stepping down until we hit the min value.
                position -= INCREMENT ;
                if (position <= MIN_POS ) {
                    position = MIN_POS;
                    rampUp = !rampUp;  // Switch ramp direction
                }
            }

            // Display the current value
            telemetry.addData("Servo Position", "%5.2f", position);
            telemetry.addData(">", "Press Stop to end test." );
            telemetry.update();

            // Set the servo to the new position and pause;
            teamHardwareMap.continuousServo1.setPosition(position);
            sleep(CYCLE_MS);
            idle();
        }

        // Signal done;
        telemetry.addData(">", "Done");
        telemetry.update();

 */
    }
}
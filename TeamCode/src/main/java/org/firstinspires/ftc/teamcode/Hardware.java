package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class Hardware {

    // Create Hardware Map
    public HardwareMap hardwareMap = null;

    // Create motors
    public DcMotor hexMotor1 =null;  // Core_Hex_Motor_1 , port 0
    public DcMotor hexMotor2 = null; //Core_Hex_Motor_2 , port 1

    public DcMotor rightMotor =null;  // HD_Hex_Motor_1, port 2
    public DcMotor leftMotor = null;  // HD_Hex_Motor_2, port 3

    // Create servos
    public Servo continuousServo1 = null; // Continuous_Rotation_Servo_1 , port 0
    public Servo continuousServo2 = null; // Continuous_Rotation_Servo_2 , port 1

    public Servo servo1 = null;  // Servo_1, port 2
    public Servo servo2 = null;  // Servo_2, port 3

    // Digital sensors
    public DigitalChannel touchSensor = null; // Touch_Sensor_1, port 0

    // I2C sensors
    NormalizedColorSensor colourSensor = null; // Colour_Sensor_1, port 0, bus 0

    // Camera will be dealt with in a separate class

    // Additional vars
    public ElapsedTime runTime = new ElapsedTime();

    // Constructor
    public Hardware(HardwareMap hardwareMap)
    {
      this.hardwareMap = hardwareMap;
      this.initialiseMotor();
      this.initialiseSensor();
      this.initialiseServo();
    }

    private void initialiseMotor()
    {
        // Connect objects to hardware
        hexMotor1 = hardwareMap.get(DcMotor.class, "Core_Hex_Motor_1");
        hexMotor2 = hardwareMap.get(DcMotor.class, "Core_Hex_Motor_2");

        rightMotor = hardwareMap.get(DcMotor.class, "HD_Hex_Motor_1");
        leftMotor = hardwareMap.get(DcMotor.class, "HD_Hex_Motor_2");

        // Setup motor direction as forwards
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        // Set zero power behavior: means when there is no power going to motor it breaks instead of free wheeling
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set default power to motors as zero
        rightMotor.setPower(0);
        leftMotor.setPower(0);

        /* Set motor mode: reset the motor ticks to 0
           I have not set the hex motors to reset encoder ticks as this may allow us to not have to have a shut down
           and boot up sequence to figure out where the arm is
        */
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set motors to use encoder
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        hexMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hexMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    private void initialiseServo()
    {
        // Connect objects to hardware
        continuousServo1 = hardwareMap.get(Servo.class, "Continuous_Rotation_Servo_1");
        continuousServo2 = hardwareMap.get(Servo.class, "Continuous_Rotation_Servo_2");

        servo1 = hardwareMap.get(Servo.class, "Servo_1");
        servo2 = hardwareMap.get(Servo.class, "Servo_2");
    }

    private void initialiseSensor()
    {
        // Connect objects to hardware
        touchSensor = hardwareMap.get(DigitalChannel.class, "Touch_Sensor_1");
        colourSensor = hardwareMap.get(NormalizedColorSensor.class, "Colour_Sensor_1");
    }


}

package org.firstinspires.ftc.teamcode;

public class MathsMethods {
    public static double[] JoystickToDifferential(double x, double y) {
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
            rawLeft = turn;
            rawRight = movement;
        } else {
            rawRight = turn;
            rawLeft = movement;
        }


        //Reverse polarity
        if (y < 0) {
            rawLeft = 0 - rawLeft;
            rawRight = 0 - rawRight;
        }

        return( new double[] {rawRight, rawLeft});
    }

    public static int CentimetresToMainMotorTicks(int centimetres) {
        return (int)(centimetres / (9 * Math.PI) * 720);
    }

    public static int InchesToMainMotorTicks(int inches) {
        return CentimetresToMainMotorTicks((int)(inches * 2.54));
    }

    public static int DegreesToMainMotorTicks(int degrees) {
        return MathsMethods.CentimetresToMainMotorTicks((int) (36 * Math.PI * 2 / (360 / degrees)));
    }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import static edu.wpi.first.units.Units.Meter;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class DriveConstants {
    // Motor controller IDs for drivetrain motors
    public static final int LEFT_LEADER_ID = 12;
    public static final int LEFT_FOLLOWER_ID = 18;
    public static final int RIGHT_LEADER_ID = 17;
    public static final int RIGHT_FOLLOWER_ID = 20;

    public static final double kPinionTeeth = 14; // Adjust this to match your configuration!
    public static final double kMotorFreeSpeed = 5676 / 60;
    public static final double kDrivingMotorReduction =  8.45; // 990 / (kPinionTeeth * 15);
    public static final double kWheelDiameterMeters = 0.1524;
    public static final double kDrivingEncoderPositionFactor = (kWheelDiameterMeters * Math.PI)
        / (double) kDrivingMotorReduction; // meters
    public static final double kDrivingEncoderVelocityFactor = ((kWheelDiameterMeters * Math.PI)
        / (double) kDrivingMotorReduction) / 60.0; // meters per second
    // Current limit for drivetrain motors. 60A is a reasonable maximum to reduce
    // likelihood of tripping breakers or damaging CIM motors
    public static final int DRIVE_MOTOR_CURRENT_LIMIT = 60;
  }

  public static final class FuelConstants {
    // Motor controller IDs for Fuel Mechanism motors
    public static final int FEEDER_MOTOR_ID = 15; //11;
    public static final int INTAKE_MOTOR_ID = 11; //13
    public static final int LAUNCHER_MOTOR_ID = 13;

    // Current limit and nominal voltage for fuel mechanism motors.
    public static final int FEEDER_MOTOR_CURRENT_LIMIT = 60;
    public static final int LAUNCHER_MOTOR_CURRENT_LIMIT = 60;

    // Voltage values for various fuel operations. These values may need to be tuned
    // based on exact robot construction.
    // See the Software Guide for tuning information
    public static final double INTAKING_FEEDER_VOLTAGE = 12;
    public static final double INTAKING_INTAKE_VOLTAGE = 10;
    public static final double LAUNCHING_FEEDER_VOLTAGE = 9;
    public static final double LAUNCHING_LAUNCHER_VOLTAGE = 12;
    public static final double SPIN_UP_FEEDER_VOLTAGE = -6;
    public static final double SPIN_UP_SECONDS = 1;
  }

  public static final class OperatorConstants {
    // Port constants for driver and operator controllers. These should match the
    // values in the Joystick tab of the Driver Station software
    public static final int DRIVER_CONTROLLER_PORT = 0;

    // This value is multiplied by the joystick value when rotating the robot to
    // help avoid turning too fast and beign difficult to control
    public static final double DRIVE_SCALING = .7;
    public static final double ROTATION_SCALING = .8;
  }

  public class LEDConstants {
    public static final double FIXED_RED = 0.61;
    public static final double FIXED_BLUE = 0.87;
    public static final double OFF = 0.99; // Black - off
    public static final double BREATH_GRAY = -0.19; // Good waiting pttern
  }
}

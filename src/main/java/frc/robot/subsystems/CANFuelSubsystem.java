// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.FuelConstants.*;

public class CANFuelSubsystem extends SubsystemBase {
  
  private final SparkMax feederRoller;
  private final SparkMax launcherRoller;
  private final SparkMax intakeRoller;

  private final RelativeEncoder m_launcherEncoder;
  private final SparkMaxConfig launcher_motor_config;
  private SparkClosedLoopController launcherController;

  //intakeLauncherRoller --> launcherRoller
  //new roller created: intakeRoller

  /** Creates a new CANBallSubsystem. */
  public CANFuelSubsystem() {
    // create brushed motors for each of the motors on the launcher mechanism
    launcherRoller = new SparkMax(LAUNCHER_MOTOR_ID, MotorType.kBrushless);
    intakeRoller = new SparkMax(INTAKE_MOTOR_ID, MotorType.kBrushless); 
    feederRoller = new SparkMax(FEEDER_MOTOR_ID, MotorType.kBrushless);

    launcherController = launcherRoller.getClosedLoopController();
    m_launcherEncoder =launcherRoller.getEncoder();
    launcher_motor_config = new SparkMaxConfig();
    launcher_motor_config.encoder.positionConversionFactor(1).velocityConversionFactor((1)/60);

    // create the configuration for the feeder roller, set a current limit and apply
    // the config to the controller
    SparkMaxConfig feederConfig = new SparkMaxConfig();
    feederConfig.smartCurrentLimit(FEEDER_MOTOR_CURRENT_LIMIT);
    feederRoller.configure(feederConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // create the configuration for the launcher roller, set a current limit, set
    // the motor to inverted so that positive values are used for both intaking and
    // launching, and apply the config to the controller
    SparkMaxConfig launcherConfig = new SparkMaxConfig();
    launcherConfig.inverted(true);
    launcherConfig.smartCurrentLimit(LAUNCHER_MOTOR_CURRENT_LIMIT);
    launcherConfig.closedLoop
        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
        // Set PID values for position control. We don't need to pass a closed loop
        // slot, as it will default to slot 0.
        .p(0.1)
        .i(0)
        .d(0)
        .outputRange(-1, 1)
        // Set PID values for velocity control in slot 1
        .p(0.0001, ClosedLoopSlot.kSlot1)
        .i(0, ClosedLoopSlot.kSlot1)
        .d(0, ClosedLoopSlot.kSlot1)
        .outputRange(-1, 1, ClosedLoopSlot.kSlot1)
        .feedForward
          // kV is now in Volts, so we multiply by the nominal voltage (12V)
          .kV(12.0 / 5767, ClosedLoopSlot.kSlot1);

    launcherRoller.configure(launcherConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // create the configuration for the intake roller, set a current limit and apply
    // the config to the controller
    SparkMaxConfig intakeConfig = new SparkMaxConfig();
    intakeConfig.inverted(true);
    intakeConfig.smartCurrentLimit(FEEDER_MOTOR_CURRENT_LIMIT);
    intakeRoller.configure(intakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // put default values for various fuel operations onto the dashboard
    // all commands using this subsystem pull values from the dashbaord to allow
    // you to tune the values easily, and then replace the values in Constants.java
    // with your new values. For more information, see the Software Guide.
    SmartDashboard.putNumber("Intaking feeder roller value", INTAKING_FEEDER_VOLTAGE);
    SmartDashboard.putNumber("Intaking intake roller value", INTAKING_INTAKE_VOLTAGE);
    SmartDashboard.putNumber("Launching feeder roller value", LAUNCHING_FEEDER_VOLTAGE);
    SmartDashboard.putNumber("Launching launcher roller value", LAUNCHING_LAUNCHER_VOLTAGE);
    SmartDashboard.putNumber("Spin-up feeder roller value", SPIN_UP_FEEDER_VOLTAGE);
  }

  // A method to set the voltage of the intake roller
  public void setIntakeRoller(double voltage) {
    intakeRoller.setVoltage(voltage);
  }

  // A method to set the voltage of the launcher roller
  public void setLauncherRoller(double voltage) {
    launcherRoller.setVoltage(voltage);
  }

  public void setLauncherRoller_PID(double targetVelocity){
        launcherController.setSetpoint(targetVelocity,  ControlType.kVelocity, ClosedLoopSlot.kSlot1);
  }

  // A method to set the voltage of the feeder roller
  public void setFeederRoller(double voltage) {
    feederRoller.setVoltage(voltage);
  }

  // A method to stop the rollers
  public void stop() {
    feederRoller.set(0);
    launcherRoller.set(0);
    intakeRoller.set(0);
  }

 public Command setLauncher(double speed) {
    return new InstantCommand(() -> launcherRoller.set(speed), this);
  }

 public Command setIntake(double speed) {
    return new InstantCommand(() -> intakeRoller.set(speed), this);
  }

 public Command setFeeder(double speed) {
    return new InstantCommand(() -> feederRoller.set(speed), this);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.setDefaultNumber("Target Position", 0);
    SmartDashboard.setDefaultNumber("Target Velocity", 0);
    SmartDashboard.setDefaultBoolean("Control Mode", false);
    SmartDashboard.setDefaultBoolean("Reset Encoder", false);
  }
}

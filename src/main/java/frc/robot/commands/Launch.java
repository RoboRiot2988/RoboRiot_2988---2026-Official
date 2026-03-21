// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANFuelSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Launch extends Command {
  /** Creates a new Intake. */

  private final CANFuelSubsystem fuelSubsystem;
  private final Timer timer = new Timer();
  private boolean staged = false;

  public Launch(CANFuelSubsystem fuelSystem) {
    addRequirements(fuelSystem);
    this.fuelSubsystem = fuelSystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // start timer and run only launcher for 0.5s
    timer.reset();
    timer.start();
    staged = false;

    fuelSubsystem.setLauncherRoller(-8);
    fuelSubsystem.setFeederRoller(0);
    fuelSubsystem.setIntakeRoller(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // after 0.5 seconds, enable feeder and intake while keeping launcher running
    if (!staged && timer.hasElapsed(0.75)) {
      fuelSubsystem.setFeederRoller(-12);
      fuelSubsystem.setIntakeRoller(12);
      staged = true;
    }
  }

  // Called once the command ends or is interrupted. Stop the rollers
  @Override
  public void end(boolean interrupted) {
    timer.stop();
    fuelSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

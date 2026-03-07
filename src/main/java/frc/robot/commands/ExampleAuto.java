// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANFuelSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ExampleAuto extends SequentialCommandGroup {
  /** Creates a new ExampleAuto. */
  public ExampleAuto(CANDriveSubsystem driveSubsystem, CANFuelSubsystem ballSubsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
  addCommands(
    // new AutoDrive(driveSubsystem, 1.0, 1.0).withTimeout(2.0),
    // new AutoDrive(driveSubsystem, 0.0, 0.0).withTimeout(1.0),
    
    // new Button_O(ballSubsystem).withTimeout(1.0),
    // new Launch(ballSubsystem).withTimeout(5.0),

    // new Intake(ballSubsystem).withTimeout(2.0),
    // new Button_O(ballSubsystem).withTimeout(1.0),
    // new Launch(ballSubsystem).withTimeout(5.0)
  );
  }
}
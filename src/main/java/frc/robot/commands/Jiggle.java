package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANFuelSubsystem;

public class Jiggle extends SequentialCommandGroup {
  /** Creates a new ExampleAuto. */
  public Jiggle(CANDriveSubsystem driveSubsystem, CANFuelSubsystem ballSubsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
  addCommands(
    new AutoDrive(driveSubsystem, 0.5, 0.5).withTimeout(0.1),
    new AutoDrive(driveSubsystem, -0.5, -0.5).withTimeout(0.1),
    new AutoDrive(driveSubsystem, 0.5, 0.5).withTimeout(0.1),
    new AutoDrive(driveSubsystem, -0.5, -0.5).withTimeout(0.1)
  );
  }
}
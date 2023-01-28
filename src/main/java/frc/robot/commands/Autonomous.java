// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


/*
 * Auto scoring: 2x high cone nodes & 1x high cube node; steps:
 * 1. score preload
 * 2. get game piece (cube; first intake)
 * 3. score game piece (cube; second score)
 * 4. get game piece (cone; second intake)
 * 5. score game piece (cone; third score)
 * 6. park
 */ 
//TODO note: this is the auto strategy for max efficiency to be used in qualification matches or if our allies can't do anything. 
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.LEDCOMM;

public class Autonomous extends SequentialCommandGroup {
  /** Creates a new Autonomous. */
  public Autonomous(LEDCOMM ledcomm) {
    // Use addRequirements() here to declare subsystem dependencies.
    addCommands(
      
    );
  }
}

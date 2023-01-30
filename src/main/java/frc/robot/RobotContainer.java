// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeStateMachine;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.LEDCOMM;
import frc.robot.subsystems.PrototypeTesting;
import frc.robot.subsystems.OuttakePrototype;
import frc.robot.subsystems.IntakeStateMachine.GamePiece;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  //LEDCOMM mLedcomm = new LEDCOMM();//TODO uncomment these when necessary
  //IntakeStateMachine mStateMachine = new IntakeStateMachine();
  //PrototypeTesting mPrototypeTesting = new PrototypeTesting();

  OuttakePrototype mOuttakePrototype = new OuttakePrototype();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  private final CommandXboxController m_operatorController = 
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    
    /*
      m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
      m_driverController.a().onTrue(new RunCommand(() -> mStateMachine.idleToCollect(), mStateMachine));
      m_driverController.x().onTrue(new RunCommand(() -> mStateMachine.cancelToIdle(), mStateMachine));

      m_driverController.leftBumper().onTrue(new RunCommand(() -> mStateMachine.updateTargetGamepiece(GamePiece.CONE), mStateMachine));
      m_driverController.rightBumper().onTrue(new RunCommand(()-> mStateMachine.updateTargetGamepiece(GamePiece.CUBE), mStateMachine));
     */
    
    //mLedcomm.setDefaultCommand(new RunCommand(() -> mLedcomm.turnOnLEDS(), mLedcomm));

    /*
     m_driverController.a().onTrue(new RunCommand(() -> mPrototypeTesting.spinIntake(0.5), mPrototypeTesting));//TODO: change speed where necessary
    m_driverController.a().onFalse(new RunCommand(() -> mPrototypeTesting.stopIntake(), mPrototypeTesting));

    m_driverController.b().onTrue(new RunCommand(() -> mPrototypeTesting.spinFeeder(0.5), mPrototypeTesting));//TODO: change speed where necessary
    m_driverController.b().onFalse(new RunCommand(() -> mPrototypeTesting.stopFeeder(), mPrototypeTesting));

    m_driverController.x().onTrue(new RunCommand(() -> mPrototypeTesting.moveFlipper(0.5), mPrototypeTesting));//TODO: change speed where necessary
    m_driverController.x().onFalse(new RunCommand(() -> mPrototypeTesting.stopFlipper(), mPrototypeTesting));
     */

    m_driverController.a().onTrue(new RunCommand(() -> mOuttakePrototype.OuttakeOut(0.2), mOuttakePrototype));
    m_driverController.b().onTrue(new RunCommand(() -> mOuttakePrototype.OuttakeOut(0.4), mOuttakePrototype));
    m_driverController.x().onTrue(new RunCommand(() -> mOuttakePrototype.OuttakeOut(0.6), mOuttakePrototype));
    m_driverController.y().onTrue(new RunCommand(() -> mOuttakePrototype.OuttakeOut(0.8), mOuttakePrototype));

    m_driverController.rightBumper().onTrue(new RunCommand(() -> mOuttakePrototype.OuttakeOut(1), mOuttakePrototype));


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}

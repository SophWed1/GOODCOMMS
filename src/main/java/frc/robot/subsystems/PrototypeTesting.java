// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

/*
 * Intake: 2 motors: 1 reverse, 1 forward
 * Feeder: 1 motor
 * Flipper: 2 motors: 1 reverse, 1 forward
 */

public class PrototypeTesting extends SubsystemBase {
  TalonFX intake1;
  TalonFX intake2;

  TalonFX feeder;

  TalonFX flipper1;
  TalonFX flipper2;
  /** Creates a new PrototypeTesting. */
  public PrototypeTesting() {
    intake1 = new TalonFX(0);//TODO: change these device numbers from 0 to whatever it is on the phoenix tuner.
    intake2 = new TalonFX(0);
    
    feeder = new TalonFX(0);

    flipper1 = new TalonFX(0);
    flipper2 = new TalonFX(0);

    intake1.setNeutralMode(NeutralMode.Brake);
    intake2.setNeutralMode(NeutralMode.Brake);
    feeder.setNeutralMode(NeutralMode.Brake);
    flipper1.setNeutralMode(NeutralMode.Brake);
    flipper2.setNeutralMode(NeutralMode.Brake);

    intake1.setInverted(true);//TODO: change where necessary 
    intake2.setInverted(false);

    flipper1.setInverted(true);//TODO: change where necessary 
    flipper2.setInverted(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void spinIntake(double speed){
    intake1.set(TalonFXControlMode.PercentOutput, speed);
    intake2.set(TalonFXControlMode.PercentOutput, speed);
  }
  
  public void stopIntake(){
    intake1.set(TalonFXControlMode.PercentOutput, 0);
    intake2.set(TalonFXControlMode.PercentOutput, 0);
  }

  public void spinFeeder(double speed){
    feeder.set(TalonFXControlMode.PercentOutput, speed);
  }

  public void stopFeeder(){
    feeder.set(TalonFXControlMode.PercentOutput, 0);
  }

  public void moveFlipper(double amount){//TODO: I imagine this would be a servo motor right? Please change where necessary - I think it would be a position instead of a power output...
    flipper1.set(TalonFXControlMode.PercentOutput, amount);
    flipper2.set(TalonFXControlMode.PercentOutput, amount);
  }

  public void stopFlipper(){
    flipper1.set(TalonFXControlMode.PercentOutput, 0);
    flipper2.set(TalonFXControlMode.PercentOutput, 0);
  }

}

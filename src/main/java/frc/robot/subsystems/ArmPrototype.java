// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class ArmPrototype extends SubsystemBase {
  TalonFX arm;//TODO: change where necessary
  
  /** Creates a new ArmPrototype. */
  public ArmPrototype() {
    arm = new TalonFX(0);
    arm.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void moveArm(double speed){
    arm.set(TalonFXControlMode.PercentOutput, speed);
  }

}

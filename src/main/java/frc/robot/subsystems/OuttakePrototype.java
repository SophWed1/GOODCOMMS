// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class OuttakePrototype extends SubsystemBase {
  private CANSparkMax outtake1;
  private CANSparkMax outtake2;

  /** Creates a new OuttakePrototype. */
  public OuttakePrototype() {
    outtake1 = new CANSparkMax(5, MotorType.kBrushless);//TODO: adjust where necessary
    outtake1.setInverted(false);
    outtake1.setIdleMode(IdleMode.kBrake);

    outtake2 = new CANSparkMax(6, MotorType.kBrushless);
    outtake2.setInverted(true);
    outtake1.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void OuttakeOut(double speed){
    outtake1.set(speed);
    outtake2.set(speed);
  }

  public void OuttakeIn(double speed){
    outtake1.set(-speed);
    outtake2.set(-speed);
  }

  public void stopOuttake(){
    outtake1.set(0);
    outtake2.set(0);
  }

}

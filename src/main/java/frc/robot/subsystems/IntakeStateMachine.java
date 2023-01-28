// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeStateMachine extends SubsystemBase {
  TalonFX IntakeLeft;
  TalonFX IntakeRight;

  TalonFX OuttakeLeft;
  TalonFX OuttakeRight;

  DigitalInput ProximitySensor;
  DigitalOutput ColourSensor;

  /** Creates a new IntakeStateMachine. */
  public IntakeStateMachine() {

    /* 
    IntakeLeft = new TalonFX(0);
    IntakeRight = new TalonFX(1);

    OuttakeLeft = new TalonFX(2);
    OuttakeRight = new TalonFX(3);
    */

    ProximitySensor = new DigitalInput(0);
    ColourSensor = new DigitalOutput(1);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    SmartDashboard.putString("State: ", state.toString());

    switch(state){
      case COLLECT:
        spinIntake(0.8);

        if (!ProximitySensor.get()){
          state = States.SENSOR_RETURN;
        }
        break;

      case IDLE:
        stopIntake();
        stopOuttake();
        break;

      case REJECT:
        //(I = on reverse if already stored; I = on && O = on if nothing is stored)
        if (storedGamePiece != GamePiece.NOTHING){
          spinIntake(-0.8);
        } else {//if we have nothing
          spinIntake(0.8);
          spinOuttake(0.8);
        }

        if (ProximitySensor.get()){
          state = States.COLLECT;
        }
        break;

      case SENSOR_RETURN:
        if (ColourSensor.get()){
          storedGamePiece = GamePiece.CONE;
        } else {
          storedGamePiece = GamePiece.CUBE;
        }

        if (storedGamePiece == targetGamePiece){
          state = States.STORE;
        } else {
          state = States.REJECT;
        }
        break;

      case STORE:
        stopIntake();
        spinOuttake(0.2);
        
        if (ProximitySensor.get()){//once the intake sees that there are no more than 1 gamepiece stored 
          state = States.IDLE;
        }
        break;
    }
    
  }

  public enum States {
    IDLE,
    COLLECT,
    SENSOR_RETURN,
    STORE,
    REJECT;
  }

  public enum GamePiece{
    CUBE,
    CONE,
    NOTHING;
  }

  States state = States.IDLE;

  GamePiece storedGamePiece = GamePiece.NOTHING;
  GamePiece targetGamePiece = GamePiece.NOTHING;

  public void idleToCollect(){

    if (state == States.IDLE){
      state = States.COLLECT;
    } 

  }

  public void cancelToIdle(){
    if (state != States.STORE){
      state = States.IDLE;
    }
  }

  public void updateTargetGamepiece(GamePiece targetGamePiece){
    this.targetGamePiece = targetGamePiece;
    SmartDashboard.putString("Target game piece: ", targetGamePiece.toString());
  }


  public void spinIntake(double speed){
    //IntakeLeft.set(TalonFXControlMode.PercentOutput, speed);
    //IntakeRight.set(TalonFXControlMode.PercentOutput, speed);
  }

  public void stopIntake(){
    //IntakeLeft.set(TalonFXControlMode.PercentOutput, 0);
    //IntakeRight.set(TalonFXControlMode.PercentOutput, 0);
  }

  public void spinOuttake(double speed){
    //OuttakeLeft.set(TalonFXControlMode.PercentOutput, speed);
    //OuttakeRight.set(TalonFXControlMode.PercentOutput, speed);
  }

  public void stopOuttake(){
    //OuttakeLeft.set(TalonFXControlMode.PercentOutput, 0);
    //OuttakeRight.set(TalonFXControlMode.PercentOutput, 0);
  }

}

/*
 * IDLE: stop all systems (I = off, O = off)
 * COLLECT: Run intake (I = on)
 * SENSOR RETURN: check colour sensor (I = off to check game piece)
 * STORE: store game piece (I = off, O = on to hold on game piece)
 * REJECT: rejecting the wrong gamepiece (I = on reverse if already stored; I = on && O = on if nothing is stored)
 */
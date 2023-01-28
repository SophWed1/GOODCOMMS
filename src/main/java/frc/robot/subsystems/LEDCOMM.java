// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.RainbowAnimation;

public class LEDCOMM extends SubsystemBase {
  DigitalInput ProximitySensor;
  DigitalOutput ColourSensor;
  CANdle mCaNdle;
  RainbowAnimation mRainbowAnimation;
  TalonFX LeftFront;
  TalonFX DTRR;

  /** Creates a new LEDCOMM. */
  public LEDCOMM() {
    ProximitySensor = new DigitalInput(0);
    ColourSensor = new DigitalOutput(1);
    mCaNdle = new CANdle(1);
    LeftFront = new TalonFX(3);
    DTRR = new TalonFX(6);
    mRainbowAnimation = new RainbowAnimation();

    LeftFront.setNeutralMode(NeutralMode.Brake);
    DTRR.setNeutralMode(NeutralMode.Brake);

    LeftFront.setInverted(true);
    DTRR.setInverted(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    mCaNdle.clearStickyFaults();
  }

  public boolean cubeDetection(){
    if (ProximitySensor.get() == false){
      return true;
    } else if (ProximitySensor.get() == false && ColourSensor.get() == true) {
      return true;
    } else {
      return false;
    }
  }

  public boolean coneDetection(){
    if (ColourSensor.get() == true){
      return true;
    } else if (ColourSensor.get() == true && ProximitySensor.get() == false) {
      return true;
    } else {
      return false;
    }
  }

  public void turnOnLEDS(){
    if (cubeDetection()){
      mCaNdle.clearAnimation(0);
      mCaNdle.setLEDs(94, 12, 166);    
      
      stopLeftFront();
      stopDTRR();

    } else if (coneDetection()){

      mCaNdle.clearAnimation(0);
      mCaNdle.setLEDs(252, 229, 28);

      stopLeftFront();
      stopDTRR();

    } else {

      LeftFront.set(TalonFXControlMode.PercentOutput, 0.4);
      DTRR.set(TalonFXControlMode.PercentOutput, 0.4);

      mCaNdle.clearAnimation(0);
      //mCaNdle.animate(mRainbowAnimation);
    }

  }

  public void stopLeftFront(){
    LeftFront.set(TalonFXControlMode.PercentOutput, 0);
  }

  public void stopDTRR(){
    DTRR.set(TalonFXControlMode.PercentOutput, 0);
  }

}

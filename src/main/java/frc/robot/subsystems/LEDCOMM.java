// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;

import javax.swing.plaf.basic.BasicBorders.MarginBorder;

import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.RainbowAnimation;

public class LEDCOMM extends SubsystemBase {
  DigitalInput ProximitySensor;
  DigitalOutput ColourSensor;
  CANdle mCaNdle;
  RainbowAnimation mRainbowAnimation;
  /** Creates a new LEDCOMM. */
  public LEDCOMM() {
    ProximitySensor = new DigitalInput(0);
    ColourSensor = new DigitalOutput(1);
    mCaNdle = new CANdle(1);
    mRainbowAnimation = new RainbowAnimation();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    mCaNdle.clearStickyFaults();
  }

  public boolean cubeDetection(){
    if (ProximitySensor.get() == false){
      return true;
    } else {
      return false;
    }
  }

  public boolean coneDetection(){
    if (ColourSensor.get() == true){
      return true;
    } else {
      return false;
    }
  }

  public void turnOnLEDS(){
    if (cubeDetection()){
      mCaNdle.clearAnimation(0);
      mCaNdle.setLEDs(94, 12, 166);
    } else if (coneDetection()){
      mCaNdle.clearAnimation(0);
      mCaNdle.setLEDs(252, 229, 28);
    } else {
      mCaNdle.animate(mRainbowAnimation);
    }

  }

}

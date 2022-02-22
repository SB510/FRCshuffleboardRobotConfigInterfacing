// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.util.WPILibVersion;

public class Robot extends TimedRobot {
  RobotConfig robotWork = new RobotConfig();
  private long lastTime;
  @Override
  public void robotInit() {

    // Add a 'max speed' widget to a tab named 'Configuration', using a number slider

    robotWork.InitializeShuffleBoard();
    while (true){
      if(System.currentTimeMillis()-lastTime > 1000){
        robotWork.UpdateVariables();
        //System.out.println(RobotConfig.driveTrain.autoCorrect);
        lastTime = System.currentTimeMillis();
      }
      
    }
  }

  @Override
  public void autonomousInit() {

  }
}

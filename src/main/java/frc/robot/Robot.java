package frc.robot;


import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.FeedCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.Shooter;


public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  
  //TODO: set constants
  private final XboxController m_controller = new XboxController(0); 

  //left motors
  private final WPI_VictorSPX m_leftMotorMaster = new WPI_VictorSPX(4); 
  private final WPI_VictorSPX m_leftMotorSlave = new WPI_VictorSPX(5);

  //right motors
  private final WPI_VictorSPX m_rightMotorMaster = new WPI_VictorSPX(3);
  private final WPI_VictorSPX m_rightMotorSlave = new WPI_VictorSPX(6);
  
  private final Shooter shooter = new Shooter();
  
  private Command feed = new FeedCommand(shooter);
  private Command shoot = new ShootCommand(shooter);

  double moveSpeed;
  double rotateSpeed;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    
    m_leftMotorSlave.follow(m_leftMotorMaster);
    m_rightMotorSlave.follow(m_rightMotorMaster);

    m_rightMotorMaster.setInverted(true);

    m_leftMotorSlave.setInverted(InvertType.FollowMaster);
    m_rightMotorSlave.setInverted(InvertType.FollowMaster);
    
  }


  @Override
  public void robotPeriodic() {
    //get speed
    moveSpeed = -m_controller.getLeftY(); //TODO
    rotateSpeed = m_controller.getRightX();

    if(Math.abs(moveSpeed) < 0.05) {
      moveSpeed = 0;
    }

    if(Math.abs(rotateSpeed) < 0.05) {
      rotateSpeed = 0;
    }
    
    arcadeDrive(moveSpeed * 0.2, rotateSpeed * 0.1);

    if (m_controller.getAButtonPressed()) {
      shoot.schedule();
    }
    if (m_controller.getBButtonPressed()) {
      feed.schedule();
    } else if (m_controller.getBButtonReleased()){
      feed.cancel();
    }
 
    CommandScheduler.getInstance().run();
  }

  private void arcadeDrive(double moveSpeed, double rotateSpeed) {
    m_leftMotorMaster.set(rotateSpeed + moveSpeed);
    m_rightMotorMaster.set(-rotateSpeed + moveSpeed);
  }


  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}

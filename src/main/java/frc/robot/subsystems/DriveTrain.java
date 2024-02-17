package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
//left motors
  public final WPI_VictorSPX m_leftMotorMaster = new WPI_VictorSPX(4); 
  public final WPI_VictorSPX m_leftMotorSlave = new WPI_VictorSPX(5);

  //right motors
  public final WPI_VictorSPX m_rightMotorMaster = new WPI_VictorSPX(3);
  public final WPI_VictorSPX m_rightMotorSlave = new WPI_VictorSPX(6);
  

  public DriveTrain () {
    m_leftMotorSlave.follow(m_leftMotorMaster);
    m_rightMotorSlave.follow(m_rightMotorMaster);

    m_rightMotorMaster.setInverted(true);

    m_leftMotorSlave.setInverted(InvertType.FollowMaster);
    m_rightMotorSlave.setInverted(InvertType.FollowMaster);
  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed) {
    m_leftMotorMaster.set(rotateSpeed + moveSpeed);
    m_rightMotorMaster.set(-rotateSpeed + moveSpeed);
  }

}


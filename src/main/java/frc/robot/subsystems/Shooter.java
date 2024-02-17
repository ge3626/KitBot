package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    public final WPI_VictorSPX shooter = new WPI_VictorSPX(2);
    public final WPI_VictorSPX feeder = new WPI_VictorSPX(7);
}

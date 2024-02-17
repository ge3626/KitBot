package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

public class MoveCommand extends Command {
    private final DriveTrain drive;
    
    private final WPI_VictorSPX leftMorterMaster;
    private final WPI_VictorSPX leftMorterSlave;
    private final WPI_VictorSPX rightMorterMaster;
    private final WPI_VictorSPX rightMorterSlave;

    public MoveCommand(DriveTrain drive) {
        this.drive = drive;
        addRequirements(drive);

        leftMorterMaster = drive.m_leftMotorMaster;
        leftMorterSlave = drive.m_leftMotorSlave;
        rightMorterMaster = drive.m_rightMotorMaster;
        rightMorterSlave = drive.m_rightMotorSlave;
    }

    Timer timer = new Timer();
    double driveSpeed;
    double rotateSpeed;

    @Override
    public void initialize() {
        driveSpeed = 1;
        rotateSpeed = 1;
        timer.restart();
        super.initialize();
    }

    @Override
    public void execute() {
        if (timer.get() < 2) {
            leftMorterMaster.set(driveSpeed * 0.1);
            rightMorterMaster.set(driveSpeed * 0.1);
        } else if (timer.get() > 2 && timer.get() < 3) {
            //turn right
            leftMorterMaster.set(rotateSpeed * 0.3);
            rightMorterMaster.set(-rotateSpeed * 0.3);
        } else if (timer.get() > 3 && timer.get() < 6) {
            leftMorterMaster.set(-driveSpeed * 0.2);
            rightMorterMaster.set(-driveSpeed * 0.2);
        }
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        leftMorterMaster.set(0);
        rightMorterMaster.set(0);
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
    return (timer.get() > 7);
    }
}

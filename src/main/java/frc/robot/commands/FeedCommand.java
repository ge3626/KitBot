package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class FeedCommand extends Command {
    private final Shooter shooter;

    double feedSpeed = -1;

    public FeedCommand(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.shooter.set(feedSpeed);
        shooter.feeder.set(feedSpeed);
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        shooter.shooter.set(0);
        shooter.feeder.set(0);
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return false;
    }


}

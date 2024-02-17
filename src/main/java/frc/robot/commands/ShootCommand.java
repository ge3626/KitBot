package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class ShootCommand extends Command {

    private final Shooter shooter;

    public ShootCommand(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    Timer timer = new Timer();
    double feedSpeed;
    double shootSpeed;
    
    @Override
    public void initialize() {
        feedSpeed = 1;
        shootSpeed = 1;
        timer.restart();
        super.initialize();
    }

    @Override
    public void execute() {

        if(timer.get() < 1) {
            shooter.shooter.set(shootSpeed);
        } else {
            shooter.shooter.set(shootSpeed);
            shooter.feeder.set(feedSpeed);
        }
        
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
        return timer.get() > 1.5;
    }

}

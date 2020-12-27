package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Hatch;

public class MoveHatch extends CommandBase
{

    private Hatch hatch;
    private boolean dir;

    public MoveHatch(Hatch hatch, boolean dir)
    {
        this.hatch = hatch;
        this.dir = dir;
    }

    @Override
    public void initialize()
    {
        hatch.setHatch(dir);
    }

    @Override
    public void execute()
    {
        hatch.setHatch(dir);
    }

    @Override
    public boolean isFinished()
    {
        return !Robot.m_robotContainer.getJoystick().getRawButtonPressed(Constants.HATCH_BUTTON_PORT);
    }

    @Override
    public void end(boolean interrupted)
    {
        hatch.setHatch(!dir);
    }
    

}
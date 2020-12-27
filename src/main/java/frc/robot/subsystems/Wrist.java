package frc.robot.subsystems;


import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Wrist extends SubsystemBase
{

    private SpeedController wristController;
    private boolean top;
    private boolean bottom;

    public Wrist(SpeedController wristController)
    {
        this.wristController = wristController;
    }

    public void moveWrist(double speed)
    {

        if(speed > 0 && top)
            wristController.set(speed);

        else if(speed < 0 && bottom)
            wristController.set(speed);

        else
            stopWrist();

    }

    public void stopWrist()
    {
        wristController.set(0);
    }

    @Override
    public void periodic()
    {

        if(Robot.m_robotContainer.getWristPotent().get() <= Constants.WRIST_BOTTOM)
            top = true;
        
        else
            top = false;

        if(Robot.m_robotContainer.getWristPotent().get() >= Constants.WRIST_TOP)
            bottom = true;
        
        else
            bottom = false;

    }

    public boolean getTop()
    {
        return top;
    }

    public boolean getBottom()
    {
        return bottom;
    }

}
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Arm extends SubsystemBase
{
    private SpeedController armController;
    private boolean top;
    private boolean bottom;


    public Arm(SpeedController armController)
    {
        this.armController = armController;
    }

    public void moveArm(double speed)
    {

        
        if(speed > 0 && top)
            armController.set(speed);

        else if(speed < 0 && bottom)
            armController.set(speed);

        else
            stopArm();

    }

    public void stopArm()
    {
        armController.set(0);

    }

    @Override
    public void periodic()
    {
        //TOP and BOTTOM are swapped
        if(Robot.m_robotContainer.getArmPotent().get() <= Constants.ARM_BOTTOM)
            top = true;
        else
            top = false;

        if(Robot.m_robotContainer.getArmPotent().get() >= Constants.ARM_TOP)
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
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Arm extends SubsystemBase
{
    private SpeedController armController;

    public Arm(SpeedController armController)
    {
        this.armController = armController;
    }

    public void moveArm(double speed)
    {

        //TOP and BOTTOM are swapped
        if(speed > 0 && Robot.m_robotContainer.getArmPotent().get() <= Constants.ARM_BOTTOM)
            armController.set(speed);

        else if(speed < 0 && Robot.m_robotContainer.getArmPotent().get() >= Constants.ARM_TOP)
            armController.set(speed);

        else
            stopArm();

    }

    public void stopArm()
    {
        armController.set(0);

    }


}
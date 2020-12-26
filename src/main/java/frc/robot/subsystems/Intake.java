package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase
{
    private SpeedController left;
    private SpeedController right;

    public Intake(SpeedController left, SpeedController right)
    {
        this.left = left;
        this.right = right;
    }

    public void moveIntake(double speed)
    {
        left.set(speed);
        right.set(-speed);
    }

    public void stopIntake()
    {
        left.stopMotor();
        right.stopMotor();
    }


}
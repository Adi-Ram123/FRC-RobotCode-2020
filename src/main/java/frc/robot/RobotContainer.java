/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.MoveArm;
import frc.robot.commands.MoveHatch;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.MoveWrist;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Wrist;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private SpeedController leftUp, leftDown, rightUp, rightDown;
  private SpeedControllerGroup left, right;
  private DifferentialDrive drive;
  private DriveTrain driveTrain;

  private SpeedController intakeLeft, intakeRight;
  private Button intakeIn, intakeOut; 
  private Intake intake;

  private Hatch hatch;
  private Solenoid hatchSol;
  private Button hatchButton;

  private AnalogPotentiometer armPotent;
  private SpeedController armController;
  private Arm arm;
  private Button armUp, armDown;

  private AnalogPotentiometer wristPotent;
  private SpeedController wristController;
  private Wrist wrist;
  private Button wristUp, wristDown;

  private Joystick joy;
  

  
  



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    // DriveTrain
    leftUp = new SteelTalonsController(Constants.LEFT_UP_PORT, false, 1);
    leftDown = new SteelTalonsController(Constants.LEFT_DOWN_PORT, false, 1);
    rightUp = new SteelTalonsController(Constants.RIGHT_UP_PORT, false, 1);
    rightDown = new SteelTalonsController(Constants.RIGHT_DOWN_PORT, false, 1);

    left = new SpeedControllerGroup(leftUp, leftDown);
    right = new SpeedControllerGroup(rightUp, rightDown);

    drive = new DifferentialDrive(left, right);

    driveTrain = new DriveTrain(left, right, drive);

    driveTrain.setDefaultCommand(new DriveWithJoystick());

    //Intake
    intakeLeft = new SteelTalonsController(Constants.INTAKE_LEFT_PORT, false, 1);
    intakeRight = new SteelTalonsController(Constants.INTAKE_RIGHT_PORT, false, 1);
    intake = new Intake(intakeLeft, intakeRight);

    //Hatch Intake
    hatchSol = new Solenoid(Constants.SOLENOID_PORT);
    hatch = new Hatch(hatchSol);

    //Arm
    armPotent = new AnalogPotentiometer(Constants.ARM_POTENT_PORT);
    armController = new SteelTalonsController(Constants.ARM_CONTROL_PORT, false, 1);
    arm = new Arm(armController);

    //Wrist
    wristPotent = new AnalogPotentiometer(Constants.WRIST_POTENT_PORT);
    wristController = new SteelTalonsController(Constants.WRIST_CONTROL_PORT, false, 1);
    wrist = new Wrist(wristController);
    

    //ConfigureButtons
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() 
  {
    joy = new Joystick(0);

    intakeIn = new JoystickButton(joy, Constants.INTAKE_IN_BUTTON);
    intakeOut = new JoystickButton(joy, Constants.INTAKE_OUT_BUTTON);
    intakeIn.whileHeld(new MoveIntake(Constants.INTAKE_IN_SPEED));
    intakeOut.whileHeld(new MoveIntake(Constants.INTAKE_OUT_SPEED));

    hatchButton = new JoystickButton(joy, Constants.HATCH_BUTTON_PORT);
    hatchButton.whileHeld(new MoveHatch(hatch, Constants.HATCH_DIRECTION));

    armUp = new JoystickButton(joy, Constants.ARM_UP_BUTTON);
    armDown = new JoystickButton(joy, Constants.ARM_DOWN_BUTTON);
    armUp.whileHeld(new MoveArm(Constants.ARM_CONTROL_SPEED));
    armDown.whileHeld(new MoveArm(-Constants.ARM_CONTROL_SPEED));

    wristUp = new JoystickButton(joy, Constants.WRIST_UP_BUTTON);
    wristDown = new JoystickButton(joy, Constants.WRIST_DOWN_BUTTON);
    wristUp.whileHeld(new MoveWrist(Constants.WRIST_CONTROL_SPEED));
    wristDown.whileHeld(new MoveWrist(-Constants.WRIST_CONTROL_SPEED));
    



  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }

   public DriveTrain getDriveTrain()
   {
     return driveTrain;
   }

   public Intake getIntake()
   {
     return intake;
   }

   public Joystick getJoystick()
   {
     return joy;
   }

   public AnalogPotentiometer getArmPotent()
   {
     return armPotent;
   }

   public Arm getArm()
   {
     return arm;
   }

   public AnalogPotentiometer getWristPotent()
   {
     return wristPotent;
   }

   public Wrist getWrist()
   {
     return wrist;
   }

   public Hatch getHatch()
   {
     return hatch;
   }

}

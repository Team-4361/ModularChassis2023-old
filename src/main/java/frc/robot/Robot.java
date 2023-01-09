// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.state.ChassisState;
import frc.robot.state.DriveControl;
import frc.robot.state.DriveMode;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.TalonSubsystem;

import static frc.robot.Constants.DefaultState.*;
import static frc.robot.subsystems.TalonSubsystem.talon4;


/**
 * The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private Command autonomousCommand;
    private RobotContainer robotContainer;

    public static DriveSubsystem driveSubsystem;
    public static TalonSubsystem modTalon;

    public static ChassisState chassisState = DEFAULT_CHASSIS_STATE;
    public static DriveControl driveControl = DEFAULT_DRIVE_CONTROL;
    public static DriveMode driveMode = DEFAULT_DRIVE_MODE;

    private SendableChooser<ChassisState> chassisStateChooser;
    private SendableChooser<DriveControl> driveControlChooser;
    private SendableChooser<DriveMode> driveModeChooser;

    
    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
        // autonomous chooser on the dashboard.
        robotContainer = new RobotContainer();
        driveSubsystem = new DriveSubsystem();
        modTalon = new TalonSubsystem();

        // Initialize the SendableChoosers responsible for allowing the user to select the desired options.
        chassisStateChooser = new SendableChooser<>();
        driveControlChooser = new SendableChooser<>();
        driveModeChooser = new SendableChooser<>();

        chassisStateChooser.addOption("Frisbee Chassis", ChassisState.FRISBEE);
        chassisStateChooser.addOption("Hammer Chassis", ChassisState.HAMMER);
        chassisStateChooser.addOption("Romulus Chassis", ChassisState.ROMULUS);
        chassisStateChooser.addOption("T-Shirt Chassis", ChassisState.T_SHIRT);

        driveControlChooser.addOption("Joystick Drive", DriveControl.JOYSTICK);
        driveControlChooser.addOption("Xbox Drive", DriveControl.XBOX);

        driveModeChooser.addOption("Tank Drive", DriveMode.TANK);
        driveModeChooser.addOption("Arcade Drive",  DriveMode.ARCADE);

        switch (chassisState) {
            case FRISBEE: chassisStateChooser.setDefaultOption("Frisbee Chassis", ChassisState.FRISBEE); break;
            case HAMMER: chassisStateChooser.setDefaultOption("Hammer Chassis", ChassisState.HAMMER); break;
            case ROMULUS: chassisStateChooser.setDefaultOption("Romulus Chassis", ChassisState.ROMULUS); break;
            case T_SHIRT: chassisStateChooser.setDefaultOption("T-Shirt Chassis", ChassisState.T_SHIRT); break;
        }

        switch (driveControl) {
            case XBOX: driveControlChooser.setDefaultOption("Xbox Drive", DriveControl.XBOX); break;
            case JOYSTICK: driveControlChooser.setDefaultOption("Joystick Drive", DriveControl.JOYSTICK); break;
        }

        switch (driveMode) {
            case TANK: driveModeChooser.setDefaultOption("Tank Drive", DriveMode.TANK); break;
            case ARCADE: driveModeChooser.setDefaultOption("Arcade Drive", DriveMode.ARCADE); break;
        }
    }

    /**
     * This method is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic methods, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();

        chassisState = chassisStateChooser.getSelected();
        driveControl = driveControlChooser.getSelected();
        driveMode = driveModeChooser.getSelected();
    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }
    
    
    /** This method is called periodically during test mode. */
    @Override
    public void testPeriodic() {}
}

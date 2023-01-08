// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.StateBuilder;
import frc.robot.commands.VariableCommand;

import static frc.robot.Constants.Control.*;
import static frc.robot.Robot.*;
import static frc.robot.commands.VariableCommand.motorSW;
import static frc.robot.subsystems.TalonSubsystem.*;
import static frc.robot.state.ChassisState.*;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private final CommandJoystick leftStick = new CommandJoystick(LEFT_STICK_ID);
    private final CommandJoystick rightStick = new CommandJoystick(RIGHT_STICK_ID);
    private final CommandXboxController xbox = new CommandXboxController(XBOX_ID);

    private final Trigger tShirtLaunch = xbox.rightTrigger().and(xbox.leftTrigger().and(xbox.x()));

    // TODO: add t-shirt cannon controls once recognized.
    private final VariableCommand COMMAND_LIST = new VariableCommand()
            .addState(new StateBuilder(FRISBEE)
                    .onTrigger(xbox.a(), motorSW(talon2(), 1))
                    .onTrigger(xbox.b(), motorSW(talon1(), 1))
            )
            .addState(new StateBuilder(HAMMER)
                    .onTrigger(xbox.x(), motorSW(talon1(), -1)
                            .alongWith(motorSW(talon2(), -1)))
                    .onTrigger(xbox.a(), motorSW(talon1(), 1)
                            .alongWith(motorSW(talon2(),  1)))
            )
            .addState(new StateBuilder(ROMULUS)
                    .onTrigger(xbox.a(), motorSW(talon3(), -1))
                    .onTrigger(xbox.y(), motorSW(talon3(), 1))
                    .onTrigger(xbox.b(), motorSW(talon2(), -1)
                            .alongWith(motorSW(talon4(), 1)))
            )
            .addState(new StateBuilder(T_SHIRT)
                    .onTrigger(xbox.leftBumper(), motorSW(talon1(), 1))
                    .onTrigger(xbox.rightBumper(), motorSW(talon1(), -1))
                    .onTrigger(xbox.a(), motorSW(talon2(), -0.45))
                    .onTrigger(xbox.y(), motorSW(talon2(), 0.45))
                    .onTrigger(tShirtLaunch, motorSW(talon4(), -1)
                            .withTimeout(2))
            );
    
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        driveSubsystem.setDefaultCommand(driveSubsystem.run(() -> {
            switch (driveState) {
                case XBOX:
                    switch (driveMode) {
                        case TANK:
                            driveSubsystem.tankDrive(xbox.getLeftY(), xbox.getRightY());
                        case ARCADE:
                            driveSubsystem.arcadeDrive(xbox.getLeftY(), xbox.getRightX());
                    }
                    break;
                case JOYSTICK:
                    switch (driveMode) {
                        case TANK:
                            driveSubsystem.tankDrive(leftStick.getY(), rightStick.getY());
                        case ARCADE:
                            driveSubsystem.arcadeDrive(leftStick.getY(), rightStick.getTwist());
                    }
                    break;
            }
        }));

        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        // Add button to command mappings here.
        // See https://docs.wpilib.org/en/stable/docs/software/commandbased/binding-commands-to-triggers.html
        xbox.x().whileTrue(COMMAND_LIST.execute(xbox.x()));
        xbox.y().whileTrue(COMMAND_LIST.execute(xbox.y()));
        xbox.a().whileTrue(COMMAND_LIST.execute(xbox.a()));
        xbox.b().whileTrue(COMMAND_LIST.execute(xbox.b()));

        // This is guaranteed to only run the Command under the t-shirt cannon mode, as this button
        // combination is not used elsewhere. The only reason I still wanted this separate is to prevent
        // false motor activations while under a different mode.
        tShirtLaunch.whileTrue(COMMAND_LIST.execute(tShirtLaunch));
    }
}

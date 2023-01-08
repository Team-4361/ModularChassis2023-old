package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import static frc.robot.Constants.Drive.*;
import static frc.robot.Constants.MotorFlip.*;

public class DriveSubsystem extends SubsystemBase {
    private DifferentialDrive drive;
    private WPI_TalonSRX flDrive, frDrive, blDrive, brDrive;

    /**
     * Construct a DifferentialDrive.
     *
     * <p>To pass multiple motors per side, use a {@link
     * MotorControllerGroup}. If a motor needs to be inverted, do
     * so before passing it in.
     */
    public DriveSubsystem() {
        this.flDrive = new WPI_TalonSRX(FL_DRIVE_ID);
        this.frDrive = new WPI_TalonSRX(FR_DRIVE_ID);
        this.blDrive = new WPI_TalonSRX(BL_DRIVE_ID);
        this.brDrive = new WPI_TalonSRX(BR_DRIVE_ID);

        flDrive.setInverted(FL_DRIVE_FLIPPED);
        frDrive.setInverted(FR_DRIVE_FLIPPED);
        blDrive.setInverted(BL_DRIVE_FLIPPED);
        brDrive.setInverted(BR_DRIVE_FLIPPED);

        this.drive = new DifferentialDrive(
                new MotorControllerGroup(flDrive, frDrive),
                new MotorControllerGroup(blDrive, brDrive)
        );
    }

    public void tankDrive(double leftVelocity, double rightVelocity) {
        drive.tankDrive(leftVelocity, rightVelocity);
    }

    public void arcadeDrive(double xSpeed, double zRotation) {
        drive.arcadeDrive(xSpeed, zRotation);
    }
}

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.ModTalon.*;
import static frc.robot.Constants.ModTalon.MOD_TALON4_ID;
import static frc.robot.Constants.MotorFlip.*;

public class TalonSubsystem extends SubsystemBase {
    private static WPI_TalonSRX modTalon1, modTalon2, modTalon3, modTalon4;

    public TalonSubsystem() {
        modTalon1 = new WPI_TalonSRX(MOD_TALON1_ID);
        modTalon2 = new WPI_TalonSRX(MOD_TALON2_ID);
        modTalon3 = new WPI_TalonSRX(MOD_TALON3_ID);
        modTalon4 = new WPI_TalonSRX(MOD_TALON4_ID);

        modTalon1.setInverted(MOD_TALON1_FLIPPED);
        modTalon2.setInverted(MOD_TALON2_FLIPPED);
        modTalon3.setInverted(MOD_TALON3_FLIPPED);
        modTalon4.setInverted(MOD_TALON4_FLIPPED);
    }

    public static WPI_TalonSRX talon1() { return modTalon1; }
    public static WPI_TalonSRX talon2() { return modTalon2; }
    public static WPI_TalonSRX talon3() { return modTalon3; }
    public static WPI_TalonSRX talon4() { return modTalon4; }

    public static void talon1(double speed) { modTalon1.set(speed); }
    public static void talon2(double speed) { modTalon2.set(speed); }
    public static void talon3(double speed) { modTalon3.set(speed); }
    public static void talon4(double speed) { modTalon4.set(speed); }

    public static String formatMotorState(WPI_TalonSRX motor) {
        return motor.get()*100 + "% : " + motor.getTemperature() + " C";
    }

    /**
     * This method is called periodically by the {@link CommandScheduler}. Useful for updating
     * subsystem-specific state that you don't want to offload to a {@link Command}. Teams should try
     * to be consistent within their own codebases about which responsibilities will be handled by
     * Commands, and which will be handled here.
     */
    @Override
    public void periodic() {
        SmartDashboard.putString("ModTalon1", formatMotorState(modTalon1));
        SmartDashboard.putString("ModTalon2", formatMotorState(modTalon2));
        SmartDashboard.putString("ModTalon3", formatMotorState(modTalon3));
        SmartDashboard.putString("ModTalon4", formatMotorState(modTalon4));
    }
}

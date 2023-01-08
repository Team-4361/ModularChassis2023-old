// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.state.ChassisState;
import frc.robot.state.DriveControl;
import frc.robot.state.DriveMode;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static class DefaultState {
        public final static ChassisState DEFAULT_CHASSIS_STATE = ChassisState.ROMULUS;
        public final static DriveControl DEFAULT_DRIVE_CONTROL = DriveControl.JOYSTICK;
        public final static DriveMode DEFAULT_DRIVE_MODE = DriveMode.TANK;
    }

    public static class MotorFlip {
        // TODO: Change values accordingly after initial testing.
        public final static boolean FL_DRIVE_FLIPPED = false;
        public final static boolean FR_DRIVE_FLIPPED = false;
        public final static boolean BL_DRIVE_FLIPPED = false;
        public final static boolean BR_DRIVE_FLIPPED = false;

        public final static boolean MOD_TALON1_FLIPPED = false;
        public final static boolean MOD_TALON2_FLIPPED = false;
        public final static boolean MOD_TALON3_FLIPPED = false;
        public final static boolean MOD_TALON4_FLIPPED = false;
    }

    public static class Drive {
        public final static int FL_DRIVE_ID = 3;
        public final static int FR_DRIVE_ID = 1;
        public final static int BL_DRIVE_ID = 4;
        public final static int BR_DRIVE_ID = 2;
    }

    public static class ModTalon {
        public final static int MOD_TALON1_ID = 8;
        public final static int MOD_TALON2_ID = 5;
        public final static int MOD_TALON3_ID = 7;
        public final static int MOD_TALON4_ID = 6;
    }

    public static class Control {
        public final static int LEFT_STICK_ID = 0;
        public final static int RIGHT_STICK_ID = 1;
        public final static int XBOX_ID = 2;
    }
}

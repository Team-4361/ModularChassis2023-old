package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Robot;
import frc.robot.state.ChassisState;

import java.util.HashMap;

public class VariableCommand {
    protected final HashMap<ChassisState, HashMap<Trigger, Command>> commandMap;

    public VariableCommand() {
        this.commandMap = new HashMap<>();
    }

    public VariableCommand addState(StateBuilder stateBuilder) {
        commandMap.put(stateBuilder.getState(), stateBuilder.build());
        return this;
    }

    public static Command motorSW(WPI_TalonSRX motor, double on) {
        return Robot.modTalon.runEnd(() -> motor.set(on), () -> motor.set(0));
    }

    public Command execute(Trigger trigger, ChassisState state) {
        return commandMap.get(state).get(trigger);
    }

    public Command execute(Trigger trigger) {
        return this.execute(trigger, Robot.chassisState);
    }
}


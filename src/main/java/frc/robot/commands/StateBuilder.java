package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.state.ChassisState;

import java.util.HashMap;

public class StateBuilder {
    private final ChassisState state;
    private final HashMap<Trigger, Command> triggerMap;

    public StateBuilder(ChassisState state) {
        this.state = state;
        this.triggerMap = new HashMap<>();
    }

    public StateBuilder onTrigger(Trigger trigger, Command command) {
        triggerMap.put(trigger, command);
        return this;
    }

    public ChassisState getState() {
        return state;
    }

    public HashMap<Trigger, Command> build() {
        return triggerMap;
    }
}

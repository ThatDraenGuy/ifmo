package cmd;

import java.util.HashMap;

public class CmdHandler {
    protected HashMap<String, Command> cmds;
    public CmdHandler() {
        this.cmds=new HashMap<>();
    }
    public void addComm(Command c) {
        final String name = c.getName();
        if (!isInComms(name)) {
            this.cmds.put(name,c);
        }
    }
    public void addComms(Command ... comms) {
        for (Command c : comms) {
            addComm(c);
        }
    }
    public boolean isInComms(String name) {
        return this.cmds.containsKey(name);
    }
}

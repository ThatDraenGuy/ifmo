package comms;

import java.util.HashMap;

public class CommList {
    protected HashMap<String, Command> comms;
    public CommList() {
        this.comms=new HashMap<>();
    }
    public void addComm(Command c) {
        final String name = c.getName();
        if (!isInComms(name)) {
            this.comms.put(name,c);
        }
    }
    public void addComms(Command ... comms) {
        for (Command c : comms) {
            addComm(c);
        }
    }
    public boolean isInComms(String name) {
        return this.comms.containsKey(name);
    }
}

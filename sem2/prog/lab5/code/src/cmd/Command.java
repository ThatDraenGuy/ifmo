package cmd;

public abstract class Command {
    protected String name;
    protected String description;
    public Command(String n, String d) {
        this.name=n;
        this.description=d;
    }
    public abstract void action();
    public String getName() {
        return name;
    }
    public String getDescription() {
        return  description;
    }
}

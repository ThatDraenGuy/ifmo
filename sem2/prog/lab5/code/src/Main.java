import comms.CommList;
import comms.commands.Help;

public class Main {
    public static void main(String ... args) {
        CommList commList = new CommList();
        commList.addComms(new Help());
    }
}

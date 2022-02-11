import Collection.CollectionHandler;
import Collection.StorageHandler;
import cmd.CmdHandler;
import cmd.commands.Help;

import java.io.File;

public class Main {
    public static void main(String ... args) {
        String filePath = String.join(",",args);
        StorageHandler storageHandler = new StorageHandler(new File(filePath));
        CollectionHandler collectionHandler = new CollectionHandler(storageHandler);
        collectionHandler.load();
        CmdHandler cmdHandler = new CmdHandler();
        cmdHandler.addComms(new Help());
    }
}

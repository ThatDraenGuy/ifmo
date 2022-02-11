package Collection;

import Collection.Classes.LabWork;

public class CollectionHandler {
    private java.util.Vector<LabWork> collection;
    private StorageHandler storageHandler;
    public  CollectionHandler(StorageHandler storageHandler) {
        this.collection = new java.util.Vector<>();
        this.storageHandler=storageHandler;
    }
    public void add(LabWork labWork) {
        collection.add(labWork);
    }
    public void load() {
        this.collection.addAll(this.storageHandler.load());
    }
}

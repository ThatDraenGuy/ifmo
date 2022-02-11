package Collection;

import Collection.Classes.LabWork;

import java.io.*;
import org.json.*;

public class StorageHandler {
    private File file;
    public StorageHandler(File file) {
        this.file=file;
    }
    public java.util.Vector<LabWork> load() {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            JSONTokener tokenizedFile = new JSONTokener(streamReader);
            JSONArray labWorksArray = new JSONArray(tokenizedFile);
            return JSONToCollection.parseLabWorksArray(labWorksArray);
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return null;
    }
}

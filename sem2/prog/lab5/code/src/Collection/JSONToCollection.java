package Collection;

import Collection.Classes.Coordinates;
import Collection.Classes.LabWork;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONToCollection {
    public static java.util.Vector<LabWork> parseLabWorksArray(JSONArray labWorksArray) {
        java.util.Vector<LabWork> labWorks = new java.util.Vector<>();
        for (int i=0; i<labWorksArray.length(); i++) {
            JSONObject labWork = labWorksArray.getJSONObject(i);
            labWorks.add(parseLabWork(labWork));
        }
        return labWorks;
    }
    public static LabWork parseLabWork(JSONObject labWork) {
        int id = labWork.getInt("id");
        String name = labWork.getString("name");
        Coordinates coordinates = parseCoordinates(labWork.getJSONObject("coordinates"));
        return null;
    }
    public static Coordinates parseCoordinates(JSONObject coordinates) {
        int x = coordinates.getInt("x");
        int y = coordinates.getInt("y");
        return new Coordinates(x,y);
    }
}

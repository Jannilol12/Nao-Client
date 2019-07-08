import components.json.JSONObject;
import components.json.abstractJSON;
import components.json.finder.JSONFinder;

public class Zwischenspeicher {
    boolean useArgs;
    String name;

    public Zwischenspeicher(abstractJSON json){
        name = JSONFinder.getString("name", json);
        useArgs = JSONFinder.getBoolean("useArgs", json);
    }

    @Override
    public String toString(){
        return name;
    }
}

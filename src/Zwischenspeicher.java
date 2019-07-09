import components.json.JSONArray;
import components.json.JSONObject;
import components.json.abstractJSON;
import components.json.finder.JSONFinder;

public class Zwischenspeicher {
    String name;
    JSONArray inputArgs;

    public Zwischenspeicher(abstractJSON json){
        name = JSONFinder.getString("name", json);
        
        if(json instanceof JSONObject) {
        	abstractJSON abstractJSON = ((JSONObject) json).get("inputs");
        	if(abstractJSON instanceof JSONArray)
        		inputArgs = (JSONArray) abstractJSON;
        }
        
    }
    
    public Zwischenspeicher(abstractJSON json, JSONArray inputArgs){
        name = JSONFinder.getString("name", json);
        this.inputArgs = inputArgs;
    }

    public String getName() {
		return name;
	}

	public JSONArray getInputArgs() {
		return inputArgs;
	}

	@Override
    public String toString(){
        return name;
    }
}

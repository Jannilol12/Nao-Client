package nao.controllers;
import components.json.JSONArray;
import components.json.JSONObject;
import components.json.abstractJSON;
import components.json.finder.JSONFinder;

/**
 * This is used to save all information of the program (from the list on the left side) into one class
 */
public class CachForPrograms {
    String name;
    JSONArray inputArgs;

    /**
     * Save all information
     * @param json get the information as a JSON
     */
    public CachForPrograms(abstractJSON json){
        //name of the program
        name = JSONFinder.getString("name", json);

        //save the inputs which are needed as an ArrayList (for example to say something, a String is needed)
        if(json instanceof JSONObject) {
        	abstractJSON abstractJSON = ((JSONObject) json).get("inputs");
        	if(abstractJSON instanceof JSONArray)
        		inputArgs = (JSONArray) abstractJSON;
        }
        
    }

    /**
     * save all information
     * @param json name of the program as a JSON
     * @param inputArgs inputs which are needed for the program (for example to say something, a String is needed)
     */
    public CachForPrograms(abstractJSON json, JSONArray inputArgs){
        name = JSONFinder.getString("name", json);
        this.inputArgs = inputArgs;
    }

    /**
     * Name of the program
     * @return name
     */
    public String getName() {
		return name;
	}

    /**
     * Inputs which are needed for the program (for example to say something, a String is needed)
     * @return inputs
     */
	public JSONArray getInputArgs() {
		return inputArgs;
	}

	@Override
    public String toString(){
        return name;
    }
}

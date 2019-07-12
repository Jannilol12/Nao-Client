package nao;
import components.json.abstractJSON;
import components.json.finder.JSONFinder;
import components.json.parser.JSONParser;
import nao.controllers.Zwischenspeicher;
import nao.controllers.controller_commands;
import nao.controllers.controller_main;

public class MainReceiver {
    public static void receiveText(String text){
        abstractJSON json = JSONParser.parse(text);

        String type = JSONFinder.getString("type", json);
        if(type == null) return;

        switch (type){
            case "ProgAdd":
                if(controller_main.cmain != null){
                    Zwischenspeicher zwischenspeicher = new Zwischenspeicher(json);
                    controller_main.cmain.addProg(zwischenspeicher);
                }
                break;
            case "battery":
                if(controller_commands.cc != null){
                    int batt = JSONFinder.getInt("battery", json);
                    controller_commands.cc.setBatteryText(batt);
                }
                break;
        }
    }
}

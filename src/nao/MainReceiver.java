package nao;
import components.json.abstractJSON;
import components.json.finder.JSONFinder;
import components.json.parser.JSONParser;
import nao.controllers.*;
import nao.controllers.RobotSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

public class MainReceiver {
    public static void receiveText(String text){
        abstractJSON json = JSONParser.parse(text);

        String type = JSONFinder.getString("type", json);
        if(type == null) return;

        switch (type){
            case "Names":
                List<String> names = (List<String>) JSONFinder.getList("Names",json);
                SpeechFaceBehavior.cE.setNames(names);
                break;
            case "ProgAdd":
                if(MainController.cmain != null){
                    Zwischenspeicher zwischenspeicher = new Zwischenspeicher(json);
                    MainController.cmain.addProg(zwischenspeicher);
                }
                break;
            case "battery":
                if(RobotSystem.cc != null){
                    int batt = JSONFinder.getInt("battery", json);
                    RobotSystem.cc.setBatteryText(batt);
                }
                break;
            case "SpeechRecognition":
                List<String> voc = (List<String>) JSONFinder.getList("Voc",json);
                SpeechFaceBehavior.cE.setVocabulary(voc);
                break;
            case "FaceDetection":
                List<String> faces = (List<String>) JSONFinder.getList("Faces",json);
                SpeechFaceBehavior.cE.setNames(faces);
                break;
            case "audioPlayer":
                String function = JSONFinder.getString("function", json);
                switch(function){
                    case "getVol":
                        double volume = JSONFinder.getDouble("getVol", json);
                        AudioPlayer.caP.setVolume(volume);
                        break;
                    case "getLength":
                        double length = JSONFinder.getDouble("Length", json);
                        AudioPlayer.caP.setPositionTime(length);
                        break;
                    case "getPosition":
                        double position = JSONFinder.getDouble("Position", json);
                        AudioPlayer.caP.setPosition(position);
                        break;
                    case "getFiles":
                        List<String> list = new LinkedList<>();
                        list = (List<String>) JSONFinder.getList("File",json);
                        AudioPlayer.caP.loadFiles(list);
                        Files.cF.loadFiles(list);
                        break;
                }
                break;
            case "Behavior":
                List<String> listBehaviors = new LinkedList<>();
                listBehaviors = (List<String>) JSONFinder.getList("Behaviors",json);
                SpeechFaceBehavior.cE.loadBehaviors(listBehaviors);
                break;
            case "temperature":
                java.lang.System.out.println("Receiving Temperatures!");

                String HeadYaw = JSONFinder.getString( "HeadYaw", json);
                String HeadPitch = JSONFinder.getString( "HeadPitch", json);
                String LElbowYaw = JSONFinder.getString( "LElbowYaw", json);
                String LElbowRoll = JSONFinder.getString( "LElbowRoll", json);
                String RElbowYaw = JSONFinder.getString( "RElbowYaw", json);
                String RElbowRoll = JSONFinder.getString( "RElbowRoll", json);
                String LHand = JSONFinder.getString( "LHand", json);
                String LWristYaw = JSONFinder.getString( "LWristYaw", json);
                String RHand = JSONFinder.getString( "RHand", json);
                String RWristYaw = JSONFinder.getString( "RWristYaw", json);
                String LShoulderPitch = JSONFinder.getString( "LShoulderPitch", json);
                String LShoulderRoll = JSONFinder.getString( "LShoulderRoll", json);
                String RShoulderPitch = JSONFinder.getString( "RShoulderPitch", json);
                String RShoulderRoll = JSONFinder.getString( "RShoulderRoll", json);
                String RHipRoll = JSONFinder.getString( "RHipRoll", json);
                String LHipRoll = JSONFinder.getString( "LHipRoll", json);
                String RHipYawPitch = JSONFinder.getString( "RHipYawPitch", json);
                String LHipYawPitch = JSONFinder.getString( "LHipYawPitch", json);
                String RHipPitch = JSONFinder.getString( "RHipPitch", json);
                String LHipPitch = JSONFinder.getString( "LHipPitch", json);
                String RKneePitch = JSONFinder.getString( "RKneePitch", json);
                String LKneePitch = JSONFinder.getString( "LKneePitch", json);
                String RAnklePitch = JSONFinder.getString( "RAnklePitch", json);
                String LAnklePitch = JSONFinder.getString( "LAnklePitch", json);
                String RAnkleRoll = JSONFinder.getString( "RAnkleRoll", json);
                String LAnkleRoll = JSONFinder.getString( "LAnkleRoll", json);
                String HeadCPU = JSONFinder.getString( "HeadCPU", json);
                String Battery = JSONFinder.getString( "Battery", json);

                RobotSystem.cc.setTemperatureText(Battery, LHand, HeadCPU, HeadYaw, LElbowYaw, LShoulderPitch, RHand, LHipPitch, HeadPitch, LElbowRoll, LShoulderRoll, LWristYaw, RWristYaw, LHipRoll, LKneePitch, RElbowYaw, RElbowRoll, RShoulderPitch, RShoulderRoll, RHipPitch, RHipRoll, LHipYawPitch, LAnklePitch, RHipYawPitch, LAnkleRoll, RAnklePitch, RAnkleRoll, RKneePitch);
                break;

            case "getAllFiles":
                List<String> list = new LinkedList<>();
                list = (List<String>) JSONFinder.getList("File",json);
                AudioPlayer.caP.loadFiles(list);
                Files.cF.loadFiles(list);
                break;

            case "downloadFile":
                String base64 = JSONFinder.getString("bytes",json);
                byte[] bytes = Base64.getDecoder().decode(base64);
                try{
                    File fileUpload = new File(Files.cF.getDirectory(), JSONFinder.getString("name", json));
                    FileOutputStream fileOutputStream = new FileOutputStream(fileUpload, true);
                    fileOutputStream.write(bytes);
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            default:
                java.lang.System.out.println("Nothing to do O.o");
                break;
        }
    }
}

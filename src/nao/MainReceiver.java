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

/**
 * Receives all messages from the robot
 */
public class MainReceiver {
    public static void receiveText(String text){
        abstractJSON json = JSONParser.parse(text);

        String type = JSONFinder.getString("type", json);

        //if message has no type -> do nothing
        if(type == null) return;

        switch (type){
            case "Console": //writes the sever console
                String consoleText = JSONFinder.getString("String",json);
                Console.c.setServerConsoleText(consoleText);
                break;
            case "ProgAdd": //add a program of the list on the left side
                    CachForPrograms cache = new CachForPrograms(json);
                    MainController.cmain.addProg(cache);
                break;
            case "battery": //get the battery load of the nao
                    int battery = JSONFinder.getInt("battery", json);
                    RobotSystem.cc.setBatteryText(battery);
                break;
            case "SpeechRecognition": //get the installed vocabulary
                List<String> voc = (List<String>) JSONFinder.getList("Voc",json);
                SpeechFaceBehavior.cE.setVocabulary(voc);
                break;
            case "FaceDetection": //get the names of the learned faces
                List<String> faces = (List<String>) JSONFinder.getList("Faces",json);
                SpeechFaceBehavior.cE.setNames(faces);
                break;
            case "audioPlayer": //everything for the audio player
                String function = JSONFinder.getString("function", json);
                switch(function){
                    case "getVol": //set the slider to the volume of the audio player
                        double volume = JSONFinder.getDouble("getVol", json);
                        AudioPlayer.caP.setVolume(volume);
                        break;
                    case "getLength": //get the length of the file, which is playing
                        double length = JSONFinder.getDouble("Length", json);
                        AudioPlayer.caP.setPositionTime(length);
                        break;
                    case "getPosition": //get the position of the file, which is playing
                        double position = JSONFinder.getDouble("Position", json);
                        AudioPlayer.caP.setPosition(position);
                        break;
                    case "getFiles": //set the installed audio files in the audio player
                        List<String> list = new LinkedList<>();
                        list = (List<String>) JSONFinder.getList("File",json);
                        AudioPlayer.caP.loadFiles(list);
                        break;
                }
                break;
            case "Behavior": //load the installed behaviors into the comboBox
                List<String> listBehaviors = new LinkedList<>();
                listBehaviors = (List<String>) JSONFinder.getList("Behaviors",json);
                SpeechFaceBehavior.cE.loadBehaviors(listBehaviors);
                break;
            case "temperature": //get the temperatures of all motors
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

            case "getAllFiles": //load the list in the "files" tab with all files on the robot
                List<String> list = new LinkedList<>();
                list = (List<String>) JSONFinder.getList("File",json);
                Files.cF.loadFiles(list);
                break;

            case "downloadFile": //add the downloaded file from the robot in the selected directory
                String base64 = JSONFinder.getString("bytes",json);
                /*
					how this works:
					The File is decoded in Base64 and is split into many Messages
					Every time a message is received the file will be written until it's finished
					So the file will be written step by step
				 */
                byte[] bytes = Base64.getDecoder().decode(base64);
                try{
                    String fileName =  JSONFinder.getString("name",json);
                    File direction = new File(Files.cF.getDirectory() + "/" +  fileName);
                    direction.getParentFile().mkdirs();
                    //append is true, so the new bytes will be attached at the end of the file
                    FileOutputStream fileOutputStream = new FileOutputStream(direction, true);
                    fileOutputStream.write(bytes);
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            default: //do nothing
                System.out.println("Nothing to do O.o");
                break;
        }
    }
}

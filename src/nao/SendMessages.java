package nao;

import components.json.JSONObject;
import nao.controllers.MainController;


/**
 * Sending messages to the server, those which are used from different methods and those which are used when initializing the system
 */
public class SendMessages {
    private static Thread batteryLoadThread;
    private static Thread audioPlayerPositionOfFileThread;
    private static Thread x;

    /**
     * getting the position of the audio file when played
     */
    public static synchronized void sendAudioPlayerPositionOfFile(){
        if(audioPlayerPositionOfFileThread != null){
            return;
        }
        //starting this as a Thread because, you want every second the position of the file
        audioPlayerPositionOfFileThread = new Thread(){
            @Override
            public void run() {
                //while not interrupting this and client is connected to the server
                while (!this.isInterrupted() && !sender.isClosed()) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.add("type", "audioPlayer");
                    jsonObject.add("function", "getPosition");
                    sender.sendMessage(jsonObject.toJSONString());
                    try {
                        Thread.sleep(1000); //1 second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //if outside the while -> Thread is interrupted
                audioPlayerPositionOfFileThread = null;
            }
        };
        audioPlayerPositionOfFileThread.start();
    }

    /**
     * interrupt the Thread from {@link #sendAudioPlayerPositionOfFile()}
     */
    public static synchronized void stopAudioPlayerPositionOfFile(){
        if(audioPlayerPositionOfFileThread == null) return;
        audioPlayerPositionOfFileThread.interrupt();
        audioPlayerPositionOfFileThread = null;
    }

    /**
     * getting the volume of the AudioPlayer, not the master volume, for that there is no Command in the API
     */
    public static synchronized void sendAudioPlayerVolume(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "getVolume");
        sender.sendMessage(jsonObject.toJSONString());
    }

    /**
     * getting the files for the AudioPlayer
     */
    public static synchronized void sendAudioFiles(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "getFiles");
        sender.sendMessage(jsonObject.toJSONString());
    }


    /**
     * getting the vocabulary.
     * For the list, if you want to delete one vocable
     */
    public static synchronized void sendVocabulary(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Events");
        jsonObject.add("function", "getVocabulary");
        sender.sendMessage(jsonObject.toJSONString());
    }

    /**
     * getting the list of learned faces.
     * For the list, if you want to delete one face.
     */
    public static synchronized void sendFaceNames(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Events");
        jsonObject.add("function", "getFaces");
        sender.sendMessage(jsonObject.toJSONString());
    }

    /**
     * getting all behaviors installed on the robot
     */
    public static synchronized void sendBehavior(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Behavior");
        jsonObject.add("function", "getBehaviors");
        sender.sendMessage(jsonObject.toJSONString());
    }

    /**
     * getting the battery load every 30 seconds
     */
    public static synchronized void sendBattery(){
        if(batteryLoadThread != null){
            return;
        }
        batteryLoadThread = new Thread(){
            @Override
            public void run() {
                //while not interrupting this and client is connected to the server
                while (!this.isInterrupted() && !sender.isClosed()) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.add("type", "battery");
                    sender.sendMessage(jsonObject.toJSONString());
                    try {
                        Thread.sleep(30000); //30 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //if outside the while -> Thread is interrupted
                batteryLoadThread = null;
            }
        };
        batteryLoadThread.start();
    }

    /**
     * interrupt the Thread from {@link #sendBattery()}
     */
    public static synchronized void stopBattery(){
        if(batteryLoadThread == null) return;

        batteryLoadThread.interrupt();
        batteryLoadThread = null;
    }

    public static synchronized void sendTemperature(){
        if(x != null){
            return;
        }
        x = new Thread(){
            @Override
            public void run() {
                //while not interrupting this and client is connected to the server
                while (!this.isInterrupted() && !sender.isClosed()) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.add("type", "temperature");
                    sender.sendMessage(jsonObject.toJSONString());
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //if outside the while -> Thread is interrupted
                x = null;
            }
        };
        x.start();
    }

    public static synchronized void stopTemperature(){
        if(x == null) return;

        x.interrupt();
        x = null;
    }

    public static synchronized void sendAllFiles(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Files");
        jsonObject.add("function", "getAllFiles");
        sender.sendMessage(jsonObject.toJSONString());
    }

    public static synchronized void sendListMethods(){
        if(MainController.cmain != null) {
            MainController.cmain.clearProgs();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "ListP");
        sender.sendMessage(jsonObject.toJSONString());
    }
}

package nao;

import components.json.JSONObject;

public class SendMessages {
    private static Thread t;
    private static Thread a;
    private static Thread x;

    public static synchronized void sendAudioPlayer(){
        if(a != null){
            return;
        }
        a = new Thread(){
            @Override
            public void run() {
                while (!this.isInterrupted() && !sender.isClosed()) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.add("type", "audioPlayer");
                    jsonObject.add("function", "getPosition");
                    sender.sendMessage(jsonObject.toJSONString());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
                a = null;
            }
        };
        a.start();
    }

    public static synchronized void stopAudioPlayer(){
        if(a == null) return;
        a.interrupt();
        a = null;
    }

    public static synchronized void sendVolume(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "getVolume");
        sender.sendMessage(jsonObject.toJSONString());
    }

    public static synchronized void sendFileRequest(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "getFiles");
        sender.sendMessage(jsonObject.toJSONString());
    }

    public static synchronized void sendVocabulary(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Events");
        jsonObject.add("function", "getVocabulary");
        sender.sendMessage(jsonObject.toJSONString());
    }

    public static synchronized void sendFaces(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Events");
        jsonObject.add("function", "getFaces");
        sender.sendMessage(jsonObject.toJSONString());
    }

    public static synchronized void sendBehavior(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "Behavior");
        jsonObject.add("function", "getBehaviors");
        sender.sendMessage(jsonObject.toJSONString());
    }

    public static synchronized void sendBattery(){
        if(t != null){
            return;
        }
        t = new Thread(){
            @Override
            public void run() {
                while (!this.isInterrupted() && !sender.isClosed()) {
                    sender.sendMessage("{\"type\":\"battery\"}");
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {}
                }
                t = null;
            }
        };
        t.start();
    }

    public static synchronized void stopBattery(){
        if(t == null) return;

        t.interrupt();
        t = null;
    }

    public static synchronized void sendTemperature(){
        if(x != null){
            return;
        }
        x = new Thread(){
            @Override
            public void run() {
                while (!this.isInterrupted() && !sender.isClosed()) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.add("type", "temperature");
                    sender.sendMessage(jsonObject.toJSONString());
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {}
                }
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
}

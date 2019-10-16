package nao;

import components.json.JSONObject;

public class SendMessages {
    private Thread t;
    private Thread a;
    private Thread x;

    public synchronized void sendAudioPlayer(){
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

    public synchronized void stopAudioPlayer(){
        if(a == null) return;
        a.interrupt();
        a = null;
    }

    public synchronized void sendVolume(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "getVolume");
        sender.sendMessage(jsonObject.toJSONString());
    }

    public synchronized void sendFileRequest(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "audioPlayer");
        jsonObject.add("function", "getFiles");
        sender.sendMessage(jsonObject.toJSONString());
    }

    public synchronized void sendVocabulary(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "SpeechRecognition");
        jsonObject.add("function", "getVocabulary");
        sender.sendMessage(jsonObject.toJSONString());
    }

    public synchronized void sendNames(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.add("type", "SpeechRecognition");
        jsonObject.add("function", "getName");
        sender.sendMessage(jsonObject.toJSONString());
    }

    public synchronized void sendBattery(){
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

    public synchronized void stopBattery(){
        if(t == null) return;

        t.interrupt();
        t = null;
    }

    public synchronized void sendTemperature(){
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

    public synchronized void stopTemperature(){
        if(x == null) return;

        x.interrupt();
        x = null;
    }
}

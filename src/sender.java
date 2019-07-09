import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class sender {
    public static Socket socket;
    public static DataOutputStream dout;
    public static DataInputStream dis;
    public static String ip;
    public static int port;
    private static Thread readingThread;

    public static void connected(String ip_,int Port_){
        ip = ip_;
        port = Port_;
        reconnect();
    }

    public static void reconnect(){
        destroy();
        try {
            socket = new Socket(ip , port);
            socket.setKeepAlive(false);
            dout = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            startReadingThread();

            if(controller_main.cmain != null)
                controller_main.cmain.clearProgs();

            sender.sendMessage("{\"type\":\"ListP\"}");
        } catch (IOException e) {
            e.printStackTrace();
            destroy();
        }
    }

    public static void sendMessage(String text){
        if(socket != null && socket.isClosed()){
            reconnect();
        }
        if(text != null && dout != null ) {
            try {
                dout.writeUTF(text);
                dout.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized static void startReadingThread(){
        if(readingThread != null)return;

        readingThread = new Thread(){
            @Override
            public void run(){
                while(!this.isInterrupted()){
                    try {
                        String str = dis.readUTF();
                        if(str.isEmpty())continue;
                        MainReceiver.receiveText(str);
                    } catch (IOException e) {
                        System.out.println("Stop reading...");
                        return;
                    }
                }
                System.out.println("Stop reading...");
            }
        };
        readingThread.start();
    }

    public static void destroy(){
        if(readingThread != null) {
            readingThread.interrupt();
            readingThread = null;
        }

        if(socket != null && socket.isClosed()){
            socket = null;
            dout = null;
            return;
        }
        if(dout != null) {
            try {
                dout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            socket = null;
            dout = null;
        }
    }

    public static boolean isClosed() {
    	if(socket == null) return true;
    	if(socket.isClosed()) return true;
    	
    	return false;
    }
}

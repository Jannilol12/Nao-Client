package nao;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import nao.controllers.controller_main;

public class sender {
    public static Socket socket;
    public static DataOutputStream dout;
    public static DataInputStream dis;
    public static boolean online;
    
    public static String ip;
    public static int port;
    private static Thread readingThread;

    public synchronized static void connected(String ip_,int Port_){
        ip = ip_;
        port = Port_;
        reconnect();
    }

    public synchronized static void reconnect(){
    	if(ip == null || ip.isEmpty() || port < 0) return;
    	
        destroy();
        try {
            socket = new Socket(ip , port);
            online = true;
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
        if(isClosed())
            reconnect();
        
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
        if(readingThread != null) return;

        readingThread = new Thread(){
            @Override
            public void run(){
                while(!this.isInterrupted() && online){
                    try {
                        String str = dis.readUTF();
                        if(str.isEmpty())continue;
                        MainReceiver.receiveText(str);
                    } catch (IOException e) {
                        System.out.println("Stop reading...");
                        destroy();
                        return;
                    }
                }
                
                System.out.println("Stop reading...");
                destroy();
            }
        };
        readingThread.start();
    }

    public synchronized static void destroy(){
    	if(!online) return;
    	online = false;
    	
        if(readingThread != null) {
            readingThread.interrupt();
            readingThread = null;
        }

        if(socket != null){
        	try {
				socket.close();
			} catch (IOException e) {}
        }
        
        socket = null;
        dout = null;
        dis = null;
    }

    public static boolean isClosed() {
    	if(socket == null) return true;
    	if(socket.isClosed()) return true;
    	
    	return false;
    }
}

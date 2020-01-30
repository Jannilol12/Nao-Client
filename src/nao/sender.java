package nao;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import nao.controllers.MainController;
import nao.debugger.Debugger;

/**
 * Sender is connecting the client to the server
 */
public class sender {
    public static Socket socket;
    public static DataOutputStream dout;
    public static DataInputStream dis;
    public static boolean online;
    
    public static String ip;
    public static int port;
    private static Thread readingThread;


    /**
     * Connect to the server, with the {@link #reconnect()} method
     * @param ip_ the ip from the window input, should be ip from the nao
     * @param Port_ the port from the window input, standard 7777
     */
    public synchronized static void connected(String ip_,int Port_){
        ip = ip_;
        port = Port_;
        reconnect();
    }

    /**
     * reconnect, in
     */
    public synchronized static void reconnect(){
        //if no ip, or port is given, do nothing
    	if(ip == null || ip.isEmpty() || port < 0){
    	    return;
        }
    	
        destroy(); //destroy the old session
        try {
            socket = new Socket(ip , port); //make a new ServerSocket
            online = true; //this variable is used later, when looking for messages from the input
            socket.setKeepAlive(false); //socket is not making new connections (if it disconnects)
            
            dout = new DataOutputStream(socket.getOutputStream()); //DataOutputStream = Sending messages
            dis = new DataInputStream(socket.getInputStream()); //DataInputStream = Receiving messages
            startReadingThread();
        } catch (IOException e) {
            e.printStackTrace();
            destroy();
        }
    }

    /**
     * Sending a message to the server
     * @param text message as JSON
     */
    public static void sendMessage(String text){
        if(isClosed()) {
            reconnect();
        }

        if(text != null && dout != null ) { //when a message and DataOutputStream is given
        	if(Debugger.isEnable())
        		System.out.println("Sending: " + text);
        	
            try {
                dout.writeUTF(text); //send message
                dout.flush(); //and clear everything which could be between the Server and the Client
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(Debugger.isEnable())
        	System.out.println("Send (Failed): " + text);
    }

    /**
     * Receiving messages
     */
    private synchronized static void startReadingThread(){
        if(readingThread != null) return;

        readingThread = new Thread(){
            @Override
            public void run(){
                while(!this.isInterrupted() && online){ //if connected
                    try {
                        String str = dis.readUTF(); //read the DataInputStream
                        if(str.isEmpty())continue;
                        MainReceiver.receiveText(str); //give the received message to the MainReceiver
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

    /**
     * disconnect and setting everything on null
     */
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
			} catch (IOException e) {
        	    e.printStackTrace();
            }
        }
        
        socket = null;
        dout = null;
        dis = null;
    }

    /**
     * If connection is closed
     * @return true when connection is closed
     */
    public static boolean isClosed() {
    	if(socket == null) return true;
    	if(socket.isClosed()) return true;

    	return false;
    }
}

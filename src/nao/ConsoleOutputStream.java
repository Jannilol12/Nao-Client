package nao;

import components.json.JSONObject;
import nao.controllers.Console;

import java.io.*;

/**
 * This Method is for the Console on the client.
 */
public class ConsoleOutputStream extends OutputStream {
    private StringBuilder builder;
    private PrintStream stream;

    /**
     * Setting everything up
     */
    public ConsoleOutputStream(){
        stream = System.out; //getting the console from the Nao.
        System.setOut(new PrintStream(this)); //instead of writing in the console, write here
        builder = new StringBuilder(); //instead of sending every single char to the client, make a String
    }

    /**
     * Writing the bytes from the console into a String and write it into the Console of the Client
     * @param b char from the console as an int
     * @throws IOException Exception
     */
    @Override
    public void write(int b) throws IOException {
        char c = (char) b;
        if(c != '\n'){ //if not a "new Line"
            stream.write(c); //write into the Console from the nao, because otherwise there isn't anything because of the System.setOut in line 23
            builder.append(c); //add the char to the StringBuilder to receive a String in the End
        }else{
            stream.write(c);
            String finalString = builder.toString(); //make a new String out of the chars

            Console.c.setClientConsoleTextArea(finalString); //write it into the Console of the Client

            builder = new StringBuilder(); //make a new StringBuilder, so emptying the old
        }
    }
}

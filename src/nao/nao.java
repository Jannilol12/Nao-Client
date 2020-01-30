package nao;

import nao.debugger.Debugger;


/**
 * Program for Nao-Robot written by Jannik Lieb
 *
 * @author Jannik Lieb
 *
 * @since 13.01.2020
 *
 * @version 1.0
 *
 * Sorry for my bad english.
 * Why didn't I wrote it in German?
 * Because I'm serious! No...I am dumb
 */
public class nao {
    public static void main(String[] args){
        //Debugger is writing a log file
    	Debugger.setEnable(true);

    	//start the window
        MainFrame.launching();
    }
}

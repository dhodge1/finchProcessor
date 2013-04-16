/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Week9;

/**
 *
 * @author David Hodge
 * A program used to process commands from a text file.
 * 
 */

import java.io.*;
import java.util.Scanner;

public class FinchProcessor extends Finch {  
    
    private File myFile = new File("C:/Users/David/Documents/commands.txt");
    Scanner scan;
    private String[] commands;
    private FinchCommand[] things;
    private int n;
    //private int numLines;
    
    /*
     * The constructor attempts to use the file location provided by myFile,
     * else it outputs the exception. Regardless it calls getCommandStringArray()
     * to read the file and put each line as a separate element in the commands
     * String[]. Next it calls getFinchCommandArray() to take each element 
     * from the previous String[] commands and split it up into a temporary 
     * String[] using commas as delimiters. It the evaluates the first element
     * (command code) of the temporary String[] to determine which elements
     * need to be stored in the FinchCommand[] for what member variables in each
     * instance of the class stored in the object array. That's a mouthful.
     */
    
    public FinchProcessor() {
        super();
        try {
            scan = new Scanner(myFile);
        }
        catch(FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
            System.exit(0);
        }
        n = 0;
        //numLines = 0;
        //setLineCount();
        commands = getCommandStringArray();
        things = getFinchCommandArray();
    }

    /*
     * Each time doNextCommand() is called it evaluates the command code for the
     * current element of the object array to determine what the finch needs
     * to do. Afterwards, it increments the index by 1 and returns true unless
     * the command code is not 0-3 (i.e. blank, invalid, etc.) in which case it
     * returns false.
     */
    
    public boolean doNextCommand() {
        //for(int n=0; n<things.length; n++) {
        //int n = 0;
        switch(things[n].commandCode) {
            case 0:
                //setLED(things[n].red, things[n].green, things[n].blue, things[n].durLED);   
                setLED(things[n].red, things[n].green, things[n].blue);
                sleep(things[n].durLED);
                if(n < (things.length-1)) {
                    n++;
                    return(true);
                }
                return(false);
            case 1:
                buzz(things[n].frequency, things[n].durFreq);
                sleep(250);
                if(n < (things.length-1)) {
                    n++;
                    return(true);
                }
                return(false);
            case 2:
                setWheelVelocities(things[n].leftWheel, things[n].rightWheel, things[n].durWheel);
                if(n < (things.length-1)) {
                    n++;
                    return(true);
                }
                return(false);
            case 3:
                //sleep(things[n].durSleep);
                playClip(things[n].pathToSound);
                sleep(things[n].durSleep);
                if(n < (things.length-1)) {
                    n++;
                    return(true);
                }
                return(false);
            default:
                return(false);   
            }
        //}
    }

/*Tried to use this to set up a variable allocation amount for the String[]
 * within the getCommandStringArray() method below. The idea was to cycle
 * through the file and add to a count. However, this resulted in a null pointer
 * exception. 
 */    
    
//    private void setLineCount() {
//        while(scan.hasNext()) {
//            String derp = scan.nextLine();
//            //System.out.println(line);
//            numLines++;
//            //System.out.println(numLines);
//        }
//    }
    
    private String[] getCommandStringArray() {
        //String[] command = new String[numLines];
        String[] command = new String[43];
        for(int i=0; scan.hasNext(); i++){
            command[i] = scan.nextLine();
        }
        return(command);
    }
    
    private FinchCommand[] getFinchCommandArray() {
        FinchCommand[] thing = new FinchCommand[commands.length];
        //for(int i=0; i<thing.length; i++) {
            for(int i=0; i<thing.length; i++ ) {
                String[] temp = commands[i].split(",");
                if(temp[0].equals("0")) {
                    int c0 = Integer.parseInt(temp[0].trim());
                    int r0 = Integer.parseInt(temp[1].trim());
                    int g0 = Integer.parseInt(temp[2].trim());
                    int b0 = Integer.parseInt(temp[3].trim());
                    int d0 = Integer.parseInt(temp[4].trim());
                    //thing[i] = new FinchCommand(c0, r0, g0, b0);
                    thing[i] = new FinchCommand(c0, r0, g0, b0, d0);
                }
                else if(temp[0].equals("1")) {
                    int c1 = Integer.parseInt(temp[0].trim());
                    int f1 = Integer.parseInt(temp[1].trim());
                    int d1 = Integer.parseInt(temp[2].trim());
                    thing[i] = new FinchCommand(c1, f1, d1);
                }
                else if(temp[0].equals("2")) {
                    int c2 = Integer.parseInt(temp[0].trim());
                    int lW2 = Integer.parseInt(temp[1].trim());
                    int rW2 = Integer.parseInt(temp[2].trim());
                    int d2 = Integer.parseInt(temp[3].trim());
                    thing[i] = new FinchCommand(c2, lW2, rW2, d2);
                }
                else if(temp[0].equals("3")) {
                    int c3 = Integer.parseInt(temp[0].trim());
                    String p3 = temp[1].trim();
                    int s3 = Integer.parseInt(temp[2].trim());
                    thing[i] = new FinchCommand(c3, p3, s3);
                }
            }
        //}
        return(thing);
    }
    
    class FinchCommand {
        
        private int    commandCode, red, green, blue, durLED, frequency, durFreq, leftWheel, rightWheel,
                       durWheel, durSleep;
        
        private String pathToSound;
        
        public FinchCommand(int comCode0, int red0, int green0, int blue0, int dur0) {
            commandCode = comCode0;
            red = red0;
            green = green0;
            blue = blue0;
            durLED = dur0;
        }
        
        public FinchCommand(int comCode1, int freq1, int dur1) {
            commandCode = comCode1;
            frequency = freq1;
            durFreq = dur1;
        }
        
        public FinchCommand(int comCode2, int l2, int r2, int dur2) {
            commandCode = comCode2;
            leftWheel = l2;
            rightWheel = r2;
            durWheel = dur2;
        }
        
        public FinchCommand(int comCode3, String path3, int dur3) {
            commandCode = comCode3;
            pathToSound = path3;
            durSleep = dur3;
        }
  
    } 
  
}

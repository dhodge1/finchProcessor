/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Week9;

/**
 *
 * @author David
 */

public class finchProcessorDriver {
    
    public static void main(final String[] args) {
        FinchProcessor f = new FinchProcessor();
        while(f.doNextCommand());
    }
    
}

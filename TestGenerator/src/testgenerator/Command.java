/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testgenerator;

/**
 *
 * @author Ariel
 */
public class Command extends Statement{
    String command;
    
    public Command(String com)
    {
        command = com;
    }

    @Override
    public void analyzeCode() {
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testgenerator;

/**
 *
 * @author Ariel
 */
public class Atrybut {
    String name;
    String type;
    
    //###############################KONSTRUKTORY###############################
    public Atrybut(String nazwa, String typ)
    {
        name = nazwa;
        type = typ;
    }
    
    //###############################METODY BEAN PROGRAMMING###############################
    public String getName()
    {
        return name;
    }
    
    public String getType()
    {
        return type;
    }
}

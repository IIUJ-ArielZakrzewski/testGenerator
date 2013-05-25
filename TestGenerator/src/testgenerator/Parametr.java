/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testgenerator;

/**
 *
 * @author Ariel
 */
public class Parametr {
    String name;
    String type;
    
    //###############################KONSTRUKTORY###############################
    public Parametr(String nazwa, String typ)
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

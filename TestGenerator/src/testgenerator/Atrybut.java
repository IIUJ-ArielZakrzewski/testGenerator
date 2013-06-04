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
    String value;
    boolean requiresMock;
    
    //###############################KONSTRUKTORY###############################
    public Atrybut(String nazwa, String typ)
    {
        name = nazwa;
        type = typ;
        value = "";
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
    
    public void setValue(String val)
    {
        value = val;
    }
    
    public String getValue()
    {
        return value;
    }
    
    public void setRequiresMock(boolean flaga)
    {
        requiresMock = flaga;
    }
    
    public boolean getRequiresMock()
    {
        return requiresMock;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testgenerator;

/**
 *
 * @author Ariel
 */
public class SourceFile {
    public String name;
    public String path;
    public String source;
    public String packageName;
    
    public SourceFile(String nazwa, String sciezka, String zrodlo)
    {
        name = nazwa;
        path = sciezka;
        source = zrodlo;
        packageName = "";
    }
    
    public SourceFile(String nazwa, String sciezka, String zrodlo, String projekt)
    {
        name = nazwa;
        path = sciezka;
        source = zrodlo;
        packageName = projekt;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testgenerator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class SourceFile {
    public String name;
    public String path;
    public String source;
    public String packageName;
    public List<Klasa> classes;
    public List<KlasaAbstrakcyjna> abstractClasses;
    public List<Interfejs> interfaces;
    public TestFile refTestFile;
    
    public SourceFile(String nazwa, String sciezka, String zrodlo)
    {
        name = nazwa;
        path = sciezka;
        source = zrodlo;
        packageName = "";
        classes = new ArrayList<>();
        abstractClasses = new ArrayList<>();
        interfaces = new ArrayList<>();
    }
    
    public SourceFile(String nazwa, String sciezka, String zrodlo, String projekt)
    {
        name = nazwa;
        path = sciezka;
        source = zrodlo;
        packageName = projekt;
        classes = new ArrayList<>();
        abstractClasses = new ArrayList<>();
        interfaces = new ArrayList<>();
    }
    
    public void addClass(Klasa k)
    {
        classes.add(k);
    }
    
    public void addAbstractClass(KlasaAbstrakcyjna k)
    {
        abstractClasses.add(k);
    }
    
    public void addInterface(Interfejs i)
    {
        interfaces.add(i);
    }
    
    public void setRefTestFile(TestFile f)
    {
        refTestFile = f;
    }
}

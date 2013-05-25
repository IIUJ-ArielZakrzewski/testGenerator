/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testgenerator;

import java.util.List;

/**
 *
 * @author Ariel
 */
public class TestFile {
    String source;
    public String refFile;
    public String generatedCode;
    public String packageName;
    
    public TestFile(String zrodlo, String sourceFileName)
    {
        source = zrodlo;
        refFile = sourceFileName;
        generatedCode = "";
        packageName = "";
    }
    
    public TestFile(String zrodlo, String sourceFileName, String paczka)
    {
        source = zrodlo;
        refFile = sourceFileName;
        generatedCode = "";
        packageName = paczka;
    }
    
    public void generateInputTests(List<SourceFile> files)
    {
        System.out.println("śmigam testy, mam tyyyyyyyle: " + files.size() + "soursow");
    }
}

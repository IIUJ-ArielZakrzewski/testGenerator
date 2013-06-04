/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testgenerator;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Ariel
 */
public class TestFile {
    String source;
    public String refFile;
    public String generatedCode;
    public String packageName;
    public List<String> imports;
    public Klasa klasa;
    public KlasaAbstrakcyjna klasaAbstrakcyjna;
    public Interfejs interfejs;
    public boolean isClass = false;
    public boolean isInterface = false;
    public boolean isAbstractClass = false;
    public List<SourceFile> sources;
    
    public TestFile(String zrodlo, String sourceFileName)
    {
        source = zrodlo;
        refFile = sourceFileName;
        generatedCode = "";
        packageName = "";
        imports = new LinkedList<>();
    }
    
    public TestFile(String zrodlo, String sourceFileName, String paczka)
    {
        source = zrodlo;
        refFile = sourceFileName;
        generatedCode = "";
        packageName = paczka;
        imports = new LinkedList<>();
    }
    
    public void generateInputTests(List<SourceFile> files) throws Exception
    {
        sources = files;
        for(SourceFile f : files)
        {
            try
            {
                analyzeCode(f);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        
       for(SourceFile f : files)
       {
           if(!f.classes.isEmpty())
           {
               Klasa k = f.classes.get(0);
               if(k.implementedInterface != null && !k.implementedInterface.equals(""))
               {
                   for(SourceFile fs : files)
                   {
                       if(!fs.interfaces.isEmpty() && fs.interfaces.get(0).getName().equals(k.implementedInterface))
                       {
                           fs.interfaces.get(0).addImlementation(k);
                       }
                   }
               }
               if(k.extendedClass != null && !k.extendedClass.equals(""))
               {
                   for(SourceFile fs : files)
                   {
                       if(!fs.abstractClasses.isEmpty() && fs.abstractClasses.get(0).getName().equals(k.extendedAbstractClass))
                       {
                           fs.abstractClasses.get(0).addImlementation(k);
                           k.extendedAbstractClass = k.extendedClass;
                           k.extendedClass = null;
                       }
                   }
               }
           }
       }
        
       for(SourceFile fs : files)
       {
           fs.refTestFile.generateTestCode();
       }
    }
    
    public void analyzeCode(SourceFile f) throws Exception
    {
        Scanner in = new Scanner(f.source);
        String line;
        f.refTestFile.isClass = false;
        f.refTestFile.isAbstractClass = false;
        f.refTestFile.isInterface = false;
        while(in.hasNextLine())
        {
            
            line = in.nextLine();
            line = line.replace("static ", "");
            if(line.matches("^package\\s.+;$"))
            {
                line = line.replace("package ", "");
                line = line.replace(";", "");
                f.refTestFile.packageName = line;
            } else if(line.matches("^import\\s.+;$"))
            {
                line = line.replace("import ", "");
                line = line.replace(";", "");
                if(!f.refTestFile.imports.contains(line))
                    f.refTestFile.imports.add(line);
            } else if(line.matches("^.+\\sabstract\\sclass\\s.+$")) {
                //System.out.println("trelelele");
                line = line.replaceFirst(".+\\sabstract\\sclass\\s", "");
                String className = line.substring(0, line.indexOf(" "));
                String extendedClass = "";
                String implementedInterface = "";
                String body = "";
                Stack<String> stos = new Stack<>();
                if(line.contains("extends"))
                {
                    line = line.replaceFirst(".+\\sextends\\s", "");
                    if(line.contains(" "))
                    {
                        extendedClass = line.substring(0, line.indexOf(" "));
                    } else if(line.contains("{")){
                        extendedClass = line.substring(0, line.indexOf("{"));
                    } else {
                        extendedClass = line.trim();
                    }
                }
                if(line.contains("implements"))
                {
                    line = line.replaceFirst(".+\\simplements\\s", "");
                    if(line.contains(" "))
                    {
                        implementedInterface = line.substring(0, line.indexOf(" "));
                    } else if(line.contains("{")){
                        implementedInterface = line.substring(0, line.indexOf("{"));
                    } else {
                        implementedInterface = line.trim();
                    }
                    
                }
                if(line.contains("{"))
                {
                    stos.push("{");
                } else {
                    line = in.nextLine();
                    line = line.replace("static ", "");
                    while(!line.contains("{"))
                    {
                        line = in.nextLine();
                        line = line.replace("static ", "");
                    }
                    stos.push("{");
                }
                body += "{\n";
                while(!stos.isEmpty())
                {
                    line = in.nextLine();
                    line = line.replace("static ", "");
                    body += line + "\n";
                    if(line.contains("{"))
                        stos.push("{");
                    if(line.contains("}"))
                        stos.pop();
                }
                f.refTestFile.klasaAbstrakcyjna = new KlasaAbstrakcyjna(className, this);
                f.refTestFile.isAbstractClass = true;
                f.refTestFile.klasaAbstrakcyjna.setClassBody(body);
                f.refTestFile.klasaAbstrakcyjna.setPackageName(packageName);
                if(!implementedInterface.equals(""))
                {
                    f.refTestFile.klasaAbstrakcyjna.setImplementedInterface(implementedInterface);
                }
                if(!extendedClass.equals(""))
                {
                    f.refTestFile.klasaAbstrakcyjna.setExtendedClass(extendedClass);
                }
            } else if(line.matches("^.+\\sclass\\s.+$")) {
                line = line.replaceFirst(".+\\sclass\\s", "");
                String className = line.substring(0, line.indexOf(" "));
                String extendedClass = "";
                String implementedInterface = "";
                String body = "";
                Stack<String> stos = new Stack<>();
                if(line.contains("extends"))
                {
                    line = line.replaceFirst(".+\\sextends\\s", "");
                    if(line.contains(" "))
                    {
                        extendedClass = line.substring(0, line.indexOf(" "));
                    } else if(line.contains("{")){
                        extendedClass = line.substring(0, line.indexOf("{"));
                    } else {
                        extendedClass = line.trim();
                    }
                }
                if(line.contains("implements"))
                {
                    line = line.replaceFirst(".+\\simplements\\s", "");
                    if(line.contains(" "))
                    {
                        implementedInterface = line.substring(0, line.indexOf(" "));
                    } else if(line.contains("{")){
                        implementedInterface = line.substring(0, line.indexOf("{"));
                    } else {
                        implementedInterface = line.trim();
                    }
                    
                }
                if(line.contains("{"))
                {
                    stos.push("{");
                    
                } else {
                    line = in.nextLine();
                    line = line.replace("static ", "");
                    while(!line.contains("{"))
                    {
                        line = in.nextLine();
                        line = line.replace("static ", "");
                    }
                    stos.push("{");
                }
                body += "{\n";
                while(!stos.isEmpty())
                {
                    line = in.nextLine();
                    line = line.replace("static ", "");
                    body += line + "\n";
                    if(line.contains("{"))
                        stos.push("{");
                    if(line.contains("}"))
                        stos.pop();
                }
                f.refTestFile.klasa  = new Klasa(className, this);
                f.refTestFile.isClass = true;
                f.refTestFile.klasa.setClassBody(body);
                f.refTestFile.klasa.setPackageName(f.refTestFile.packageName);
                if(!implementedInterface.equals(""))
                {
                    f.refTestFile.klasa.setImplementedInterface(implementedInterface);
                }
                if(!extendedClass.equals(""))
                {
                    f.refTestFile.klasa.setExtendedClass(extendedClass);
                }
            } else if(line.matches("^.+\\sinterface\\s.+$")) {
                line = line.replaceFirst(".+\\sinterface\\s", "");
                boolean containsClamre = line.contains("{");
                String interfaceName = line.replace("{", "").trim();
                String body = "";
                Stack<String> stos = new Stack<>();
                
                
                if(containsClamre)
                {
                    stos.push("{");
                    
                } else {
                    line = in.nextLine();
                    line = line.replace("static ", "");
                    while(!line.contains("{"))
                    {
                        line = in.nextLine();
                        line = line.replace("static ", "");
                    }
                    stos.push("{");
                }
                body += "{\n";
                while(!stos.isEmpty())
                {
                    line = in.nextLine();
                    line = line.replace("static ", "");
                    body += line + "\n";
                    if(line.contains("{"))
                        stos.push("{");
                    if(line.contains("}"))
                        stos.pop();
                }
                f.refTestFile.interfejs  = new Interfejs(interfaceName, this);
                f.refTestFile.isInterface = true;
                f.refTestFile.interfejs.setInterfaceBody(body);
                f.refTestFile.interfejs.setPackageName(f.refTestFile.packageName);
            } else {
            }
        }
        if(f.refTestFile.isClass)
        {
            f.refTestFile.klasa.analyzeCode();
            f.addClass(f.refTestFile.klasa);
        } else if(f.refTestFile.isAbstractClass)
        {
            f.refTestFile.klasaAbstrakcyjna.analyzeCode();
            f.addAbstractClass(f.refTestFile.klasaAbstrakcyjna);
        } else if(f.refTestFile.isInterface)
        {
            f.refTestFile.interfejs.analyzeCode();
            f.addInterface(f.refTestFile.interfejs);
        } else {
            System.out.println(f.refTestFile.refFile);
            System.out.println("Błąd danych w klasie test file");
        }
    }
    
    public void generateTestCode() throws Exception
    {
        if(isInterface)
        {
            generatedCode = "";
            return;
        }
        generatedCode += "package <nazwa package'a>;";
        generatedCode += "\n\n";
        for(String s : imports)
        {
            generatedCode += "\n" + s + ";";
        }
        generatedCode += "\nimport org.junit.*;";
        generatedCode += "\nimport static org.junit.Assert.*;";
        if(isClass)
        {
            klasa.checkAttributesAboutMocks();
            if(klasa.requiresMock())
            {
                generatedCode += "\nimport static org.easymock.EasyMock.*;";
            }
            klasa.generateMethodCommonTests();
            klasa.generateTestCode();
            generatedCode += klasa.getTestCode();
        } else if(isAbstractClass)
        {
            klasaAbstrakcyjna.generateMethodCommonTests();
            klasaAbstrakcyjna.generateTestCode();
            generatedCode += klasaAbstrakcyjna.getTestCode();
        }
    }
}

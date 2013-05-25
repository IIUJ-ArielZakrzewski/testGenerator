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
public class Klasa {
    
    //###############################ATRYBUTY KLASY DO ANALIZY###############################
    String name;
    List<Atrybut> attributes;
    List<Metoda> methods;
    Interfejs implementedInterface;
    Klasa extendedClass;
    KlasaAbstrakcyjna extendedAbstractClass;
    String packageName;
    String classBody;
    
    //###############################WYNIKOWY KOD###############################
    List<UnitaryTestMethod> tests;
    List<String> imports;
    String testCode;
    
    //###############################KONSTRUKTORY###############################
    public Klasa(String nazwa)
    {
        name = nazwa;
        attributes = new ArrayList<>();
        methods = new ArrayList<>();
        implementedInterface = null;
        extendedClass = null;
        extendedAbstractClass = null;
        packageName = "";
        classBody = "";
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
    }
    
    public Klasa(String nazwa, List atrybuty)
    {
        name = nazwa;
        attributes = atrybuty;
        methods = new ArrayList<>();
        implementedInterface = null;
        extendedClass = null;
        extendedAbstractClass = null;
        packageName = "";
        classBody = "";
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
    }
    
    public Klasa(String nazwa, List atrybuty, List metody)
    {
        name = nazwa;
        attributes = atrybuty;
        methods = metody;
        implementedInterface = null;
        extendedClass = null;
        extendedAbstractClass = null;
        packageName = "";
        classBody = "";
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
    }
    
    //###############################METODY BEAN PROGRAMMING###############################
    public String getName()
    {
        return name;
    }
    
    public void setAttributes(List newAttributes)
    {
        attributes = newAttributes;
    }
    
    public List getAttributes()
    {
        return attributes;
    }
    
    public void addAttribute(Atrybut nowy) throws Exception
    {
        for(Atrybut a : attributes)
        {
            if(a.getName().equals(nowy.getName()))
            {
                throw new Exception("Atrybut o podanej nazwie już istnieje!");
            }
        }
        attributes.add(nowy);
    }
    
    public void removeAttribute(String nazwa) throws Exception
    {
        for(Atrybut a : attributes)
        {
            if(a.getName().equals(nazwa))
            {
                attributes.remove(a);
                return;
            }
        }
        throw new Exception("Atrybut o podanej nazwie nie istnieje!");
    }
    
    public Atrybut getAttribute(String nazwa) throws Exception
    {
        for(Atrybut a : attributes)
        {
            if(a.getName().equals(nazwa))
            {
                return a;
            }
        }
        throw new Exception("Atrybut o podanej nazwie nie istnieje!");
    }
    
    public void clearAttributes()
    {
        attributes.clear();
    }
    
    public boolean hasAttribute(String nazwa)
    {
        for(Atrybut a : attributes)
        {
            if(a.getName().equals(nazwa))
                return true;
        }
        return false;
    }
    
    public boolean hasAnyAttribute()
    {
        if(attributes.size()>0)
            return true;
        return false;
    }
    
    public void setMethods(List newMethods)
    {
        methods = newMethods;
    }
    
    public List getMethods()
    {
        return methods;
    }
    
    public void addMethod(Metoda nowy) throws Exception
    {
        for(Metoda a : methods)
        {
            if(a.getName().equals(nowy.getName()) && a.parametersCount() == nowy.parametersCount())
            {
                throw new Exception("Metoda o podanej nazwie już istnieje!");
            }
        }
        methods.add(nowy);
    }
    
    public void removeMethod(String nazwa, int iloscParametrow) throws Exception
    {
        for(Metoda a : methods)
        {
            if(a.getName().equals(nazwa) && a.parametersCount() == iloscParametrow)
            {
                methods.remove(a);
                return;
            }
        }
        throw new Exception("Metoda o podanej nazwie nie istnieje!");
    }
    
    public Metoda getMethod(String nazwa) throws Exception
    {
        for(Metoda a : methods)
        {
            if(a.getName().equals(nazwa))
            {
                return a;
            }
        }
        throw new Exception("Metoda o podanej nazwie nie istnieje!");
    }
    
    public Metoda getMethod(String nazwa, int iloscParametrow) throws Exception
    {
        for(Metoda a : methods)
        {
            if(a.getName().equals(nazwa) && a.parametersCount() == iloscParametrow)
            {
                return a;
            }
        }
        throw new Exception("Metoda o podanej nazwie nie istnieje!");
    }
    
    public void clearMethods()
    {
        methods.clear();
    }
    
    public boolean hasMethod(String nazwa)
    {
        for(Metoda a : methods)
        {
            if(a.getName().equals(nazwa))
                return true;
        }
        return false;
    }
    
    public boolean hasAnyMethod()
    {
        if(methods.size()>0)
            return true;
        return false;
    }
    
    public Interfejs getImplementedInterface()
    {
        return implementedInterface;
    }
    
    public void setImplementedInterface(Interfejs newInterface)
    {
        implementedInterface = newInterface;
    }
    
    public Klasa getExtendedClass()
    {
        return extendedClass;
    }
    
    public void setExtendedClass(Klasa newExtendedClass)
    {
        extendedClass = newExtendedClass;
    }
    
    public KlasaAbstrakcyjna getExtendedAbstractClass()
    {
        return extendedAbstractClass;
    }
    
    public void setExtendedAbstractClass(KlasaAbstrakcyjna newExtendedAbstractClass)
    {
        extendedAbstractClass = newExtendedAbstractClass;
    }
    
    public String getPackageName()
    {
        return packageName;
    }
    
    public void setPackageName(String newPackageName)
    {
        packageName = newPackageName;
    }
    
    public String getClassBody()
    {
        return classBody;
    }
    
    public void setClassBody(String newClassBody)
    {
        classBody = newClassBody;
    }
            
    public List getTests()
    {
        return tests;
    }
    
    public void addTest(UnitaryTestMethod nowy) throws Exception
    {
        for(UnitaryTestMethod a : tests)
        {
            if(a.getName().equals(nowy.getName()))
            {
                throw new Exception("Test o podanej nazwie już istnieje!");
            }
        }
        tests.add(nowy);
    }
    
    public void removeTest(String nazwa) throws Exception
    {
        for(UnitaryTestMethod a : tests)
        {
            if(a.getName().equals(nazwa))
            {
                tests.remove(a);
                return;
            }
        }
        throw new Exception("Test o podanej nazwie nie istnieje!");
    }
    
    public UnitaryTestMethod getTest(String nazwa) throws Exception
    {
        for(UnitaryTestMethod a : tests)
        {
            if(a.getName().equals(nazwa))
            {
                return a;
            }
        }
        throw new Exception("Test o podanej nazwie nie istnieje!");
    }
    
    public void clearTests()
    {
        tests.clear();
    }
    
    public boolean hasTest(String nazwa)
    {
        for(UnitaryTestMethod a : tests)
        {
            if(a.getName().equals(nazwa))
                return true;
        }
        return false;
    }
    
    public boolean hasAnyTest()
    {
        if(tests.size()>0)
            return true;
        return false;
    }
    
    public List getImports()
    {
        return imports;
    }
    
    public void addImport(String nowy) throws Exception
    {
        for(String a : imports)
        {
            if(a.equals(nowy))
            {
                throw new Exception("Import o podanej nazwie już istnieje!");
            }
        }
        imports.add(nowy);
    }
    
    public void removeImport(String nazwa) throws Exception
    {
        for(String a : imports)
        {
            if(a.equals(nazwa))
            {
                imports.remove(a);
                return;
            }
        }
        throw new Exception("Import o podanej nazwie nie istnieje!");
    }
    
    public void clearImports()
    {
        imports.clear();
    }
    
    public boolean hasImport(String nazwa)
    {
        for(String a : imports)
        {
            if(a.equals(nazwa))
                return true;
        }
        return false;
    }
    
    public boolean hasAnyImport()
    {
        if(imports.size()>0)
            return true;
        return false;
    }
    
    public String getTestCode()
    {
        return testCode;
    }
    
    //###############################METODY ANALIZY KODU###############################
    public void analyzeCode()
    {
        
    }
    
    //###############################METODY GENERACJI TESTÓW###############################
    
    public void generateMethodCommonTests()
    {
        
    }
    
    public void generateTestCode()
    {
        
    }
}

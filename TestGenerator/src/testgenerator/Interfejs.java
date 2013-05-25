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
public class Interfejs {
    
    //###############################ATRYBUTY KLASY DO ANALIZY###############################
    String name;
    List<Metoda> methods;
    List<Klasa> implementations;
    String interfaceBody;
    
    //###############################WYNIKOWY KOD###############################
    List<UnitaryTestMethod> tests;
    List<String> imports;
    String testCode;
    
    //###############################KONSTRUKTORY###############################
    public Interfejs(String nazwa)
    {
        name = nazwa;
        methods = new ArrayList<>();
        implementations = new ArrayList<>();
        interfaceBody = "";
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
    }
    
    public Interfejs(String nazwa, List metody)
    {
        name = nazwa;
        methods = metody;
        implementations = new ArrayList<>();
        interfaceBody = "";
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
    }
    
    public Interfejs(String nazwa, String tresc)
    {
        name = nazwa;
        methods = new ArrayList<>();
        implementations = new ArrayList<>();
        interfaceBody = tresc;
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
    }
    
    public Interfejs(String nazwa, List metody, String tresc)
    {
        name = nazwa;
        methods = metody;
        implementations = new ArrayList<>();
        interfaceBody = tresc;
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
    }
    
    //###############################METODY BEAN PROGRAMMING###############################
    public String getName()
    {
        return name;
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
    
    public List getImplementations()
    {
        return implementations;
    }
    
    public void addImlementation(Klasa nowy) throws Exception
    {
        for(Klasa a : implementations)
        {
            if(a.equals(nowy))
            {
                throw new Exception("Implementacja o podanej nazwie już istnieje!");
            }
        }
        implementations.add(nowy);
    }
    
    public void removeImplementation(String nazwa) throws Exception
    {
        for(Klasa a : implementations)
        {
            if(a.equals(nazwa))
            {
                implementations.remove(a);
                return;
            }
        }
        throw new Exception("Implementacja o podanej nazwie nie istnieje!");
    }
    
    public Klasa getImplementation(String nazwa) throws Exception
    {
        for(Klasa a : implementations)
        {
            if(a.getName().equals(nazwa))
            {
                return a;
            }
        }
        throw new Exception("Implementacja o podanej nazwie nie istnieje!");
    }
    
    public void clearImplementations()
    {
        implementations.clear();
    }
    
    public boolean hasImplementation(String nazwa)
    {
        for(Klasa a : implementations)
        {
            if(a.getName().equals(nazwa))
                return true;
        }
        return false;
    }
    
    public boolean hasAnyImplementation()
    {
        if(implementations.size()>0)
            return true;
        return false;
    }
    
    public String getInterfaceBody()
    {
        return interfaceBody;
    }
    
    public void setInterfaceBody(String newInterfaceBody)
    {
        interfaceBody = newInterfaceBody;
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

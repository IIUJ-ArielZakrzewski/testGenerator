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
public class Metoda {
    
    //###############################ATRYBUTY KLASY DO ANALIZY###############################
    String name;
    String returnType;
    List<Parametr> parameters;
    String methodBody;
    
    //###############################WYNIKOWY KOD###############################
    List<UnitaryTestMethod> tests;
    List<String> imports;
    String testCode;
    
    //###############################KONSTRUKTORY###############################
    public Metoda(String nazwa)
    {
        name = nazwa;
        returnType = "void";
        parameters = new ArrayList<>();
        methodBody = "";
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
    }
    
    
    public Metoda(String nazwa, String typZwracany)
    {
        name = nazwa;
        returnType = typZwracany;
        parameters = new ArrayList<>();
        methodBody = "";
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
    }
    
    public Metoda(String nazwa, List parametry)
    {
        name = nazwa;
        returnType = "void";
        parameters = parametry;
        methodBody = "";
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
        
    }
    
    public Metoda(String nazwa, String typZwracany, List parametry)
    {
        name = nazwa;
        returnType = typZwracany;
        parameters = parametry;
        methodBody = "";
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
    }
    
    //###############################METODY BEAN PROGRAMMING###############################
    public String getName()
    {
        return name;
    }
    
    public void setReturnType(String newType)
    {
        returnType = newType;
    }
    
    public String getReturnType()
    {
        return returnType;
    }
    
    public void setParameters(List newParameters)
    {
        parameters = newParameters;
    }
    
    public List getParameters()
    {
        return parameters;
    }
    
    public void addParameter(Parametr nowy) throws Exception
    {
        for(Parametr a : parameters)
        {
            if(a.getName().equals(nowy.getName()))
            {
                throw new Exception("Parametr o podanej nazwie już istnieje!");
            }
        }
        parameters.add(nowy);
    }
    
    public void removeParameter(String nazwa) throws Exception
    {
        for(Parametr a : parameters)
        {
            if(a.getName().equals(nazwa))
            {
                parameters.remove(a);
                return;
            }
        }
        throw new Exception("Parametr o podanej nazwie nie istnieje!");
    }
    
    public Parametr getParameter(String nazwa) throws Exception
    {
        for(Parametr a : parameters)
        {
            if(a.getName().equals(nazwa))
            {
                return a;
            }
        }
        throw new Exception("Parametr o podanej nazwie nie istnieje!");
    }
    
    public void clearParameters()
    {
        parameters.clear();
    }
    
    public boolean hasParameter(String nazwa)
    {
        for(Parametr a : parameters)
        {
            if(a.getName().equals(nazwa))
                return true;
        }
        return false;
    }
    
    public boolean hasAnyParameter()
    {
        if(parameters.size()>0)
            return true;
        return false;
    }
    
    public int parametersCount()
    {
        return parameters.size();
    }
    
    public void setMethodBody(String body)
    {
        methodBody = body;
    }
    
    public String getMethodBody()
    {
        return methodBody;
    }
    
    public void setTests(List newTests)
    {
        tests = newTests;
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
    
    public void setImoprts(List newImports)
    {
        imports = newImports;
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
                throw new Exception("Klauzula import o podanej nazwie już istnieje!");
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
        throw new Exception("Klauzula import o podanej nazwie nie istnieje!");
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
    
    public void generateUnitaryMethodCommonTests()
    {
        
    }
    
    public void generateTestCode()
    {
        
    }
}

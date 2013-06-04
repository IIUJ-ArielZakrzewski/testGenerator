/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    String packageName;
    TestFile referencedTestFile;
    
    //###############################WYNIKOWY KOD###############################
    List<UnitaryTestMethod> tests;
    List<String> imports;
    String testCode;
    
    //###############################KONSTRUKTORY###############################
    public Interfejs(String nazwa, TestFile ref)
    {
        name = nazwa;
        methods = new ArrayList<>();
        implementations = new ArrayList<>();
        interfaceBody = "";
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
        referencedTestFile = ref;
    }
    
    public Interfejs(String nazwa, List metody, TestFile ref)
    {
        name = nazwa;
        methods = metody;
        implementations = new ArrayList<>();
        interfaceBody = "";
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
        referencedTestFile = ref;
    }
    
    public Interfejs(String nazwa, String tresc, TestFile ref)
    {
        name = nazwa;
        methods = new ArrayList<>();
        implementations = new ArrayList<>();
        interfaceBody = tresc;
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
        referencedTestFile = ref;
    }
    
    public Interfejs(String nazwa, List metody, String tresc, TestFile ref)
    {
        name = nazwa;
        methods = metody;
        implementations = new ArrayList<>();
        interfaceBody = tresc;
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
        referencedTestFile = ref;
    }
    
    //###############################METODY BEAN PROGRAMMING###############################
    public String getPackageName()
    {
        return packageName;
    }
    
    public void setPackageName(String  pkgName)
    {
        packageName = pkgName;
    }
    
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
        Scanner in = new Scanner(interfaceBody);
        String line;
        while(in.hasNextLine())
        {
            line = in.nextLine();
            //System.out.println(line);
            if(line.trim().isEmpty())
            {
                
            } else if(line.matches("^.*\\s.+\\(.*\\);$")) //Deklaracja metody 
            {
                String attr = line.trim();
                attr = attr.replace(";", "");
                String[] tmp = attr.split("\\(");
                tmp[1] = tmp[1].replace(")", "").trim();
                String[] temp = tmp[0].split(" ");
                String returnType;
                String methodName;
                List<Parametr> parameters = new ArrayList<>();
                if(temp.length == 2)
                {
                    returnType = temp[0];
                    methodName = temp[1];
                } else if(temp.length == 3)
                {
                    returnType = temp[1];
                    methodName = temp[2];
                } else {
                    System.out.println("Błąd danych");
                    return;
                }
                if(!tmp[1].isEmpty())
                {
                    temp = tmp[1].split(",");
                    for(int i = 0; i<temp.length; i++)
                    {
                        temp[i].trim();
                        String[] par = temp[i].split(" ");
                        Parametr p = new Parametr(par[1].trim(), par[0].trim());
                        parameters.add(p);
                    }
                }
                Metoda m;
                if(parameters.size()>0)
                {
                    m = new Metoda(methodName, returnType, parameters, null);
                } else {
                    m = new Metoda(methodName, returnType, null);
                }
                methods.add(m);
            } else if(line.contains("//")){
                
            } else if(line.contains("/*")){
                while(!line.contains("*/"))
                {
                    line = in.nextLine();
                }
            } else if(line.trim().equals("{") || line.trim().contains("}"))
            {
                
            } else {
                System.out.println("Błąd danych");
            }
        }
    }
    
    //###############################METODY GENERACJI TESTÓW###############################
    
    public void generateMethodCommonTests()
    {
        
    }
    
    public void generateTestCode()
    {
        
    }
}

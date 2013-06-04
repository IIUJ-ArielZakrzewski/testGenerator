/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Ariel
 */
public class Klasa {
    
    //###############################ATRYBUTY KLASY DO ANALIZY###############################
    String name;
    List<Atrybut> attributes;
    List<Metoda> methods;
    List<Metoda> constructors;
    String implementedInterface;
    String extendedClass;
    String extendedAbstractClass;
    String packageName;
    String classBody;
    TestFile referencedTestFile;
    
    //###############################WYNIKOWY KOD###############################
    List<UnitaryTestMethod> tests;
    List<String> imports;
    String testCode;
    
    //###############################KONSTRUKTORY###############################
    public Klasa(String nazwa, TestFile ref)
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
        constructors = new ArrayList<>();
        testCode = "";
        referencedTestFile = ref;
    }
    
    public Klasa(String nazwa, List atrybuty, TestFile ref)
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
        constructors = new ArrayList<>();
        testCode = "";
        referencedTestFile = ref;
    }
    
    public Klasa(String nazwa, List atrybuty, List metody, TestFile ref)
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
        constructors = new ArrayList<>();
        testCode = "";
        referencedTestFile = ref;
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
    
    public String getImplementedInterface()
    {
        return implementedInterface;
    }
    
    public void setImplementedInterface(String newInterface)
    {
        implementedInterface = newInterface;
    }
    
    public String getExtendedClass()
    {
        return extendedClass;
    }
    
    public void setExtendedClass(String newExtendedClass)
    {
        extendedClass = newExtendedClass;
    }
    
    public String getExtendedAbstractClass()
    {
        return extendedAbstractClass;
    }
    
    public void setExtendedAbstractClass(String newExtendedAbstractClass)
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
    public void analyzeCode() throws Exception
    {
        Scanner in = new Scanner(classBody);
        String line;
        while(in.hasNextLine())
        {
            line = in.nextLine().trim();
            if(line.trim().isEmpty())
            {
            } else if(line.matches("^.+\\s.+;$")) //Deklaracja atrybutu
            {
                String attr = line.trim();
                attr = attr.replace(";", "");
                String[] temp = attr.split(" ");
                if(temp.length == 2)
                {
                    Atrybut a = new Atrybut(temp[1], temp[0]);
                    addAttribute(a);
                } else if(temp.length == 3)
                {
                    Atrybut a = new Atrybut(temp[2], temp[1]);
                    addAttribute(a);
                } else {
                    System.out.println("Błąd danych przy deklaracji atrybutu w klasie");
                }
                
            } else if(line.matches("^.+\\s.+=.+;$")) //Deklaracja atrybutu z inicjalizacją
            {
                String attr = line.trim();
                attr = attr.replace(";", "");
                String[] tmp = attr.split("=");
                tmp[0] = tmp[0].trim();
                tmp[1] = tmp[1].trim();
                String[] temp = tmp[0].split(" ");
                Atrybut a;
                if(temp.length == 2)
                {
                    a = new Atrybut(temp[1], temp[0]);
                } else if(temp.length == 3)
                {
                    a = new Atrybut(temp[2], temp[1]);
                } else {
                    System.out.println("Błąd danych przy deklaracji atrybutu z inicjalizacją");
                    return;
                }
                a.setValue(tmp[1]);
                addAttribute(a);
            } else if(line.matches("^.+\\s.+\\s.+\\(.*\\).*$") && !line.contains("="))//Deklaracja metody
            {
                String methodLine = line;
                boolean containesClamre = methodLine.contains("{");
                if(containesClamre)
                {
                    methodLine = methodLine.replace("{", "");
                }
                String[] tmp = methodLine.split("\\(");
                tmp[1] = tmp[1].replace(")", "");
                String[] temp = tmp[0].split(" ");
                String returnType;
                String methodName;
                List<Parametr> parameters = new ArrayList<>();
                if(temp.length == 3)
                {
                    returnType = temp[1];
                    methodName = temp[2];
                } else {
                    System.out.println("Błąd danych przy deklaracji metody");
                    return;
                }
                if(!tmp[1].trim().isEmpty())
                {
                    temp = tmp[1].split(",");
                    for(int i = 0; i<temp.length; i++)
                    {
                        temp[i] = temp[i].trim();
                        String[] par = temp[i].split(" ");
                        Parametr p = new Parametr(par[1].trim(), par[0].trim());
                        parameters.add(p);
                    }
                }
                Metoda m;
                
                if(parameters.size()>0)
                {
                    m = new Metoda(methodName, returnType, parameters, this);
                } else {
                    m = new Metoda(methodName, returnType, this);
                }
                Stack<String> stos = new Stack<>();
                if(containesClamre)
                {
                    stos.push("{");
                } else {
                    line = in.nextLine().trim();
                    while(!line.contains("{"))
                    {
                        line = in.nextLine().trim();
                    }
                    stos.push("{");
                }
                String body = "{\n";
                while(!stos.isEmpty())
                {
                    line = in.nextLine().trim();
                    body += line + "\n";
                    if(line.contains("{"))
                        stos.push("{");
                    if(line.contains("}"))
                        stos.pop();
                }
                m.setMethodBody(body);
                methods.add(m);
            } else if(line.matches("^.+\\s.+\\(.*\\).*$") && !line.contains("="))//Deklaracja konstruktora lub metody bez modyfikatora
            {
                String methodLine = line;
                boolean containesClamre = methodLine.contains("{");
                if(containesClamre)
                {
                    methodLine = methodLine.replace("{", "");
                }
                String[] tmp = methodLine.split("\\(");
                tmp[1] = tmp[1].replace(")", "").trim();
                tmp[0] = tmp[0].trim();
                String[] temp = tmp[0].split(" ");
                String returnType;
                String methodName;
                List<Parametr> parameters = new ArrayList<>();
                if(temp.length == 2)
                {
                    returnType = temp[0];
                    methodName = temp[1];
                } else {
                    System.out.println("Błąd danych w deklaracji konstruktora lub metody bez modyfikatora");
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
                boolean isConstructor = returnType.trim().equals("public") || returnType.trim().equals("private") || returnType.trim().equals("protected");
                if(isConstructor)
                {
                    returnType = "";
                }
                if(parameters.size()>0)
                {
                    m = new Metoda(methodName, returnType, parameters, this);
                } else {
                    m = new Metoda(methodName, returnType, this);
                }
                Stack<String> stos = new Stack<>();
                if(containesClamre)
                {
                    stos.push("{");
                } else {
                    line = in.nextLine().trim();
                    while(!line.contains("{"))
                    {
                        line = in.nextLine().trim();
                    }
                    stos.push("{");
                }
                String body = "{\n";
                while(!stos.isEmpty())
                {
                    line = in.nextLine().trim();
                    body += line + "\n";
                    if(line.contains("{"))
                        stos.push("{");
                    if(line.contains("}"))
                        stos.pop();
                }
                m.setMethodBody(body);
                if(isConstructor)
                {
                    constructors.add(m);
                } else {
                    methods.add(m);
                }
                
            } else if(line.contains("//")){
                
            } else if(line.contains("/*")){
                while(!line.contains("*/"))
                {
                    line = in.nextLine().trim();
                }
            } else if(line.trim().equals("{") || line.trim().contains("}"))
            {
            } else {
                System.out.println("Nieznany błąd danych");
            }
        }
        for(Metoda m : methods)
        {
            m.analyzeCode();
        }
        for(Metoda m : constructors)
        {
            m.analyzeCode();
        }
    }
    
    public void checkAttributesAboutMocks()
    {
        for(Atrybut a : attributes)
        {
            for(SourceFile s : referencedTestFile.sources)
            {
                for(Interfejs i : s.interfaces)
                {
                    if(a.getType().equals(i.getName()))
                    {
                        a.setRequiresMock(true);
                    }
                }
                
            }
        }
    }
    
    public boolean requiresMock()
    {
        for(Atrybut a : attributes)
        {
            if(a.getRequiresMock())
                return true;
        }
        return false;
    }
    
    //###############################METODY GENERACJI TESTÓW###############################
    
    public void generateMethodCommonTests() throws Exception
    {
        
        for(Metoda m : methods)
        {
            m.generateUnitaryMethodCommonTests();
            m.generateTestCode();
        }
    }
    
    public void generateTestCode()
    {
        checkAttributesAboutMocks();
        testCode += "\n\n" + "public class " + getName() + "Test extends " + getName() + "\n{";
        testCode += "\n\n";
        if(requiresMock())
        {
            testCode += "\n/*";
            testCode += "\n*Wśród atrybutów klasy występują deklaracje obiektów jako";
            testCode += "\n*implementacji interfejsów. ";
            testCode += "\n*W kodach metod należy pamiętać o napisaniu scenariusza wywołań";
            testCode += "\n*metod tych obiektów wykorzystując komendy biblioteki easyMock!";
            testCode += "\n*/";
        }
        testCode += "\n    @Before";
        testCode += "\n    public void setUp()";
        testCode += "\n    {";
        
        if(requiresMock())
        {
            for(Atrybut a : attributes)
            {
                if(a.getRequiresMock())
                {
                    testCode += "\n        " + a.getName() + " = createMock(" + a.getType() + ".class);";
                }
            }
        }
        
        testCode += "\n    }";
        
        for(Metoda m : methods)
        {
            Scanner in = new Scanner(m.getTestCode());
            while(in.hasNextLine())
            {
                testCode += "\n    " + in.nextLine();
            }
        }
        if(extendedClass != null && !extendedClass.equals(""))
        {
            Klasa k = null;
            for(SourceFile sf : referencedTestFile.sources)
            {
                for(Klasa s : sf.classes)
                {
                    if(s.getName().equals(extendedClass))
                    {
                        k = s;
                    }
                }
            }
            
            if(k != null)
            {
                for(Metoda m : k.methods)
                {
                    boolean nieprzeladowana = true;
                    for(Metoda mym : methods)
                    {
                        if(m.getName().equals(mym.getName()))
                        {
                            nieprzeladowana = false;
                        }
                        
                        if(nieprzeladowana)
                        {
                            Scanner in = new Scanner(m.getTestCode());
                            while(in.hasNextLine())
                            {
                                testCode += "\n    " + in.nextLine();
                            }
                        }
                    }
                }
            }
        }
        
        if(extendedAbstractClass != null && !extendedAbstractClass.equals(""))
        {
            Klasa k = null;
            for(SourceFile sf : referencedTestFile.sources)
            {
                for(Klasa s : sf.abstractClasses)
                {
                    if(s.getName().equals(extendedAbstractClass))
                    {
                        k = s;
                    }
                }
            }
            
            if(k != null)
            {
                for(Metoda m : k.methods)
                {
                    boolean nieprzeladowana = true;
                    for(Metoda mym : methods)
                    {
                        if(m.getName().equals(mym.getName()))
                        {
                            nieprzeladowana = false;
                        }
                        
                        if(nieprzeladowana)
                        {
                            Scanner in = new Scanner(m.getTestCode());
                            while(in.hasNextLine())
                            {
                                testCode += "\n    " + in.nextLine();
                            }
                        }
                    }
                }
            }
        }
        
        testCode += "\n}\n";
    }
}

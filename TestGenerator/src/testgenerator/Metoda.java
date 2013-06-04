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
public class Metoda {
    
    //###############################ATRYBUTY KLASY DO ANALIZY###############################
    String name;
    String returnType;
    List<Parametr> parameters;
    String body;
    Statement methodBody;
    Klasa parentClass;
    
    
    //###############################WYNIKOWY KOD###############################
    List<UnitaryTestMethod> tests;
    List<String> imports;
    String testCode;
    
    //###############################KONSTRUKTORY###############################
    public Metoda(String nazwa, Klasa parent)
    {
        name = nazwa;
        returnType = "void";
        parameters = new ArrayList<>();
        body = "";
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
        methodBody = new StartStatement();
        parentClass = parent;
    }
    
    
    public Metoda(String nazwa, String typZwracany, Klasa parent)
    {
        name = nazwa;
        returnType = typZwracany;
        parameters = new ArrayList<>();
        body = "";
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
        methodBody = new StartStatement();
        parentClass = parent;
    }
    
    public Metoda(String nazwa, List parametry, Klasa parent)
    {
        name = nazwa;
        returnType = "void";
        parameters = parametry;
        body = "";
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
        methodBody = new StartStatement();
        parentClass = parent;
        
    }
    
    public Metoda(String nazwa, String typZwracany, List parametry, Klasa parent)
    {
        name = nazwa;
        returnType = typZwracany;
        parameters = parametry;
        body = "";
        tests = new ArrayList<>();
        imports = new ArrayList<>();
        testCode = "";
        methodBody = new StartStatement();
        parentClass = parent;
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
    
    public void setMethodBody(String bd)
    {
        body = bd;
    }
    
    public String getMethodBody()
    {
        return body;
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
        Scanner in = new Scanner(body);
        String line;
        while(in.hasNextLine())
        {
            line = in.nextLine();
            if(line.trim().isEmpty())
            {
                
            } else if(line.matches("^for\\(.+;.+;.+\\).*$")) //standardowy for
            {
                String attr = line;
                boolean containsClamre = attr.contains("{");
                if(containsClamre)
                {
                    attr = attr.replace("{", "");
                }
                attr = attr.replace("for", "").trim();
                String[] tmp = attr.split(";");
                String condition = tmp[1].trim();
                String increment = tmp[2].trim();
                String variable = tmp[0].trim();
                String variableType = "";
                if(variable.contains(" "))
                {
                    String[] temp = variable.split(" ");
                    variableType = temp[0].trim();
                    variable = temp[1].trim();
                }
                Stack<String> stos = new Stack<>();
                if(containsClamre)
                {
                    stos.push("{");
                } else {
                    line = in.nextLine();
                    while(!line.contains("{"))
                    {
                        line = in.nextLine();
                    }
                    stos.push("{");
                }
                String forBody = "{\n";
                while(!stos.isEmpty())
                {
                    line = in.nextLine();
                    forBody += line + "\n";
                    if(line.contains("{"))
                        stos.push("{");
                    if(line.contains("}"))
                        stos.pop();
                }
                For forStatement = new For(variable, condition, increment, forBody);
                if(!variableType.equals(""))
                {
                    forStatement.setType(variableType);
                }
                addStatement(forStatement);
            } else if(line.matches("^for\\(.+\\s.+:.+\\).*$")) //foreach
            {
                String attr = line;
                boolean containsClamre = attr.contains("{");
                if(containsClamre)
                {
                    attr = attr.replace("{", "");
                }
                attr = attr.replace("for", "").trim();
                String[] tmp = attr.split(":");
                String varTemp = tmp[0].trim();
                String collectionName = tmp[1].trim();
                tmp = varTemp.split(" ");
                String variable = tmp[1].trim();
                String variableType = tmp[0].trim();
                
                Stack<String> stos = new Stack<>();
                if(containsClamre)
                {
                    stos.push("{");
                } else {
                    line = in.nextLine();
                    while(!line.contains("{"))
                    {
                        line = in.nextLine();
                    }
                    stos.push("{");
                }
                String forBody = "{\n";
                while(!stos.isEmpty())
                {
                    line = in.nextLine();
                    forBody += line + "\n";
                    if(line.contains("{"))
                        stos.push("{");
                    if(line.contains("}"))
                        stos.pop();
                }
                For forStatement = new For(variableType, variable, forBody);
                forStatement.setCollectionName(collectionName);
                forStatement.setIfForEach(true);
                addStatement(forStatement);
            } else if(line.matches("^if\\(.+\\).*$"))//if
            {
                String attr = line;
                boolean containsClamre = attr.contains("{");
                if(containsClamre)
                {
                    attr = attr.replace("{", "").trim();
                }
                attr = attr.replace("if", "").trim();
                attr = attr.substring(1, attr.length()-2).trim();
                String condition = attr;
                String ifBody = "";
                String elseBody = "";
                if(containsClamre)
                {
                    Stack<String> stos = new Stack<>();
                    stos.push("{");
                    ifBody += "{\n";
                    while(!stos.isEmpty())
                    {
                        line = in.nextLine();
                        ifBody += line + "\n";
                        if(line.contains("{"))
                            stos.push("{");
                        if(line.contains("}"))
                            stos.pop();
                    }
                } else {
                    line = in.nextLine();
                    while(line.trim().isEmpty())
                    {
                        line = in.nextLine();
                    }
                    
                    if(line.contains("{"))
                    {
                        Stack<String> stos = new Stack<>();
                        stos.push("{");
                        ifBody += "{\n";
                        while(!stos.isEmpty())
                        {
                            line = in.nextLine();
                            ifBody += line + "\n";
                            if(line.contains("{"))
                                stos.push("{");
                            if(line.contains("}"))
                                stos.pop();
                        }
                    } else {
                        ifBody = line;
                    }
                }
                if(line.contains("else"))
                {
                    containsClamre = line.contains("{");
                    if(containsClamre)
                    {
                        Stack<String> stos = new Stack<>();
                        stos.push("{");
                        elseBody += "{\n";
                        while(!stos.isEmpty())
                        {
                            line = in.nextLine();
                            elseBody += line + "\n";
                            if(line.contains("{"))
                                stos.push("{");
                            if(line.contains("}"))
                                stos.pop();
                        }
                    } else {
                        while(line.trim().isEmpty())
                        {
                            line = in.nextLine();
                        }

                        if(line.contains("{"))
                        {
                            Stack<String> stos = new Stack<>();
                            stos.push("{");
                            elseBody += "{\n";
                            while(!stos.isEmpty())
                            {
                                line = in.nextLine();
                                elseBody += line + "\n";
                                if(line.contains("{"))
                                    stos.push("{");
                                if(line.contains("}"))
                                    stos.pop();
                            }
                        } else {
                            elseBody = line;
                        }

                    }
                }
                If ifStatement = new If(condition);
                ifStatement.setIfSource(ifBody);
                if(!elseBody.equals(""))
                {
                    ifStatement.setElseSource(elseBody);
                } 
                addStatement(ifStatement);
            } else if(line.matches(".*else.*$"))//switch
            {
                if(getLastStatement() instanceof If)
                {
                    If stat = (If)getLastStatement();
                    String attr = line;
                    String elseBody = "";
                    if(line.contains("{"))
                    {
                        Stack<String> stos = new Stack<>();
                        stos.push("{");
                        elseBody += "{\n";
                        while(!stos.isEmpty())
                        {
                            line = in.nextLine();
                            elseBody += line + "\n";
                            if(line.contains("{"))
                                stos.push("{");
                            if(line.contains("}"))
                                stos.pop();
                        }
                    } else {
                        while(line.trim().isEmpty())
                        {
                            line = in.nextLine();
                        }

                        if(line.contains("{"))
                        {
                            Stack<String> stos = new Stack<>();
                            stos.push("{");
                            elseBody += "{\n";
                            while(!stos.isEmpty())
                            {
                                line = in.nextLine();
                                elseBody += line + "\n";
                                if(line.contains("{"))
                                    stos.push("{");
                                if(line.contains("}"))
                                    stos.pop();
                            }
                        } else {
                            elseBody = line;
                        }
                    }
                    
                    stat.setElseSource(elseBody);
                } else {
                    System.out.println("Błąd parsowania");
                    return;
                }
            } else if(line.matches("^switch\\(.+\\).*$"))//switch
            {
                String attr = line;
                boolean containsClamre = attr.contains("{");
                if(containsClamre)
                {
                    attr = attr.replace("{", "").trim();
                }
                attr = attr.replace("switch", "").trim();
                String variable = attr.substring(1, attr.length()-2);
                boolean continueSwitch = true;
                Switch s = new Switch(variable);
                if(!containsClamre)
                {
                    while(!line.contains("{"))
                    {
                        line = in.nextLine();
                    }
                }
                SwitchCase c = null;
                String bodySwitch = "";
                int it = 0;
                while(continueSwitch)
                {
                    line = in.nextLine();
                    if(line.contains("case"))
                    {
                        if(c == null)
                        {
                            c = new SwitchCase();
                        } else {
                            c.setBodyStatement(bodySwitch);
                            s.addCase(c);
                            bodySwitch = "";
                            c = new SwitchCase();
                        }
                        String newCase = line.replace("case", "").replace(":", "").trim();
                        c.setConditionValue(newCase);
                    } else if(line.contains("default")) {
                        c.setBodyStatement(bodySwitch);
                        s.addCase(c);
                        bodySwitch = "";
                        c = new SwitchCase();
                        c.setIsDefault(true);
                    } else {
                        bodySwitch += line + "\n";
                        if(line.contains("break"))
                        {
                            c.SetContainsBreak(true);
                        }
                        if(line.contains("{"))
                        {
                            it++;
                        }
                        if(line.contains("}"))
                        {
                            it--;
                        }
                        if(it<0)
                        {
                            c.setBodyStatement(bodySwitch);
                            s.addCase(c);
                            bodySwitch = "";
                            c = null;
                            continueSwitch = false;
                        }
                    }
                }
                addStatement(s);
            } else if(line.matches("^do.*$"))//do while
            {
                String attr = line;
                boolean containsClamre = attr.contains("{");
                
                Stack<String> stos = new Stack<>();
                if(containsClamre)
                {
                    stos.push("{");
                } else {
                    line = in.nextLine();
                    while(!line.contains("{"))
                    {
                        line = in.nextLine();
                    }
                    stos.push("{");
                }
                String whileBody = "{\n";
                while(!stos.isEmpty())
                {
                    line = in.nextLine();
                    whileBody += line + "\n";
                    if(line.contains("{"))
                        stos.push("{");
                    if(line.contains("}"))
                        stos.pop();
                }
                if(!line.contains("while"))
                {
                    while(!line.contains("while"))
                    {
                        line = in.nextLine();
                    }
                }
                attr = line.replace(";", "").trim();
                attr = attr.replace("while", "").trim();
                attr = attr.replace("\\(", "").trim();
                attr = attr.replace(")", "").trim();
                String condition = attr;
                While whileStatement = new While(condition, whileBody);
                whileStatement.setIfDoWhile(true);
                addStatement(whileStatement);
            } else if(line.matches("^while\\(.+\\).*$"))//while
            {
                String attr = line;
                boolean containsClamre = attr.contains("{");
                if(containsClamre)
                {
                    attr = attr.replace("{", "").trim();
                }
                attr = attr.replace("while", "").trim();
                attr = attr.replace("\\(", "").trim();
                attr = attr.replace(")", "").trim();
                String condition = attr;
                Stack<String> stos = new Stack<>();
                if(containsClamre)
                {
                    stos.push("{");
                } else {
                    line = in.nextLine();
                    while(!line.contains("{"))
                    {
                        line = in.nextLine();
                    }
                    stos.push("{");
                }
                String whileBody = "{\n";
                while(!stos.isEmpty())
                {
                    line = in.nextLine();
                    whileBody += line + "\n";
                    if(line.contains("{"))
                        stos.push("{");
                    if(line.contains("}"))
                        stos.pop();
                }
                While whileStatement = new While(condition, whileBody);
                addStatement(whileStatement);
                
            } else if(line.matches("^try.*$"))//try .. catch
            {
                String attr = line;
                boolean containsClamre = attr.contains("{");
                Stack<String> stos = new Stack<>();
                if(containsClamre)
                {
                    stos.push("{");
                } else {
                    line = in.nextLine();
                    while(!line.contains("{"))
                    {
                        line = in.nextLine();
                    }
                    stos.push("{");
                }
                String tryBody = "{\n";
                String catchBody;
                String exArg;
                while(!stos.isEmpty())
                {
                    line = in.nextLine();
                    tryBody += line + "\n";
                    if(line.contains("{"))
                        stos.push("{");
                    if(line.contains("}"))
                        stos.pop();
                }
                if(line.contains("catch"))
                {
                    if(line.contains("("))
                    {
                        exArg = line.substring(line.indexOf("(") + 1, line.indexOf(")") - 1);
                    } else {
                        while(!line.contains("("))
                        {
                            line = in.nextLine();
                        }
                        exArg = line.substring(line.indexOf("(") + 1, line.indexOf(")") - 1);
                    }
                    containsClamre = line.contains("{");
                    if(!containsClamre)
                    {
                        line = in.nextLine();
                        while(!line.contains("{"))
                        {
                            line = in.nextLine();
                        }
                    }
                } else {
                    while(!line.contains("catch"))
                    {
                        line = in.nextLine();
                    }
                    
                    if(line.contains("("))
                    {
                        exArg = line.substring(line.indexOf("(") + 1, line.indexOf(")") - 1);
                    } else {
                        while(!line.contains("("))
                        {
                            line = in.nextLine();
                        }
                        exArg = line.substring(line.indexOf("(") + 1, line.indexOf(")") - 1);
                    }
                    while(!line.contains("{"))
                    {
                        line = in.nextLine();
                    }
                }
                stos.push("{");
                catchBody = "{\n";
                while(!stos.isEmpty())
                {
                    line = in.nextLine();
                    catchBody += line + "\n";
                    if(line.contains("{"))
                        stos.push("{");
                    if(line.contains("}"))
                        stos.pop();
                }
                
                TryCatch tc = new TryCatch();
                tc.setCatchException(exArg);
                tc.setBodyTry(tryBody);
                tc.setBodyCatch(catchBody);
                
            } else if(line.contains("finally")){
                
                if(!(getLastStatement() instanceof TryCatch))
                {
                    System.out.println("Bład parsowania danych");
                    return;
                }
                TryCatch tc = (TryCatch) getLastStatement();
                String attr = line;
                boolean containsClamre = attr.contains("{");
                Stack<String> stos = new Stack<>();
                if(containsClamre)
                {
                    stos.push("{");
                } else {
                    line = in.nextLine();
                    while(!line.contains("{"))
                    {
                        line = in.nextLine();
                    }
                    stos.push("{");
                }
                String finallyBody = "{\n";
                while(!stos.isEmpty())
                {
                    line = in.nextLine();
                    finallyBody += line + "\n";
                    if(line.contains("{"))
                        stos.push("{");
                    if(line.contains("}"))
                        stos.pop();
                }
                tc.setBodyFinally(finallyBody);
            } else if(line.contains("//")){
                
            } else if(line.contains("/*")){
                while(!line.contains("*/"))
                {
                    line = in.nextLine();
                }
            } else if(line.trim().equals("{"))
            {
                
            } else if(line.trim().contains("}"))
            {
                finish();
            } else {
                addStatement(new Command(line));
            }
        }
        
        Statement s = methodBody;
        while(s!=null)
        {
            s.analyzeCode();
            s = s.nextStatement;
        }
    }
    
    public void addStatement(Statement s)
    {
        Statement stearing = methodBody;
        while(stearing.nextStatement != null)
        {
            stearing = stearing.nextStatement;
        }
        if(stearing instanceof EndStatement)
        {
            return;
        }
        stearing.nextStatement = s;
    }
    
    public Statement getLastStatement()
    {
        Statement temp = methodBody;
        while(temp.nextStatement != null)
        {
            temp = temp.nextStatement;
        }
        return temp;
    }
    
    public void finish()
    {
        addStatement(new EndStatement());
    }
    //###############################METODY GENERACJI TESTÓW###############################
    
    public void generateUnitaryMethodCommonTests()
    {
        tests.add(generateCorrectInputTest());
        for(Parametr p : parameters)
        {
            tests.add(generateIncorrectInputNullParameterTest(p));
            tests.add(generateIncorrectInputValueParameterTest(p));
        }
    }
    
    public UnitaryTestMethod generateCorrectInputTest()
    {
        UnitaryTestMethod testCase = new UnitaryTestMethod(getName() + "CorrectInputTest");
        String testCaseBody = "{";
        if(parameters.isEmpty())
        {
            testCase.setComment("Test sprawdzający działanie metody " + getName() + "\n");
        } else {
            testCase.setComment("Test sprawdzający działanie metody " + getName() + "\n" +
                    "dla prawidłowych parametrów wejściowych.");
        }
        
        String methodCall;
        if(!parameters.isEmpty())
        {
            methodCall = getName() + "(";
            boolean isFirst = true;
            for(Parametr p : parameters)
            {
                testCaseBody += "\n" + p.getType() + " input_" + p.getName() + " = <podaj poprawną wartość wejściową parametru>;" + "//Należy zainicjować wartość parametru wejściowego: " + p.getName();
                if(isFirst)
                {
                    isFirst = false;
                } else {
                    methodCall += ", ";
                }
                methodCall += "input" + p.getName();
            }
            methodCall += ")";
        } else {
            methodCall = getName() + "(" + ")";
        }
        
        
        if(parentClass.requiresMock())
        {
            for(Atrybut a : parentClass.attributes)
            {
                if(a.getRequiresMock())
                {
                    if(body.contains(a.getName()))
                    {
                        testCaseBody += "\n/*W metodzie został użyty atrybut interfejsu,";
                        testCaseBody += "\n*prawdopodobnie jest on generowany jako obiekt mock w metodzie setUp,";
                        testCaseBody += "\n*należy na podstawie ciała metody napisać scenariusz jego wywołań*/";
                        testCaseBody += "\n" + "reply(" + a.getName() + ");";
                    }
                }
            }
            
            
        }
        
        if(!getReturnType().equals("") && !getReturnType().equals("void"))
        {
            boolean isProjectClassObject = false;
            boolean isProjectInterfaceImplementationObject = false;
            
            for(SourceFile s : parentClass.referencedTestFile.sources)
            {
                if(!s.classes.isEmpty())
                {
                    for(Klasa c : s.classes)
                    {
                        if(c.getName().equals(getReturnType()))
                        {
                            isProjectClassObject = true;
                        }
                    }
                }
                if(!s.abstractClasses.isEmpty() && !isProjectClassObject)
                {
                    for(KlasaAbstrakcyjna c : s.abstractClasses)
                    {
                        if(c.getName().equals(getReturnType()))
                        {
                            isProjectClassObject = true;
                        }
                    }
                }
                if(!s.interfaces.isEmpty() && !isProjectClassObject)
                {
                    for(Interfejs c : s.interfaces)
                    {
                        if(c.getName().equals(getReturnType()))
                        {
                            isProjectInterfaceImplementationObject = true;
                        }
                    }
                }
            }
            
            if(isProjectClassObject)
            {
                testCaseBody += "\n" + "//" + getReturnType() + " expectedValue = new " + getReturnType() + "();" + "//Należy zainicjować obiekt w taki sposób, aby odpowiadał przewidywanemu zwróconemu obiektowi.";
                testCaseBody += "\n" + getReturnType() + " resultValue = " + methodCall + ";";
                testCaseBody += "\n" + "System.out.println(\"Testy prawidłowego wejścia dla metody: " + getName() + "\");";
                testCaseBody += "\n" + "assertNotNull(resultValue);";
                testCaseBody += "\n" + "assertEquals(expectedValue, resultValue);";
            } else if(isProjectInterfaceImplementationObject) {
                testCaseBody += "\n" + "//" + getReturnType() + " expectedValue = new <Należy wpisać nazwę klasy implementującej interfejs, której instancję zwraca metoda>();" + "//Należy zainicjować obiekt w taki sposób, aby odpowiadał przewidywanemu zwróconemu obiektowi.";
                testCaseBody += "\n" + getReturnType() + " resultValue = " + methodCall + ";";
                testCaseBody += "\n" + "System.out.println(\"Testy prawidłowego wejścia dla metody: " + getName() + "\");";
                testCaseBody += "\n" + "assertNotNull(resultValue);";
                testCaseBody += "\n" + "assertEquals(expectedValue, resultValue);";
            } else {
                testCaseBody += "\n" + getReturnType() + " expectedValue = <oczekiwana wartość>;" + "//Należy przypisać wartość oczekiwaną jako wynik zwrócony przez metodę.";
                testCaseBody += "\n" + getReturnType() + " resultValue = " + methodCall + ";";
                testCaseBody += "\n" + "System.out.println(\"Testy prawidłowego wejścia dla metody: " + getName() + "\");";
                testCaseBody += "\n" + "assertEquals(expectedValue, resultValue);";
            }
        } else {
            testCase.addCommandLine("Metoda nie zwraca żadnego obiektu,");
            testCase.addCommandLine("należy przygotować analizę atrybutów klasy zmienianych przez metodę.");
        }
        testCaseBody += "\n}";
        testCase.setBodyText(testCaseBody);
        return testCase;
    }
    
    public UnitaryTestMethod generateIncorrectInputNullParameterTest(Parametr parameter)
    {
        UnitaryTestMethod testCase = new UnitaryTestMethod(getName() + "IncorrectInputNull" + parameter.getName() + "ParameterTest");
        String testCaseBody = "{";
        testCase.setComment("Test sprawdzający działanie metody " + getName() + "\n" +
                    "dla argumentu " + parameter.getName() + " o wartości null.");
        
        
        String methodCall;
        
        methodCall = getName() + "(";
        boolean isFirst = true;
        for(Parametr p : parameters)
        {
            if(!p.getName().equals(parameter.getName()))
            {
                testCaseBody += "\n" + p.getType() + " input_" + p.getName() + " = <podaj poprawną wartość wejściową parametru>;" + "//Należy zainicjować wartość parametru wejściowego: " + p.getName();
            } else {
                testCaseBody += "\n" + p.getType() + " input_" + p.getName() + " = null;" + "//Inicjalizacja parametru wejściowego: " + p.getName() + " jako null";
            }
            if(isFirst)
            {
                isFirst = false;
            } else {
                methodCall += ", ";
            }
            methodCall += "input" + p.getName();
        }
        methodCall += ")";
        
        
        
        if(parentClass.requiresMock())
        {
            for(Atrybut a : parentClass.attributes)
            {
                if(a.getRequiresMock())
                {
                    if(body.contains(a.getName()))
                    {
                        testCaseBody += "\n/*W metodzie został użyty atrybut interfejsu,";
                        testCaseBody += "\n*prawdopodobnie jest on generowany jako obiekt mock w metodzie setUp,";
                        testCaseBody += "\n*należy na podstawie ciała metody napisać scenariusz jego wywołań*/";
                        testCaseBody += "\n" + "reply(" + a.getName() + ");";
                    }
                }
            }
            
            
        }
        
        if(!getReturnType().equals("") && !getReturnType().equals("void"))
        {
            boolean isProjectClassObject = false;
            boolean isProjectInterfaceImplementationObject = false;
            
            for(SourceFile s : parentClass.referencedTestFile.sources)
            {
                if(!s.classes.isEmpty())
                {
                    for(Klasa c : s.classes)
                    {
                        if(c.getName().equals(getReturnType()))
                        {
                            isProjectClassObject = true;
                        }
                    }
                }
                if(!s.abstractClasses.isEmpty() && !isProjectClassObject)
                {
                    for(KlasaAbstrakcyjna c : s.abstractClasses)
                    {
                        if(c.getName().equals(getReturnType()))
                        {
                            isProjectClassObject = true;
                        }
                    }
                }
                if(!s.interfaces.isEmpty() && !isProjectClassObject)
                {
                    for(Interfejs c : s.interfaces)
                    {
                        if(c.getName().equals(getReturnType()))
                        {
                            isProjectInterfaceImplementationObject = true;
                        }
                    }
                }
            }
            
            if(isProjectClassObject)
            {
                testCaseBody += "\n" + "//" + getReturnType() + " expectedValue = new " + getReturnType() + "();" + "//Należy zainicjować obiekt w taki sposób, aby odpowiadał przewidywanemu zwróconemu obiektowi.";
                testCaseBody += "\n" + getReturnType() + " resultValue = " + methodCall + ";";
                testCaseBody += "\n" + "System.out.println(\"Testy błędnego wejścia dla metody: " + getName() + ", parametr " + parameter.getName() + " jako wartość null\");";
                testCaseBody += "\n" + "assertNotNull(resultValue);";
                testCaseBody += "\n" + "assertEquals(expectedValue, resultValue);";
            } else if(isProjectInterfaceImplementationObject) {
                testCaseBody += "\n" + "//" + getReturnType() + " expectedValue = new <Należy wpisać nazwę klasy implementującej interfejs, której instancję zwraca metoda>();" + "//Należy zainicjować obiekt w taki sposób, aby odpowiadał przewidywanemu zwróconemu obiektowi.";
                testCaseBody += "\n" + getReturnType() + " resultValue = " + methodCall + ";";
                testCaseBody += "\n" + "System.out.println(\"Testy błędnego wejścia dla metody: " + getName() + ", parametr " + parameter.getName() + " jako wartość null\");";
                testCaseBody += "\n" + "assertNotNull(resultValue);";
                testCaseBody += "\n" + "assertEquals(expectedValue, resultValue);";
            } else {
                testCaseBody += "\n" + getReturnType() + " expectedValue = <oczekiwana wartość>;" + "//Należy przypisać wartość oczekiwaną jako wynik zwrócony przez metodę.";
                testCaseBody += "\n" + getReturnType() + " resultValue = " + methodCall + ";";
                testCaseBody += "\n" + "System.out.println(\"Testy błędnego wejścia dla metody: " + getName() + ", parametr " + parameter.getName() + " jako wartość null\");";
                testCaseBody += "\n" + "assertEquals(expectedValue, resultValue);";
            }
        } else {
            testCase.addCommandLine("Metoda nie zwraca żadnego obiektu,");
            testCase.addCommandLine("należy przygotować analizę atrybutów klasy zmienianych przez metodę.");
        }
        testCaseBody += "\n}";
        testCase.setBodyText(testCaseBody);
        return testCase;
    }
    
    public UnitaryTestMethod generateIncorrectInputValueParameterTest(Parametr parameter)
    {
        UnitaryTestMethod testCase = new UnitaryTestMethod(getName() + "IncorrectInputValue" + parameter.getName() + "ParameterTest");
        String testCaseBody = "{";
        testCase.setComment("Test sprawdzający działanie metody " + getName() + "\n" +
                    "dla argumentu " + parameter.getName() + " o niepoprawnej wartości.");
        
        
        String methodCall;
        
        methodCall = getName() + "(";
        boolean isFirst = true;
        for(Parametr p : parameters)
        {
            if(!p.getName().equals(parameter.getName()))
            {
                testCaseBody += "\n" + p.getType() + " input_" + p.getName() + " = <podaj poprawną wartość wejściową parametru>;" + "//Należy zainicjować wartość parametru wejściowego: " + p.getName();
            } else {
                testCaseBody += "\n" + p.getType() + " input_" + p.getName() + " = <podaj niepoprawną wartość wejściową parametru>;" + "//Należy zainicjować niepoprawną wartość parametru wejściowego: " + p.getName();
            }
            if(isFirst)
            {
                isFirst = false;
            } else {
                methodCall += ", ";
            }
            methodCall += "input" + p.getName();
        }
        methodCall += ")";
        
        
        
        if(parentClass.requiresMock())
        {
            for(Atrybut a : parentClass.attributes)
            {
                if(a.getRequiresMock())
                {
                    if(body.contains(a.getName()))
                    {
                        testCaseBody += "\n/*W metodzie został użyty atrybut interfejsu,";
                        testCaseBody += "\n*prawdopodobnie jest on generowany jako obiekt mock w metodzie setUp,";
                        testCaseBody += "\n*należy na podstawie ciała metody napisać scenariusz jego wywołań*/";
                        testCaseBody += "\n" + "reply(" + a.getName() + ");";
                    }
                }
            }
            
            
        }
        
        if(!getReturnType().equals("") && !getReturnType().equals("void"))
        {
            boolean isProjectClassObject = false;
            boolean isProjectInterfaceImplementationObject = false;
            
            for(SourceFile s : parentClass.referencedTestFile.sources)
            {
                if(!s.classes.isEmpty())
                {
                    for(Klasa c : s.classes)
                    {
                        if(c.getName().equals(getReturnType()))
                        {
                            isProjectClassObject = true;
                        }
                    }
                }
                if(!s.abstractClasses.isEmpty() && !isProjectClassObject)
                {
                    for(KlasaAbstrakcyjna c : s.abstractClasses)
                    {
                        if(c.getName().equals(getReturnType()))
                        {
                            isProjectClassObject = true;
                        }
                    }
                }
                if(!s.interfaces.isEmpty() && !isProjectClassObject)
                {
                    for(Interfejs c : s.interfaces)
                    {
                        if(c.getName().equals(getReturnType()))
                        {
                            isProjectInterfaceImplementationObject = true;
                        }
                    }
                }
            }
            
            if(isProjectClassObject)
            {
                testCaseBody += "\n" + "\\\\" + getReturnType() + " expectedValue = new " + getReturnType() + "();" + "\\\\Należy zainicjować obiekt w taki sposób, aby odpowiadał przewidywanemu zwróconemu obiektowi.";
                testCaseBody += "\n" + getReturnType() + " resultValue = " + methodCall + ";";
                testCaseBody += "\n" + "System.out.println(\"Testy błędnego wejścia dla metody: " + getName() + ", parametr " + parameter.getName() + " jako niepoprawna wartość\");";
                testCaseBody += "\n" + "assertNotNull(resultValue);";
                testCaseBody += "\n" + "assertEquals(expectedValue, resultValue);";
            } else if(isProjectInterfaceImplementationObject) {
                testCaseBody += "\n" + "\\\\" + getReturnType() + " expectedValue = new <Należy wpisać nazwę klasy implementującej interfejs, której instancję zwraca metoda>();" + "\\\\Należy zainicjować obiekt w taki sposób, aby odpowiadał przewidywanemu zwróconemu obiektowi.";
                testCaseBody += "\n" + getReturnType() + " resultValue = " + methodCall + ";";
                testCaseBody += "\n" + "System.out.println(\"Testy błędnego wejścia dla metody: " + getName() + ", parametr " + parameter.getName() + " jako niepoprawna wartość\");";
                testCaseBody += "\n" + "assertNotNull(resultValue);";
                testCaseBody += "\n" + "assertEquals(expectedValue, resultValue);";
            } else {
                testCaseBody += "\n" + getReturnType() + " expectedValue = <oczekiwana wartość>;" + "\\\\Należy przypisać wartość oczekiwaną jako wynik zwrócony przez metodę.";
                testCaseBody += "\n" + getReturnType() + " resultValue = " + methodCall + ";";
                testCaseBody += "\n" + "System.out.println(\"Testy błędnego wejścia dla metody: " + getName() + ", parametr " + parameter.getName() + " jako niepoprawna wartość\");";
                testCaseBody += "\n" + "assertEquals(expectedValue, resultValue);";
            }
        } else {
            testCase.addCommandLine("Metoda nie zwraca żadnego obiektu,");
            testCase.addCommandLine("należy przygotować analizę atrybutów klasy zmienianych przez metodę.");
        }
        testCaseBody += "\n}";
        testCase.setBodyText(testCaseBody);
        return testCase;
    }
    
    public void generateTestCode()
    {
        
        for(UnitaryTestMethod t : tests)
        {
            testCode += "\n\n";
            testCode += t.getComment() + "\n";
            testCode += "\n@test";
            testCode += "\npublic void " + t.getName() + "()\n";
            testCode += t.getBodyText();
        }
        
    }
    
    //###############################Metody tymczasowe#######################################
    public void whoAmI()
    {
        System.out.println("Metoda");
        System.out.println("Nazwa: " + getName());
        System.out.println("Zwracany typ: " + getReturnType());
        System.out.print("Parametry: ");
        boolean first = true;
        for(Parametr a : parameters)
        {
            if(first)
            {
                System.out.print(a.getType() +  " " + a.getName());
                first = false;
            } else {
                System.out.print(", " + a.getType() +  " " + a.getName());
            }
            
        }
        System.out.println("");
        System.out.println("Ciało: ");
        System.out.println(getMethodBody());
    }
}

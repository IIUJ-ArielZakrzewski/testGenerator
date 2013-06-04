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
public class KlasaAbstrakcyjna extends Klasa{
    
    //###############################ATRYBUTY KLASY DO ANALIZY###############################
    List<Klasa> implementations;
    List<Metoda> abstractMethods;
    
    //###############################KONSTRUKTORY###############################
    public KlasaAbstrakcyjna(String nazwa, TestFile ref)
    {
        super(nazwa, ref);
        implementations = new ArrayList<>();
        abstractMethods = new ArrayList<>();
    }
    
    public KlasaAbstrakcyjna(String nazwa, List atrybuty, TestFile ref)
    {
        super(nazwa, atrybuty, ref);
        implementations = new ArrayList<>();
        abstractMethods = new ArrayList<>();
    }
    
    public KlasaAbstrakcyjna(String nazwa, List atrybuty, List metody, TestFile ref)
    {
        super(nazwa, atrybuty, metody, ref);
        implementations = new ArrayList<>();
        abstractMethods = new ArrayList<>();
    }
    
    
    //###############################METODY BEAN PROGRAMMING###############################
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
            if(a.getName().equals(nazwa))
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
    
    public void addAbstractMethod(Metoda metoda)
    {
        abstractMethods.add(metoda);
    }
    
    //###############################METODY ANALIZY KODU###############################
    @Override
    public void analyzeCode() throws Exception
    {
        Scanner in = new Scanner(classBody);
        String line;
        while(in.hasNextLine())
        {
            line = in.nextLine().trim();
            if(line.trim().isEmpty())
            {
                
            } else if(line.matches("^.*abstract.+\\(.*\\);$")) //Deklaracja metody abstrakcyjnej
            {
                String attr = line.trim();
                attr = attr.replace(";", "");
                attr = attr.replace("abstract ", "");
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
                    System.out.println("Błąd danych przy deklaracji metody abstrakcyjnej");
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
                    m = new Metoda(methodName, returnType, parameters, this);
                } else {
                    m = new Metoda(methodName, returnType, this);
                }
                abstractMethods.add(m);
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
                    System.out.println("Błąd danych przy deklaracji atrybutu w klasie abstrakcyjnej");
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
                    m = new Metoda(methodName, returnType, parameters, this);
                } else {
                    m = new Metoda(methodName, returnType, this);
                }
                Stack<String> stos = new Stack<>();
                if(containesClamre)
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
                String body = "{\n";
                while(!stos.isEmpty())
                {
                    line = in.nextLine();
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
                    line = in.nextLine();
                    while(!line.contains("{"))
                    {
                        line = in.nextLine();
                    }
                    stos.push("{");
                }
                String body = "{\n";
                while(!stos.isEmpty())
                {
                    line = in.nextLine();
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
                    line = in.nextLine();
                }
            } else if(line.trim().equals("{") || line.trim().contains("}"))
            {
                
            } else {
                System.out.println("Błąd danych");
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
    
    //###############################METODY GENERACJI TESTÓW###############################
    
    @Override
    public void generateMethodCommonTests() throws Exception
    {
        for(Metoda m : methods)
        {
            m.generateUnitaryMethodCommonTests();
            m.generateTestCode();
        }
    }
    
    
}

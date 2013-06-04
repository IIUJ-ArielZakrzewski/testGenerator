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
public class UnitaryTestMethod {
    String name;
    List<Parametr> parameters;
    String bodyText;
    String comment;

    //###############################KONSTRUKTORY###############################
    public UnitaryTestMethod(String nazwa)
    {
        name = nazwa;
        parameters = new ArrayList<>();
        bodyText = "";
    }
    
    public UnitaryTestMethod(String nazwa, List parametry)
    {
        name = nazwa;
        parameters = parametry;
        bodyText = "";
    }
    
    public UnitaryTestMethod(String nazwa, String methodText)
    {
        name = nazwa;
        bodyText = methodText;
        parameters = new ArrayList<>();
    }
    
    public UnitaryTestMethod(String nazwa, String methodText, List parametry)
    {
        name = nazwa;
        bodyText = methodText;
        parameters = parametry;
    }
    
    //###############################METODY BEAN PROGRAMMING###############################
    public String getName()
    {
        return name;
    }
    
    public void setName(String newName)
    {
        name = newName;
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
    
    public String getBodyText()
    {
        Scanner in = new Scanner(bodyText);
        String result = "";
        while(in.hasNextLine())
        {
            String arg = in.nextLine();
            switch (arg.trim()) {
                case "{":
                    result += arg;
                    break;
                case "}":
                    result += "\n" + arg;
                    break;
                default:
                    result += "\n    " + arg;
                    break;
            }
            
        }
        return result;
    }
    
    public void setBodyText(String newText)
    {
        bodyText = newText;
    }
    
    public void setComment(String newComment)
    {
        comment = newComment;
    }
    
    public String getComment()
    {
        Scanner in = new Scanner(comment);
        String result = "";
        result += "/*" + in.nextLine();
        while(in.hasNextLine())
        {
            result += "\n*" + in.nextLine();
        }
        result += "\n*/";
        return result;
    }
    
    public void addCommandLine(String line)
    {
        comment += "\n" + line;
    }
}

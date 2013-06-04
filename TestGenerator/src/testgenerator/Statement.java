/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testgenerator;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Ariel
 */
public abstract class Statement {
    public Statement nextStatement;
    public String bodyStatement;
    
    public Statement(){}
    public Statement(String bd)
    {
        bodyStatement = bd;
    }
    
    public void setBodyStatement(String bd)
    {
        bodyStatement = bd;
    }
    
    public String getBodyStatement()
    {
        return bodyStatement;
    }
    
    public abstract void analyzeCode();
    
    public void analyzeSubCode(String subCodeBody, Statement startingStatement)
    {
        Scanner in = new Scanner(subCodeBody);
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
                addStatement(startingStatement, forStatement);
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
                addStatement(startingStatement, forStatement);
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
                addStatement(startingStatement, ifStatement);
            } else if(line.matches(".*else.*$"))//switch
            {
                if(getLastStatement(startingStatement) instanceof If)
                {
                    If stat = (If)getLastStatement(startingStatement);
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
                addStatement(startingStatement, s);
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
                addStatement(startingStatement, whileStatement);
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
                addStatement(startingStatement, whileStatement);
                
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
                
                if(!(getLastStatement(startingStatement) instanceof TryCatch))
                {
                    System.out.println("Bład parsowania danych");
                    return;
                }
                TryCatch tc = (TryCatch) getLastStatement(startingStatement);
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
                finish(startingStatement);
            } else {
                addStatement(startingStatement, new Command(line));
            }
        }
        Statement s = startingStatement;
        while(s!=null)
        {
            s.analyzeCode();
            s = s.nextStatement;
        }
    }
    
    public void addStatement(Statement destinationStatement, Statement addedStatement)
    {
        Statement temp = destinationStatement;
        while(temp.nextStatement != null)
        {
            temp = temp.nextStatement;
        }
        if(temp instanceof EndStatement)
        {
            return;
        }
        temp.nextStatement = addedStatement;
    }
    
    public void finish(Statement destinationStatement)
    {
        addStatement(destinationStatement, new EndStatement());
    }
    
    public Statement getLastStatement(Statement sourceStatement)
    {
        Statement temp = sourceStatement;
        while(temp.nextStatement != null)
        {
            temp = temp.nextStatement;
        }
        
        return temp;
    }
    
}

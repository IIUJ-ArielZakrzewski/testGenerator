/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testgenerator;

/**
 *
 * @author Ariel
 */
public class If extends Statement{
    String condition;
    Statement ifBody;
    Statement elseBody;
    String ifSource;
    String elseSource;
    
    public If(String cond)
    {
        condition = cond;
        ifBody = new StartStatement();
        elseBody = null;
        ifSource = "";
        elseSource = "";
    }
    
    public void addIfStatement(Statement s)
    {
        Statement stearing = ifBody;
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
    
    public void finishIf()
    {
        addIfStatement(new EndStatement());
    }
    
    public void createElse()
    {
        elseBody = new StartStatement();
    }
    
    public void addElseStatement(Statement s)
    {
        Statement stearing = elseBody;
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
    
    public void finishElse()
    {
        addElseStatement(new EndStatement());
    }
    
    public void setIfSource(String source)
    {
        ifSource = source;
    }
    
    public String getIfSource()
    {
        return ifSource;
    }
    
    public void setElseSource(String source)
    {
        elseSource = source;
        createElse();
    }
    
    public String getElseSource()
    {
        return elseSource;
    }

    @Override
    public void analyzeCode() {
        analyzeSubCode(ifSource, ifBody);
        if(!elseSource.isEmpty())
        {
            if(elseBody == null)
            {
                createElse();
            }
            analyzeSubCode(elseSource, elseBody);
        }
    }
}

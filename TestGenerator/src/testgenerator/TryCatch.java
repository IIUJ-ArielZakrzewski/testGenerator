/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testgenerator;

/**
 *
 * @author Ariel
 */
public class TryCatch extends Statement{
    Statement tryBody;
    Statement catchBody;
    String catchException;
    Statement finallyBody;
    String bodyTry;
    String bodyCatch;
    String bodyFinally;
    
    public TryCatch()
    {
        tryBody = new StartStatement();
        catchBody = new StartStatement();
        finallyBody = new StartStatement();
        bodyTry = "";
        bodyCatch = "";
        bodyFinally = "";
    }
    
    public void setCatchException(String ex)
    {
        catchException = ex;
    }
    
    public void addTryStatement(Statement s)
    {
        Statement stearing = tryBody;
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
    
    public void finishTry()
    {
        addTryStatement(new EndStatement());
    }
    
    public void addCatchStatement(Statement s)
    {
        Statement stearing = catchBody;
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
    
    public void finishCatch()
    {
        addCatchStatement(new EndStatement());
    }
    
    public void addFinallyStatement(Statement s)
    {
        Statement stearing = finallyBody;
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
    
    public void finishFinally()
    {
        addFinallyStatement(new EndStatement());
    }
    
    public void setBodyTry(String bdTry)
    {
        bodyTry = bdTry;
    }
    
    public String getBodyTry()
    {
        return bodyTry;
    }
    
    public void setBodyCatch(String bdCatch)
    {
        bodyCatch = bdCatch;
    }
    
    public String getBodyCatch()
    {
        return bodyCatch;
    }
    
    public void setBodyFinally(String bdFinally)
    {
        bodyFinally = bdFinally;
    }
    
    public String getBodyFinally()
    {
        return bodyFinally;
    }

    @Override
    public void analyzeCode() {
        analyzeSubCode(bodyTry, tryBody);
        analyzeSubCode(bodyCatch, catchBody);
        if(!bodyFinally.isEmpty())
        {
            analyzeSubCode(bodyFinally, finallyBody);
        }
    }
    
}

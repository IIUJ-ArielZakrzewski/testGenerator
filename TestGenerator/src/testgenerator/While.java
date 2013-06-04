/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testgenerator;

/**
 *
 * @author Ariel
 */
public class While extends Statement {
    String condition;
    Statement whileBody;
    boolean doWhile;
    
    public While()
    {
        super();
        condition = "";
        whileBody = new StartStatement();
        doWhile = false;
    }
    
    public While(String cond, String bodyWhile)
    {
        super(bodyWhile);
        condition = cond;
        whileBody = new StartStatement();
        doWhile = false;
    }
    
    public void addWhileStatement(Statement s)
    {
        Statement stearing = whileBody;
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
    
    public void finishWhile()
    {
        addWhileStatement(new EndStatement());
    }
    
    public void setCondition(String cond)
    {
        condition = cond;
    }
    
    public String getCondition()
    {
        return condition;
    }
    
    public void setIfDoWhile(boolean flag)
    {
        doWhile = flag;
    }
    
    public boolean getIfDoWhile()
    {
        return doWhile;
    }

    @Override
    public void analyzeCode() {
        analyzeSubCode(bodyStatement, whileBody);
    }
}

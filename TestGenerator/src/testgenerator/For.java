/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testgenerator;

/**
 *
 * @author Ariel
 */
public class For extends Statement{
    
    boolean foreach = false;
    String iterator;
    String type;
    String condition;
    String incrementCommand;
    String collectionName;
    Statement body;
    
    public For(String typ, String iter, String cond, String command, String bodystat)
    {
        super(bodystat);
        iterator = iter;
        type = typ;
        condition = cond;
        incrementCommand = command;
        body = new StartStatement();
        collectionName = "";
    }
    
    public For(String iter, String cond, String command, String bodystat)
    {
        super(bodystat);
        iterator = iter;
        type = "";
        condition = cond;
        incrementCommand = command;
        body = new StartStatement();
        collectionName = "";
    }
    
    public For(String typ, String iter, String bodystat)
    {
        super(bodystat);
        iterator = iter;
        type = typ;
        condition = "";
        incrementCommand = "";
        body = new StartStatement();
        collectionName = "";
    }
    
    public void addStatement(Statement s)
    {
        Statement stearing = body;
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
    
    public void finishFor()
    {
        addStatement(new EndStatement());
    }
    
    public void setIfForEach(boolean arg)
    {
        foreach = arg;
    }
    
    public void setType(String typ)
    {
        type = typ;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setCollectionName(String newName)
    {
        collectionName = newName;
    }
    
    public String getCollectionName()
    {
        return collectionName;
    }

    @Override
    public void analyzeCode() {
        analyzeSubCode(bodyStatement, body);
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testgenerator;

/**
 *
 * @author Ariel
 */
public class SwitchCase extends Statement{
    String conditionValue;
    Statement caseBody;
    boolean containsBreak;
    boolean isDefault;
    
    public SwitchCase(String cond)
    {
        conditionValue = cond;
        caseBody = new StartStatement();
        containsBreak = false;
        isDefault = false;
    }
    
    public SwitchCase()
    {
        conditionValue = "";
        caseBody = new StartStatement();
        containsBreak = false;
        isDefault = false;
    }
    
    public void addCaseStatement(Statement s)
    {
        Statement stearing = caseBody;
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
    
    public void finishCase()
    {
        addCaseStatement(new EndStatement());
    }
    
    public void SetContainsBreak(boolean flag)
    {
        containsBreak = flag;
    }
    
    public Boolean GetContainsBreak()
    {
        return containsBreak;
    }
    
    public void setConditionValue(String newValue)
    {
        conditionValue = newValue;
    }
    
    public String getConditionValue()
    {
        return conditionValue;
    }
    
    public void setIsDefault(boolean flag)
    {
        isDefault = flag;
    }
    
    public boolean getIsDefault()
    {
        return isDefault;
    }

    @Override
    public void analyzeCode() {
        analyzeSubCode(bodyStatement, caseBody);
    }
    
}

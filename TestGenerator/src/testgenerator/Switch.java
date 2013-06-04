/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testgenerator;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class Switch extends Statement{
    List<SwitchCase> cases;
    String variable;
    
    public Switch(String var)
    {
        variable = var;
        cases = new LinkedList<>();
    }
    
    public void addCase(SwitchCase cas)
    {
        cases.add(cas);
    }

    @Override
    public void analyzeCode() {
        for(SwitchCase s : cases)
        {
            s.analyzeCode();
        }
    }
}

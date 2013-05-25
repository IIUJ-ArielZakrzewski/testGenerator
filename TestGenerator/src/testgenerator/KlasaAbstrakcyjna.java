/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testgenerator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class KlasaAbstrakcyjna extends Klasa{
    
    //###############################ATRYBUTY KLASY DO ANALIZY###############################
    List<Klasa> implementations;
    
    //###############################KONSTRUKTORY###############################
    public KlasaAbstrakcyjna(String nazwa)
    {
        super(nazwa);
        implementations = new ArrayList<>();
    }
    
    public KlasaAbstrakcyjna(String nazwa, List atrybuty)
    {
        super(nazwa, atrybuty);
        implementations = new ArrayList<>();
    }
    
    public KlasaAbstrakcyjna(String nazwa, List atrybuty, List metody)
    {
        super(nazwa, atrybuty, metody);
        implementations = new ArrayList<>();
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
            if(a.equals(nazwa))
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
    
}

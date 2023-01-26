package dace.service;
import dace.unit.Unit;


public class UnitService {


    public Unit getUnitFromString(Unit unit, String str) {
        str = str.toUpperCase().replaceAll("[^A-Z]", "");
        for (Unit u : unit.getAllUnits()) {
            if (str.equals(u.toString()))
                return u;
        }
        throw new RuntimeException("Unable to convert "
                                +  unit.getClass().getSimpleName() 
                                +  " from the input: "
                                +   str);
    }
    
}

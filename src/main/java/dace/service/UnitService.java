package dace.service;
import java.lang.reflect.InvocationTargetException;
import dace.model.Unit;
import dace.model.UnitType;


public class UnitService {

    public Unit getUnitFromString(UnitType unitType, String str) {
        Unit unit = convertUnitType(unitType);
        return getUnitFromString(unit, str);
    }



    private Unit convertUnitType(UnitType unitType) {
        try {
            String unitParam = unitType.toString().substring(0, 1).toUpperCase() 
                             + unitType.toString().substring(1).toLowerCase();
            Class<?> classUnit = Class.forName("dace.unit." + unitParam);
            Unit unit = (Unit) classUnit.getConstructor().newInstance();
            return unit;
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | 
                     NoSuchMethodException | SecurityException | ClassNotFoundException e) {
                return null;
            }
    }



    private Unit getUnitFromString(Unit unit, String str) {
        str = str.toUpperCase().replaceAll("[^A-Z]", "");
        for (Unit u : unit.getAllUnits()) {
            if (str.equals(u.toString()))
                return u;
        }
        throw new RuntimeException("Unable to convert " + unit.getClass().getSimpleName() 
                                 + " from the input: " + str);
    }
    
}

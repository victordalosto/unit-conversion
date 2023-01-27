package dace;

import org.junit.jupiter.api.Test;

import dace.model.Unit;
import dace.model.UnitType;
import dace.service.UnitService;

public class Teste {


    @Test
    void testa() {
        UnitService unitService = new UnitService();
        Unit unit = unitService.getUnitFromString(UnitType.FORCE, "N");
        System.out.println(unit);
    }
    
}

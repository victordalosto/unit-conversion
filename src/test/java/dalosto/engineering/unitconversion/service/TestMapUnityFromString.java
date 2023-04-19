package dalosto.engineering.unitconversion.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.exception.ParameterException;
import dalosto.engineering.unitconversion.exception.UnitException;
import dalosto.engineering.unitconversion.units.Area;
import dalosto.engineering.unitconversion.units.Length;


@SpringBootTest
public class TestMapUnityFromString {
    
    
    @Autowired
    private MapUnitTypeService mapUnitTypeService;


    @Test
    void serviceShouldHandleNullString() {
        assertThrows(ParameterException.class, () -> mapUnitTypeService.getUnitTypeFromString(null, null));
        assertThrows(ParameterException.class, () -> mapUnitTypeService.getUnitTypeFromString("Text", null));
        assertThrows(ParameterException.class, () -> mapUnitTypeService.getUnitTypeFromString(null, new Length()));
    }


    @Test
    void shouldThrowExceptionWhenInvalidParamIsGiven() {
        assertThrows(UnitException.class, () -> mapUnitTypeService.getUnitTypeFromString("M", new Area()));
        assertThrows(UnitException.class, () -> mapUnitTypeService.getUnitTypeFromString("M_2", new Length()));
        assertThrows(UnitException.class, () -> mapUnitTypeService.getUnitTypeFromString("M_3", new Length()));
        assertThrows(UnitException.class, () -> mapUnitTypeService.getUnitTypeFromString("invalid param", new Area()));
    }

}

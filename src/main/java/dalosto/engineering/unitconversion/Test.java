package dalosto.engineering.unitconversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.interfaces.UnitFormulas;
import dalosto.engineering.unitconversion.units.Length;
import jakarta.annotation.PostConstruct;


@Component
public class Test {

    @Autowired
    @Qualifier("LengthFormula")
    UnitFormulas formulas;


    @PostConstruct
    void main() {
        Double value = 25.4;
        UnitType cm = Length.Types.CM;
        Unit unit = new Unit(value, cm);
        Unit buildUnitIntoAnotherType = formulas.buildUnitIntoAnotherType(unit, Length.Types.MM);
        System.out.println(buildUnitIntoAnotherType);
        Unit b = formulas.buildUnitIntoAnotherType(unit, Length.Types.IN);
        System.out.println(b);
        //  Unit(value=10.0, unitType=MM);
    }

}

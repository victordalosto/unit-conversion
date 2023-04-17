package dalosto.engineering.unitconversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;
import dalosto.engineering.unitconversion.units.Length;
import jakarta.annotation.PostConstruct;


@Component
public class Test {

    @Autowired
    @Qualifier("LengthFormula")
    UnitFormula formulas;


    @PostConstruct
    void main() {
        Double value = 25.4;
        UnitType cm = Length.Types.CM;
        Unit unit = new Unit(value, cm);
        formulas.buildUnitToSI(unit);  //  Unit(value=0.254, unitType=M);
        //  Unit(value=10.0, unitType=MM);
    }

}

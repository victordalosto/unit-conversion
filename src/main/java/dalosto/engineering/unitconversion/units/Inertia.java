package dalosto.engineering.unitconversion.units;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;


@Component("inertia")
public class Inertia extends TemplateUnitFormulas {

    public static enum Types implements UnitType {

        M4(1.0),
        DM4(Math.pow(10.0, -4)),
        CM4(Math.pow(10.0, -8)),
        MM4(Math.pow(10.0, -12)),
        HM4(Math.pow(10.0, 8)),
        KM4(Math.pow(10.0, 12)),
        UM4(Math.pow(10.0, -24)),
        IN4(0.0000004162314256),
        FT4(0.0086309748412416),
        YD4(0.6991089621405696);
    

        protected final double factorOfEquivalenceToSI;
        private Types(double factorOfEquivalenceToSI) {
            this.factorOfEquivalenceToSI = factorOfEquivalenceToSI;
        }


        @Override
        public UnitType getSITypeOfThisCategory() {
            return M4;
        }


        @Override
        public List<UnitType> getAllUnitTypesOfThisCategory() {
            return new ArrayList<>(Arrays.asList(Types.values()));
        }


    }
    

    @Override
    public Unit convertUnitIntoAnotherType(Unit unit, UnitType anotherType) {
        double inputConversion = ((Types) unit.getType()).factorOfEquivalenceToSI;
        double ouputConversion = ((Types) anotherType).factorOfEquivalenceToSI;
        double value = unit.getValue() * (inputConversion / ouputConversion);
        return new Unit(value, anotherType);
    }


    @Override
    public UnitType getSITypeOfThisCategory() {
        return Types.M4.getSITypeOfThisCategory();
    }


    @Override
    public List<UnitType> getAllUnitTypesOfThisCategory() {
        return Types.M4.getAllUnitTypesOfThisCategory();
    }


}

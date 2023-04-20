package dalosto.engineering.unitconversion.units;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;


@Component("Time")
public class Time extends TemplateUnitFormulas {

    public enum Types implements UnitType {

        S(1.0),
        MS(Math.pow(10.0, -3)),
        US(Math.pow(10.0, -6)),
        MIN(60.0),
        H(3600.0),
        DAY(86400.0),
        WEEK(604800.0),
        MONTH(86400.0 * (365.0/12.0)),
        MONTH_30(86400.0 * 30.0),
        MONTH_31(86400.0 * 31.0),
        YEAR(31536000.0);
    

        protected final double factorOfEquivalenceToSI;
        private Types(double factorOfEquivalenceToSI) {
            this.factorOfEquivalenceToSI = factorOfEquivalenceToSI;
        }


        @Override
        public UnitType getSITypeOfThisCategory() {
            return S;
        }


        @Override
        public Set<UnitType> getAllUnitTypesOfThisCategory() {
            return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(Types.values())));
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
        return Types.S.getSITypeOfThisCategory();
    }

    
    @Override
    public Set<UnitType> getAllUnitTypesOfThisCategory() {
        return Types.S.getAllUnitTypesOfThisCategory();
    }


}

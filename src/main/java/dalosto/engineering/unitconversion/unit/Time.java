package dalosto.engineering.unitconversion.unit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.formula.TemplateUnitFormulas;


@Component("time")
public final class Time extends TemplateUnitFormulas {

    public static enum Types implements UnitType {

        S(1.0),
        MS(Math.pow(10.0, -3)),
        US(Math.pow(10.0, -6)),
        MIN(60.0),
        H(3600.0),
        DAY(86400.0),
        WEEK(604800.0),
        MONTH(86400.0 * (365.0/12.0)),
        MONTH30(86400.0 * 30.0),
        MONTH31(86400.0 * 31.0),
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
        return Types.S.getSITypeOfThisCategory();
    }

    
    @Override
    public List<UnitType> getAllUnitTypesOfThisCategory() {
        return Types.S.getAllUnitTypesOfThisCategory();
    }


}

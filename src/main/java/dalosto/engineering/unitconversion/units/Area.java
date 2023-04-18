package dalosto.engineering.unitconversion.units;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;


@Component("Area")
public class Area extends TemplateUnitFormulas {

    public enum Types implements UnitType {

        M_2(1.0),
        DM_2(0.01),
        CM_2(0.0001),
        MM_2(0.000001),
        HM_2(10000.0),
        KM_2(1000000.0),
        UM_2(Math.pow(10.0, -12)),
        IN_2(0.00064516),
        FT_2(0.09290304),
        YD_2(0.83612736);
    

        protected final double factorOfEquivalenceToSI;
        private Types(double factorOfEquivalenceToSI) {
            this.factorOfEquivalenceToSI = factorOfEquivalenceToSI;
        }


        @Override
        public UnitType getSITypeOfThisCategory() {
            return M_2;
        }


        @Override
        public Set<UnitType> getAllUnitTypesOfThisCategory() {
            return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(Types.values())));
        }


    }
    

    @Override
    public Unit convertUnitIntoAnotherType(Unit unit, UnitType anotherType) {
        double inputConversion = ((Types) unit.getUnitType()).factorOfEquivalenceToSI;
        double ouputConversion = ((Types) anotherType).factorOfEquivalenceToSI;
        double value = unit.getValue() * (inputConversion / ouputConversion);
        return new Unit(value, anotherType);
    }


    @Override
    public UnitType getSITypeOfThisCategory() {
        return Types.M_2.getSITypeOfThisCategory();
    }

    
    @Override
    public Set<UnitType> getAllUnitTypesOfThisCategory() {
        return Types.M_2.getAllUnitTypesOfThisCategory();
    }


}

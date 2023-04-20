package dalosto.engineering.unitconversion.units;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;


@Component("inertia")
public class Inertia extends TemplateUnitFormulas {

    public enum Types implements UnitType {

        M_4(1.0),
        DM_4(Math.pow(10.0, -4)),
        CM_4(Math.pow(10.0, -8)),
        MM_4(Math.pow(10.0, -12)),
        HM_4(Math.pow(10.0, 8)),
        KM_4(Math.pow(10.0, 12)),
        UM_4(Math.pow(10.0, -24)),
        IN_4(0.0000004162314256),
        FT_4(0.0086309748412416),
        YD_4(0.6991089621405696);
    

        protected final double factorOfEquivalenceToSI;
        private Types(double factorOfEquivalenceToSI) {
            this.factorOfEquivalenceToSI = factorOfEquivalenceToSI;
        }


        @Override
        public UnitType getSITypeOfThisCategory() {
            return M_4;
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
        return Types.M_4.getSITypeOfThisCategory();
    }


    @Override
    public Set<UnitType> getAllUnitTypesOfThisCategory() {
        return Types.M_4.getAllUnitTypesOfThisCategory();
    }


}

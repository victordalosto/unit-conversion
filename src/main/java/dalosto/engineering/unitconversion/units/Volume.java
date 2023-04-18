package dalosto.engineering.unitconversion.units;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;


@Component("VolumeFormula")
public class Volume extends TemplateUnitFormulas {

    public enum Types implements UnitType {

        M_3(1.0),
        DM_3(0.001),
        CM_3(Math.pow(10.0, -6)),
        MM_3(Math.pow(10.0, -9)),
        HM_3(Math.pow(10.0, 6)),
        KM_3(Math.pow(10.0, 9)),
        UM_3(Math.pow(10.0, -18)),
        IN_3(0.000016387064),
        FT_3(0.028316846592),
        YD_3(0.764554857984),
        ML(0.000001),
        L(0.001);
    

        protected final double factorOfEquivalenceToSI;
        private Types(double factorOfEquivalenceToSI) {
            this.factorOfEquivalenceToSI = factorOfEquivalenceToSI;
        }


        @Override
        public UnitType getSITypeOfThisCategory() {
            return M_3;
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
        return Types.M_3.getSITypeOfThisCategory();
    }


    @Override
    public Set<UnitType> getAllUnitTypesOfThisCategory() {
        return Types.M_3.getAllUnitTypesOfThisCategory();
    }


}

package dalosto.engineering.unitconversion.units;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;


@Component("LengthFormula")
public class Length extends TemplateUnitFormulas {

    public enum Types implements UnitType {

        M(1.0), 
        DM(0.1), 
        CM(0.01), 
        MM(0.001), 
        HM(100.0), 
        KM(1000.0), 
        UM(Math.pow(10.0, -6)), 
        IN(0.0254), 
        FT(0.3048),
        YD(0.9144);
    

        protected final double factorOfEquivalenceToSI;
        private Types(double factorOfEquivalenceToSI) {
            this.factorOfEquivalenceToSI = factorOfEquivalenceToSI;
        }


        @Override
        public UnitType getSITypeOfThisCategory() {
            return M;
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
        return Types.M.getSITypeOfThisCategory();
    }


    @Override
    public Set<UnitType> getAllUnitTypesOfThisCategory() {
        return Types.M.getAllUnitTypesOfThisCategory();
    }


}

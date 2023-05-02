package dalosto.engineering.unitconversion.units;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;

@Component("force")
public class Force extends TemplateUnitFormulas {

    private static final double GRAVITY = 9.8066500286389;

    public static enum Types implements UnitType {

        N(1.0, false),
        KN(1000.0, false),
        MN(1000.0*1000.0, false),
        GN(1000.0*1000.0*1000.0, false),
        TN(1000000000000.0, false),
        LB(4.4482216282509, false),
        POUND(4.4482216282509, false),
        KIP(4448.2216282509, false),
        G(1.0/1000, true),
        KG(1.0, true),
        T(1000.0, true);
    

        protected final double factorOfEquivalenceToSI;
        public final boolean dependesOfGravityOnConversion;

        private Types(double factorOfEquivalenceToSI, boolean usesGravity) {
            this.factorOfEquivalenceToSI = factorOfEquivalenceToSI;
            this.dependesOfGravityOnConversion = usesGravity;
        }


        @Override
        public UnitType getSITypeOfThisCategory() {
            return N;
        }


        @Override
        public List<UnitType> getAllUnitTypesOfThisCategory() {
            return new ArrayList<>(Arrays.asList(Types.values()));
        }


    }
    

    @Override
    public Unit convertUnitIntoAnotherType(Unit unit, UnitType anotherType) {
        double inputConversion = ((Types) unit.getType()).factorOfEquivalenceToSI;
        if (((Types) unit.getType()).dependesOfGravityOnConversion) {
            inputConversion *= GRAVITY;
        }
        double ouputConversion = ((Types) anotherType).factorOfEquivalenceToSI;
        if (((Types) anotherType).dependesOfGravityOnConversion) {
            ouputConversion *= GRAVITY;
        }
        double value = unit.getValue() * (inputConversion / ouputConversion);
        return new Unit(value, anotherType);
    }


    @Override
    public UnitType getSITypeOfThisCategory() {
        return Types.N.getSITypeOfThisCategory();
    }


    @Override
    public List<UnitType> getAllUnitTypesOfThisCategory() {
        return Types.N.getAllUnitTypesOfThisCategory();
    }



}
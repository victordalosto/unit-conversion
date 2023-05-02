package dalosto.engineering.unitconversion.units;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;


@Component("area")
public class Area extends TemplateUnitFormulas {

    public static enum Types implements UnitType {

        M2(1.0),
        DM2(0.01),
        CM2(0.0001),
        MM2(0.000001),
        HM2(10000.0),
        KM2(1000000.0),
        UM2(Math.pow(10.0, -12)),
        IN2(0.00064516),
        FT2(0.09290304),
        YD2(0.83612736);
    

        protected final double factorOfEquivalenceToSI;
        private Types(double factorOfEquivalenceToSI) {
            this.factorOfEquivalenceToSI = factorOfEquivalenceToSI;
        }


        @Override
        public UnitType getSITypeOfThisCategory() {
            return M2;
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
        return Types.M2.getSITypeOfThisCategory();
    }

    
    @Override
    public List<UnitType> getAllUnitTypesOfThisCategory() {
        return Types.M2.getAllUnitTypesOfThisCategory();
    }


}

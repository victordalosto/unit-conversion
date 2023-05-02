package dalosto.engineering.unitconversion.units;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;


@Component("volume")
public class Volume extends TemplateUnitFormulas {

    public static enum Types implements UnitType {

        M3(1.0),
        DM3(0.001),
        CM3(Math.pow(10.0, -6)),
        MM3(Math.pow(10.0, -9)),
        HM3(Math.pow(10.0, 6)),
        KM3(Math.pow(10.0, 9)),
        UM3(Math.pow(10.0, -18)),
        IN3(0.000016387064),
        FT3(0.028316846592),
        YD3(0.764554857984),
        ML(0.000001),
        L(0.001);
    

        protected final double factorOfEquivalenceToSI;
        private Types(double factorOfEquivalenceToSI) {
            this.factorOfEquivalenceToSI = factorOfEquivalenceToSI;
        }


        @Override
        public UnitType getSITypeOfThisCategory() {
            return M3;
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
        return Types.M3.getSITypeOfThisCategory();
    }


    @Override
    public List<UnitType> getAllUnitTypesOfThisCategory() {
        return Types.M3.getAllUnitTypesOfThisCategory();
    }


}

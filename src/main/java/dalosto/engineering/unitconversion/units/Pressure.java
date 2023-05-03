package dalosto.engineering.unitconversion.units;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.domain.UnitTypeDuo;
import dalosto.engineering.unitconversion.exception.UnitException;


@Component("pressure")
public class Pressure extends TemplateUnitFormulas {

    public static class Types extends UnitTypeDuo {

        private static List<UnitType> types = new ArrayList<>();
        private String ALTERNATIVE_NAME = null;

        static {
            types.add(Pressure.factory("PA", Force.Types.N, Area.Types.M2));
            types.add(Pressure.factory("KPA", Force.Types.KN, Area.Types.M2));
            types.add(Pressure.factory("MPA", Force.Types.MN, Area.Types.M2));
            types.add(Pressure.factory("PSI", Force.Types.POUND, Area.Types.IN2));
            types.add(Pressure.factory("KSI", Force.Types.KIP, Area.Types.IN2));
            for (UnitType force : new Force().getAllUnitTypesOfThisCategory()) {
                for (UnitType area : new Area().getAllUnitTypesOfThisCategory()) {
                    types.add(factory(force, area));
                }
            }
        }


        @Override
        public UnitType getSITypeOfThisCategory() {
            return factory(new Force().getSITypeOfThisCategory(), new Area().getSITypeOfThisCategory());
        }


        @Override
        public List<UnitType> getAllUnitTypesOfThisCategory() {
            return new ArrayList<>(types);
        }


        @Override
        public String toString() {
            if (ALTERNATIVE_NAME != null) {
                return ALTERNATIVE_NAME;
            }
            return super.getPrincipal() + "/" + super.getSecondary();
        }

    }


    public static UnitType factory(UnitType force, UnitType area) {
        if (force == null || area == null) {
            throw new UnitException("Force and Area must not be null.");
        }
        if (!(force instanceof Force.Types) || !(area instanceof Area.Types)) {
            throw new UnitException("Paremeters for constructor doesn't match");
        }
        Types t = new Types();
        t.setPrincipal(force);
        t.setSecondary(area);
        return t;
    }


    public static UnitType factory(String alt, UnitType force, UnitType area) {
        UnitType t = factory(force, area);
        ((Types) t).ALTERNATIVE_NAME = alt;
        return t;
    }


    @Override
    public UnitType getSITypeOfThisCategory() {
        return new Types().getSITypeOfThisCategory();
    }


    @Override
    public List<UnitType> getAllUnitTypesOfThisCategory() {
        return new Types().getAllUnitTypesOfThisCategory();
    }


    @Override
    public Unit convertUnitIntoAnotherType(Unit unit, UnitType anotherType) {
        double value = unit.getValue();
        UnitType principal = ((Types) unit.getType()).getPrincipal();
        UnitType secondary = ((Types) unit.getType()).getSecondary();
        UnitType otherPri = ((Types) anotherType).getPrincipal();
        UnitType otherSec = ((Types) anotherType).getSecondary();
        Unit force = new Force().buildUnitIntoAnotherType(new Unit(value, principal), otherPri);
        Unit area = new Area().buildUnitIntoAnotherType(new Unit(force.getValue(), otherSec), secondary);
        return new Unit(area.getValue(), anotherType);
    }

}

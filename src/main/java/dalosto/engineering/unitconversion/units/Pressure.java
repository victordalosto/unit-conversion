package dalosto.engineering.unitconversion.units;
import java.util.ArrayList;
import java.util.Collections;
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
        static {
            for (UnitType force : new Force().getAllUnitTypesOfThisCategory()) {
                for (UnitType area : new Area().getAllUnitTypesOfThisCategory()) {
                    Types.types.add(factory(force, area));
                }
            }
        }


        @Override
        public UnitType getSITypeOfThisCategory() {
            return factory(new Force().getSITypeOfThisCategory(), new Area().getSITypeOfThisCategory());
        }


        @Override
        public List<UnitType> getAllUnitTypesOfThisCategory() {
            return Collections.unmodifiableList(types);
        }


        @Override
        public String toString() {
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

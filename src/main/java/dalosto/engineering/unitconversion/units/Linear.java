package dalosto.engineering.unitconversion.units;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.domain.UnitTypeDuo;
import dalosto.engineering.unitconversion.exception.UnitException;


@Component("linear")
public class Linear extends TemplateUnitFormulas {

    public static class Types extends UnitTypeDuo {

        private static List<UnitType> types = new ArrayList<>();
        static {
            for (UnitType force : new Force().getAllUnitTypesOfThisCategory()) {
                for (UnitType length : new Length().getAllUnitTypesOfThisCategory()) {
                    Types.types.add(factory(force, length));
                }
            }
        }


        @Override
        public UnitType getSITypeOfThisCategory() {
            return factory(new Force().getSITypeOfThisCategory(), new Length().getSITypeOfThisCategory());
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


    public static UnitType factory(UnitType force, UnitType length) {
        if (force == null || length == null) {
            throw new UnitException("Force and Length must not be null.");
        }
        if (!(force instanceof Force.Types) || !(length instanceof Length.Types)) {
            throw new UnitException("Paremeters for Torque constructor doesn't match");
        }
        Types t = new Types();
        t.setPrincipal(force);
        t.setSecondary(length);
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
        Unit length = new Length().buildUnitIntoAnotherType(new Unit(force.getValue(), otherSec), secondary);
        return new Unit(length.getValue(), anotherType);
    }

}

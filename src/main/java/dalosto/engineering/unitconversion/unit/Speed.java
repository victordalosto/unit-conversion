package dalosto.engineering.unitconversion.unit;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.domain.UnitTypeDuo;
import dalosto.engineering.unitconversion.exception.UnitException;
import dalosto.engineering.unitconversion.formula.TemplateUnitFormulas;


@Component("speed")
public final class Speed extends TemplateUnitFormulas {

    public static class Types extends UnitTypeDuo {

        private static List<UnitType> types = new ArrayList<>();
        static {
            for (UnitType length : new Length().getAllUnitTypesOfThisCategory()) {
                for (UnitType time : new Time().getAllUnitTypesOfThisCategory()) {
                    Types.types.add(factory(length, time));
                }
            }
        }


        @Override
        public UnitType getSITypeOfThisCategory() {
            return factory(new Length().getSITypeOfThisCategory(), new Time().getSITypeOfThisCategory());
        }


        @Override
        public List<UnitType> getAllUnitTypesOfThisCategory() {
            return new ArrayList<>(types);
        }


        @Override
        public String toString() {
            return super.getPrincipal() + "/" + super.getSecondary();
        }

    }


    public static UnitType factory(UnitType speed, UnitType time) {
        if (speed == null || time == null) {
            throw new UnitException("Length and Time must not be null.");
        }
        if (!(speed instanceof Length.Types) || !(time instanceof Time.Types)) {
            throw new UnitException("Paremeters for constructor doesn't match");
        }
        Types t = new Types();
        t.setPrincipal(speed);
        t.setSecondary(time);
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
        Unit length = new Length().buildUnitIntoAnotherType(new Unit(value, principal), otherPri);
        Unit time = new Time().buildUnitIntoAnotherType(new Unit(length.getValue(), otherSec), secondary);
        return new Unit(time.getValue(), anotherType);
    }

}

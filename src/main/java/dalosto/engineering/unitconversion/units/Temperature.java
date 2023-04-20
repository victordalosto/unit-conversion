package dalosto.engineering.unitconversion.units;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;


@Component("Temperature")
public class Temperature extends TemplateUnitFormulas {

    public enum Types implements UnitType {

        K, C, F, R;


        @Override
        public UnitType getSITypeOfThisCategory() {
            return K;
        }


        @Override
        public Set<UnitType> getAllUnitTypesOfThisCategory() {
            return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(Types.values())));
        }

    }


    @Override
    public UnitType getSITypeOfThisCategory() {
        return Types.K.getSITypeOfThisCategory();
    }


    @Override
    public Set<UnitType> getAllUnitTypesOfThisCategory() {
        return Types.K.getAllUnitTypesOfThisCategory();
    }


    @Override
    public Unit convertUnitIntoAnotherType(Unit unit, UnitType otherType) {
        double unitValue = unit.getValue();
        UnitType unitType = unit.getType();
        if (unitType.equals(otherType)) {
            return new Unit(unitValue, unitType);
        }

        switch ((Types) unitType) {
            case K:
                switch ((Types) otherType) {
                case C:
                    return new Unit(unitValue - 273.15, otherType);
                case F:
                    return new Unit(unitValue * 9 / 5 - 459.67, otherType);
                case R:
                    return new Unit(unitValue * 9 / 5, otherType);
                default:
                    return null;
                }
            case C:
                switch ((Types) otherType) {
                case K:
                    return new Unit(unitValue + 273.15, otherType);
                case F:
                    return new Unit(unitValue * 9 / 5 + 32, otherType);
                case R:
                    return new Unit((unitValue + 273.15) * 9 / 5, otherType);
                default:
                    return null;
                }
            case F:
                switch ((Types) otherType) {
                case K:
                    return new Unit((unitValue + 459.67) * 5 / 9, otherType);
                case C:
                    return new Unit((unitValue - 32) * 5 / 9, otherType);
                case R:
                    return new Unit(unitValue + 459.67, otherType);
                default:
                    return null;
                }
            case R:
                switch ((Types) otherType) {
                case K:
                    return new Unit(unitValue * 5 / 9, otherType);
                case C:
                    return new Unit((unitValue - 491.67) * 5 / 9, otherType);
                case F:
                    return new Unit(unitValue - 459.67, otherType);
                default:
                    return null;
                }
        }
        return null;
    }

}

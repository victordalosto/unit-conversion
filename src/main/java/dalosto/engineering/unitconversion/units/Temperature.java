package dalosto.engineering.unitconversion.units;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;


@Component("temperature")
public class Temperature extends TemplateUnitFormulas {

    private static final Map<Types, Map<Types, Function<Double, Double>>> conversionTable = new HashMap<>();


    public static enum Types implements UnitType {

        K, C, F, R;


        @Override
        public UnitType getSITypeOfThisCategory() {
            return K;
        }


        @Override
        public List<UnitType> getAllUnitTypesOfThisCategory() {
            return new ArrayList<>(Arrays.asList(Types.values()));
        }

    }


    @Override
    public UnitType getSITypeOfThisCategory() {
        return Types.K.getSITypeOfThisCategory();
    }


    @Override
    public List<UnitType> getAllUnitTypesOfThisCategory() {
        return Types.K.getAllUnitTypesOfThisCategory();
    }


    @Override
    @SuppressWarnings("all")
    public Unit convertUnitIntoAnotherType(Unit unit, UnitType otherType) {
        double unitValue = unit.getValue();
        UnitType unitType = unit.getType();

        if (unitType.equals(otherType)) {
            return new Unit(unitValue, unitType);
        }

        double convertedValue = conversionTable.get(unitType).get(otherType).apply(unitValue);
        return new Unit(convertedValue, otherType);
    }



    static {
        // Initialize the conversion table
        Map<Types, Function<Double, Double>> kToOthers = new HashMap<>();
        kToOthers.put(Types.C, k -> k - 273.15);
        kToOthers.put(Types.F, k -> k * 9 / 5 - 459.67);
        kToOthers.put(Types.R, k -> k * 9 / 5);

        Map<Types, Function<Double, Double>> cToOthers = new HashMap<>();
        cToOthers.put(Types.K, c -> c + 273.15);
        cToOthers.put(Types.F, c -> c * 9 / 5 + 32);
        cToOthers.put(Types.R, c -> (c + 273.15) * 9 / 5);

        Map<Types, Function<Double, Double>> fToOthers = new HashMap<>();
        fToOthers.put(Types.K, f -> (f + 459.67) * 5 / 9);
        fToOthers.put(Types.C, f -> (f - 32) * 5 / 9);
        fToOthers.put(Types.R, f -> f + 459.67);

        Map<Types, Function<Double, Double>> rToOthers = new HashMap<>();
        rToOthers.put(Types.K, r -> r * 5 / 9);
        rToOthers.put(Types.C, r -> (r - 491.67) * 5 / 9);
        rToOthers.put(Types.F, r -> r - 459.67);

        conversionTable.put(Types.K, kToOthers);
        conversionTable.put(Types.C, cToOthers);
        conversionTable.put(Types.F, fToOthers);
        conversionTable.put(Types.R, rToOthers);
    }
}

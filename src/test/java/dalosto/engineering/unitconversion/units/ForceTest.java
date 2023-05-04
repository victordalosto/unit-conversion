package dalosto.engineering.unitconversion.units;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import dalosto.engineering.unitconversion.MetricTest;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.exception.UnitException;
import dalosto.engineering.unitconversion.formula.UnitFormula;
import dalosto.engineering.unitconversion.unit.Force;


public class ForceTest {

    private UnitFormula unitFormula = new Force();


    public void assertEquivalentForceInSI(double expected, Unit actual) {
        MetricTest.assertEquavalentInSI(expected, actual, unitFormula);
    }


    public void assertEquivalentForce(double fromValue, Force.Types from, Double toValue, Force.Types to) {
        MetricTest.assertEquivalentUnit(fromValue, from, toValue, to, unitFormula);
    }


    @Test
    public void shouldBeAbleToCreateAUnitAndConvertToAnotherUnitWithoutChangingTheOriginalType() {
        double value = 50.0;
        UnitType unitType = Force.Types.KN;
        Unit unit = new Unit(value, unitType);
        Unit outputSI = unitFormula.buildUnitToSI(unit);
        Unit outputAnotherType = unitFormula.buildUnitIntoAnotherType(unit, Force.Types.KGF);
        assertEquals(value, unit.getValue(), MetricTest.tolerance);
        assertEquals(unitType, unit.getType());
        assertEquals(50000.0, outputSI.getValue(), MetricTest.tolerance);
        assertEquals(Force.Types.N, outputSI.getType());
        assertEquals(5098.58104999997, outputAnotherType.getValue(), MetricTest.tolerance);
        assertEquals(Force.Types.KGF, outputAnotherType.getType());
    }


    @Test
    public void SIUnitTypeOfForceShouldBeMeterSquare() {
        assertEquals(Force.Types.N, unitFormula.getSITypeOfThisCategory());
        assertEquals(Force.Types.N, Force.Types.MN.getSITypeOfThisCategory());
        assertEquals(Force.Types.N, new Unit(0.0, Force.Types.POUND).getType().getSITypeOfThisCategory());
        assertEquals(Force.Types.N, new Unit(0.0, Force.Types.KGF).getType().getSITypeOfThisCategory());
    }


    @Test
    public void assertThatOnlyTheCorrectUnitTypesUsesGravityValue() {
        assertEquals(14, Force.Types.values().length);
        assert(!Force.Types.N.dependesOfGravityOnConversion);
        assert(!Force.Types.KN.dependesOfGravityOnConversion);
        assert(!Force.Types.MN.dependesOfGravityOnConversion);
        assert(!Force.Types.GN.dependesOfGravityOnConversion);
        assert(!Force.Types.TN.dependesOfGravityOnConversion);
        assert(!Force.Types.LB.dependesOfGravityOnConversion);
        assert(!Force.Types.POUND.dependesOfGravityOnConversion);
        assert(!Force.Types.OZ.dependesOfGravityOnConversion);
        assert(!Force.Types.KIP.dependesOfGravityOnConversion);
        assert(Force.Types.GF.dependesOfGravityOnConversion);
        assert(Force.Types.G.dependesOfGravityOnConversion);
        assert(Force.Types.KGF.dependesOfGravityOnConversion);
        assert(Force.Types.KG.dependesOfGravityOnConversion);
        assert(Force.Types.T.dependesOfGravityOnConversion);
    }


    @Test
    public void allForceValuesShouldBeTestedAndAreCorrectInSIForUnitaryValue() {
        assertEquals(14, Force.Types.values().length);
        assertEquivalentForceInSI(1.0, new Unit(1, Force.Types.N));
        assertEquivalentForceInSI(Math.pow(10, 3), new Unit(1, Force.Types.KN));
        assertEquivalentForceInSI(Math.pow(10, 6), new Unit(1, Force.Types.MN));
        assertEquivalentForceInSI(Math.pow(10, 9), new Unit(1, Force.Types.GN));
        assertEquivalentForceInSI(Math.pow(10, 12), new Unit(1, Force.Types.TN));
        assertEquivalentForceInSI(4.4482216282509, new Unit(1, Force.Types.LB));
        assertEquivalentForceInSI(4.4482216282509, new Unit(1, Force.Types.POUND));
        assertEquivalentForceInSI(0.27801385176568125, new Unit(1, Force.Types.OZ));
        assertEquivalentForceInSI(4448.2216282509, new Unit(1, Force.Types.KIP));
        assertEquivalentForceInSI(0.0098066500286389, new Unit(1, Force.Types.GF));
        assertEquivalentForceInSI(0.0098066500286389, new Unit(1, Force.Types.G));
        assertEquivalentForceInSI(9.8066500286389, new Unit(1, Force.Types.KGF));
        assertEquivalentForceInSI(9.8066500286389, new Unit(1, Force.Types.KG));
        assertEquivalentForceInSI(9806.6500286389, new Unit(1, Force.Types.T));
    }


    @Test
    public void allForceValuesShouldBeTestedAndAreCorrectInSIForPostiveValue() {
        assertEquals(14, Force.Types.values().length);
        double expected = 12345.67;
        assertEquivalentForceInSI(expected, new Unit(12345.67, Force.Types.N));
        assertEquivalentForceInSI(expected, new Unit(12.34567, Force.Types.KN));
        assertEquivalentForceInSI(expected, new Unit(0.01234567, Force.Types.MN));
        assertEquivalentForceInSI(expected, new Unit(12345.67 * Math.pow(10, -9), Force.Types.GN));
        assertEquivalentForceInSI(expected, new Unit(12345.67 * Math.pow(10, -12), Force.Types.TN));
        assertEquivalentForceInSI(expected, new Unit(2775.41701645259, Force.Types.LB));
        assertEquivalentForceInSI(expected, new Unit(2775.41701645259, Force.Types.POUND));
        assertEquivalentForceInSI(expected, new Unit(44406.67226324144, Force.Types.OZ));
        assertEquivalentForceInSI(expected, new Unit(2.77541701645259, Force.Types.KIP));
        assertEquivalentForceInSI(expected, new Unit(1258907.98223106, Force.Types.GF));
        assertEquivalentForceInSI(expected, new Unit(1258907.98223106, Force.Types.G));
        assertEquivalentForceInSI(expected, new Unit(1258.90798223106, Force.Types.KGF));
        assertEquivalentForceInSI(expected, new Unit(1258.90798223106, Force.Types.KG));
        assertEquivalentForceInSI(expected, new Unit(1.25890798223106, Force.Types.T));
    }


    @Test
    public void allForceValuesShouldBeTestedAndAreCorrectInSIForNegativeValue() {
        assertEquals(14, Force.Types.values().length);
        double expected = -12345.67;
        assertEquivalentForceInSI(expected, new Unit(-12345.67, Force.Types.N));
        assertEquivalentForceInSI(expected, new Unit(-12.34567, Force.Types.KN));
        assertEquivalentForceInSI(expected, new Unit(-0.01234567, Force.Types.MN));
        assertEquivalentForceInSI(expected, new Unit(-12345.67 * Math.pow(10, -9), Force.Types.GN));
        assertEquivalentForceInSI(expected, new Unit(-12345.67 * Math.pow(10, -12), Force.Types.TN));
        assertEquivalentForceInSI(expected, new Unit(-2775.41701645259, Force.Types.LB));
        assertEquivalentForceInSI(expected, new Unit(-2775.41701645259, Force.Types.POUND));
        assertEquivalentForceInSI(expected, new Unit(-44406.67226324144, Force.Types.OZ));
        assertEquivalentForceInSI(expected, new Unit(-2.77541701645259, Force.Types.KIP));
        assertEquivalentForceInSI(expected, new Unit(-1258907.98223106, Force.Types.GF));
        assertEquivalentForceInSI(expected, new Unit(-1258907.98223106, Force.Types.G));
        assertEquivalentForceInSI(expected, new Unit(-1258.90798223106, Force.Types.KGF));
        assertEquivalentForceInSI(expected, new Unit(-1258.90798223106, Force.Types.KG));
        assertEquivalentForceInSI(expected, new Unit(-1.25890798223106, Force.Types.T));
    }


    @Test
    public void allForceValuesShouldBeTestedAndAreCorrectInSIForZero() {
        assertEquals(14, Force.Types.values().length);
        double expected = 0.0;
        assertEquivalentForceInSI(expected, new Unit(0.0, Force.Types.N));
        assertEquivalentForceInSI(expected, new Unit(0.0, Force.Types.KN));
        assertEquivalentForceInSI(expected, new Unit(0.0, Force.Types.MN));
        assertEquivalentForceInSI(expected, new Unit(0.0, Force.Types.GN));
        assertEquivalentForceInSI(expected, new Unit(0.0, Force.Types.TN));
        assertEquivalentForceInSI(expected, new Unit(0.0, Force.Types.LB));
        assertEquivalentForceInSI(expected, new Unit(0.0, Force.Types.POUND));
        assertEquivalentForceInSI(expected, new Unit(0.0, Force.Types.OZ));
        assertEquivalentForceInSI(expected, new Unit(0.0, Force.Types.KIP));
        assertEquivalentForceInSI(expected, new Unit(0.0, Force.Types.GF));
        assertEquivalentForceInSI(expected, new Unit(0.0, Force.Types.G));
        assertEquivalentForceInSI(expected, new Unit(0.0, Force.Types.KG));
        assertEquivalentForceInSI(expected, new Unit(0.0, Force.Types.KGF));
        assertEquivalentForceInSI(expected, new Unit(0.0, Force.Types.T));
    }


    @Test
    public void forceValuesShouldBeCorrectForConversionBetweenTypesUsingZero() {
        assertEquals(14, Force.Types.values().length);
        assertEquivalentForce(0.0, Force.Types.N, 0.0, Force.Types.KN);
        assertEquivalentForce(0.0, Force.Types.KN, 0.0, Force.Types.MN);
        assertEquivalentForce(0.0, Force.Types.MN, 0.0, Force.Types.GN);
        assertEquivalentForce(0.0, Force.Types.GN, 0.0, Force.Types.TN);
        assertEquivalentForce(0.0, Force.Types.TN, 0.0, Force.Types.LB);
        assertEquivalentForce(0.0, Force.Types.LB, 0.0, Force.Types.POUND);
        assertEquivalentForce(0.0, Force.Types.LB, 0.0, Force.Types.OZ);
        assertEquivalentForce(0.0, Force.Types.POUND, 0.0, Force.Types.KIP);
        assertEquivalentForce(0.0, Force.Types.KIP, 0.0, Force.Types.G);
        assertEquivalentForce(0.0, Force.Types.KIP, 0.0, Force.Types.GF);
        assertEquivalentForce(0.0, Force.Types.KIP, 0.0, Force.Types.GF);
        assertEquivalentForce(0.0, Force.Types.GF, 0.0, Force.Types.KGF);
        assertEquivalentForce(0.0, Force.Types.GF, 0.0, Force.Types.KG);
        assertEquivalentForce(0.0, Force.Types.KGF, 0.0, Force.Types.T);
        assertEquivalentForce(0.0, Force.Types.T, 0.0, Force.Types.N);
    }


    @Test
    public void forceValuesShouldBeCorrectForConversionBetweenTypesUsingRandomValues() {
        assertEquals(14, Force.Types.values().length);
        double randomValue = Math.random() * 100;
        assertEquivalentForce(randomValue, Force.Types.N, randomValue/1000.0, Force.Types.KN);
        assertEquivalentForce(randomValue, Force.Types.KN, randomValue/1000.0, Force.Types.MN);
        assertEquivalentForce(randomValue, Force.Types.MN, randomValue/1000.0, Force.Types.GN);
        assertEquivalentForce(randomValue, Force.Types.GN, randomValue/1000.0, Force.Types.TN);
        assertEquivalentForce(randomValue, Force.Types.KN, randomValue*224.808942443188, Force.Types.LB);
        assertEquivalentForce(randomValue, Force.Types.LB, randomValue, Force.Types.POUND);
        assertEquivalentForce(randomValue, Force.Types.POUND, randomValue/1000.0, Force.Types.KIP);
        assertEquivalentForce(randomValue, Force.Types.OZ, randomValue/1000.0/16.0, Force.Types.KIP);
        assertEquivalentForce(randomValue, Force.Types.GF, randomValue/1000.0, Force.Types.KGF);
        assertEquivalentForce(randomValue, Force.Types.G, randomValue/1000.0, Force.Types.KGF);
        assertEquivalentForce(randomValue, Force.Types.KG, randomValue/1000.0, Force.Types.T);
        assertEquivalentForce(randomValue, Force.Types.KGF, randomValue/1000.0, Force.Types.T);
        assertEquivalentForce(randomValue, Force.Types.T, randomValue*9.8066500286389*1000.0, Force.Types.N);
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullValuesArePassed() {
        Unit unit = new Unit(1, Force.Types.N);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, Force.Types.N));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, null));
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullUnitTypeValuesArePassed() {
        Unit unit = new Unit(1, null);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(unit));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Force.Types.N));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
    }

}
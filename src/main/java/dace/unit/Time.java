package dace.unit;

import dace.model.Unit;

public enum Time implements Unit {

    S(1.0),
    MS(Math.pow(10.0, -3)),
    US(Math.pow(10.0, -6)),
    MIN(60.0),
    H(3600.0),
    DAY(86400.0),
    WEEK(604800.0),
    MONTH(86400.0 * (365.0/12.0)),
    MONTH_30(86400.0 * 30.0),
    MONTH_31(86400.0 * 31.0),
    YEAR(31536000.0);


    private final double factorEquivalenceToSI;

    private Time(double value) {
        this.factorEquivalenceToSI = value;
    }

    
    @Override
    public double convertValueToSI(double value) {
        return value * factorEquivalenceToSI;
    }
    
}

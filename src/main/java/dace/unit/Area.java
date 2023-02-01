package dace.unit;

import dace.model.Unit;

public enum Area implements Unit {

    M_2(1.0),
    DM_2(0.01),
    CM_2(0.0001),
    MM_2(0.000001),
    HM_2(10000.0),
    KM_2(1000000.0),
    UM_2(Math.pow(10.0, -12)),
    IN_2(0.00064516),
    FT_2(0.09290304),
    YD_2(0.83612736);


    private final double factorEquivalenceToSI;

    private Area(double value) {
        this.factorEquivalenceToSI = value;
    }

    
    @Override
    public double getEquivalenceFactorInSI() {
        return factorEquivalenceToSI;
    }
    
}

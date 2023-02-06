package dace.unit;

import dace.model.Unit;

public enum Inertia implements Unit {

    M_4(1.0),
    DM_4(Math.pow(10.0, -4)),
    CM_4(Math.pow(10.0, -8)),
    MM_4(Math.pow(10.0, -12)),
    HM_4(Math.pow(10.0, 8)),
    KM_4(Math.pow(10.0, 12)),
    UM_4(Math.pow(10.0, -24)),
    IN_4(0.0000004162314256),
    FT_4(0.0086309748412416),
    YD_4(0.6991089621405696);


    private final double factorEquivalenceToSI;

    private Inertia(double value) {
        this.factorEquivalenceToSI = value;
    }

    
    @Override
    public double convertValueToSI(double value) {
        return value * factorEquivalenceToSI;
    }
    
}

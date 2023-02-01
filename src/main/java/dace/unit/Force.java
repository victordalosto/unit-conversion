package dace.unit;

import dace.model.Unit;

public enum Force implements Unit {

    N(1.0),
    KN(1000.0),
    MN(1000.0*1000.0),
    GN(1000.0*1000.0*1000.0),
    TN(1000000000000.0),
    LB(4.4482216153),
    POUND(4.4482216153),
    KIP(4448.2216153),
    G(1.0/1000),   // *= Gravity
    KG(1.0),       // *= Gravity
    T(1000.0);     // *= Gravity


    private final double factorEquivalenceToSI;
    private final double gravityForce = 9.80665; // m/s2

    private Force(double value) {
        this.factorEquivalenceToSI = value;
    }


    @Override
    public double getEquivalenceFactorInSI() {
        if (this.equals(G) || this.equals(KG) || this.equals(T))
            return gravityForce * factorEquivalenceToSI;
        return factorEquivalenceToSI;
    }

}

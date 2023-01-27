package dace.unit;

import dace.model.Unit;

public enum Force implements Unit {

    N(1),
    KN(1000d),
    MN(1000d*1000),
    GN(1000d*1000*1000),
    TN(1000000000000d),
    LB(4.4482216153),
    POUND(4.4482216153),
    KIP(4448.2216153),
    G(1.0/1000),
    KG(1),
    T(1000);

    private final double factorEquivalenceToSI;
    private final double gravityForce = 9.80665; // m/s2

    private Force(double value) {
        this.factorEquivalenceToSI = value;
    }


    @Override
    public double getEquivalenceInSI() {
        if (this.equals(G) || this.equals(KG) || this.equals(T))
            return gravityForce * factorEquivalenceToSI;
        return factorEquivalenceToSI;
    }


    @Override
    public Unit[] getAllUnits() {
        return values();
    }

}

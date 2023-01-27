package dace.unit;

import dace.model.Unit;

public enum Length implements Unit {

    M(1),
    DM(0.1),
    CM(0.01),
    MM(0.001),
    HM(100),
    KM(1000),
    UM(1.0/(1000*1000)),
    IN(0.0254),
    FT(0.3048),
    YD(0.9144);

    private final double factorEquivalenceToSI;

    private Length(double value) {
        this.factorEquivalenceToSI = value;
    }

    
    @Override
    public double getEquivalenceInSI() {
        return factorEquivalenceToSI;
    }


    @Override
    public Unit[] getAllUnits() {
        return Length.values();
    }
    
}

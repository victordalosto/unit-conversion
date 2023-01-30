package dace.unit;
import dace.model.Unit;


public enum Volume implements Unit {

    M_3(1),
    DM_3(0.001),
    CM_3(Math.pow(10, -6)),
    MM_3(Math.pow(10, -9)),
    HM_3(Math.pow(10, 6)),
    KM_3(Math.pow(10, 9)),
    UM_3(Math.pow(10, -12)),
    IN_3(0.000016387064),
    FT_3(0.028316846592),
    YD_3(0.764554857984),
    ML(0.000001),
    L(0.001);
    


    private final double factorEquivalenceToSI;

    private Volume(double value) {
        this.factorEquivalenceToSI = value;
    }

    
    @Override
    public double getEquivalenceInSI() {
        return factorEquivalenceToSI;
    }


    @Override
    public Unit[] getAllUnits() {
        return Volume.values();
    }
    
}

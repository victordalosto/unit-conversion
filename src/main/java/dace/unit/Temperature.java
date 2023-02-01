package dace.unit;

import dace.model.Unit;

public enum Temperature implements Unit  {

    K,
    C,
    F;


    @Override
    public double getEquivalenceFactorInSI() {
        return 0;
    }
    
}

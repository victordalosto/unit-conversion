package dace.unit;

import dace.model.Unit;

public enum Temperature implements Unit  {

    K,
    C,
    F;


    @Override
    public double convertValueToSI(double value) {
        if (this.equals(C))
            return value + 273.15;
        if (this.equals(F))
            return ((value-32.0) * 5.0/9.0) + 273.15;
        return value;
    }
    
}

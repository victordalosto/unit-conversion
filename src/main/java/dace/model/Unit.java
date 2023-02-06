package dace.model;


public interface Unit {

    /**
     * @return The equivalent value of a Unit in SI.
     */
    double convertValueToSI(double value);
    
}
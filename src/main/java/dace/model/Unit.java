package dace.model;


public interface Unit {


    /**
     * @return get All possible Units of a Type.
     */
    Unit [] getAllUnits();


    /**
     * @return The equivalent value of a Unit in SI.
     */
    double getEquivalenceInSI();

    
}

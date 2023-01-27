package dace.unit;

import dace.model.Unit;

public enum Volume implements Unit {

    V(Length.CM);

    private final Length length;


    Volume(Length length) {
        this.length = length;
    }




    @Override
    public Unit[] getAllUnits() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getEquivalenceInSI() {
        // TODO Auto-generated method stub
        return 0;
    }


    
}

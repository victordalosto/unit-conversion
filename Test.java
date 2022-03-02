
import src.Conversion;

/**
 * This class contains examples of usage of the:
 * Unit conversion functions in the java Class
 */

class Test extends Conversion{
    public static void main(String[] args) {

        double input;
        double output;

        
        // Convert unit of Length
        // Example: Converting value from inches(in) to centimeter(cm)
        input = 1;
        output = convertLength(input, "in", "cm"); // returns 2.54
        System.out.println(input + "in -> " + output + "cm");


        // Convert unit of Area
        // Example: Converting value from milimeter^2(mm2) to centimeter^2(cm2)
        input = 2;
        output = convertArea(input, "cm2", "mm2"); // returns 200
        System.out.println(input + "cm2 -> " + output + "mm2");


        // Convert unit of Volume
        // Example: Converting value from Liters(L) to cm3(cm3)
        input = 3;
        output = convertVolume(input, "L", "cm3"); // returns 3000
        System.out.println(input + "L -> " + output + "cm3");


        // Convert unit of Inertia
        // Example: Converting value from meters^4(m4) to ft4(ft4)
        input = 4;
        output = convertInertia(input, "m", "ft"); // returns 463.44706983580863
        System.out.println(input + "m4 -> " + output + "ft4");


        // Convert unit of Force
        // Example: Converting value from kilopound-force(kipf) to Newton(N)
        input = 5;
        output = convertForce(input, "kipf", "N"); // returns 22241.1080765
        System.out.println(input + "kipf -> " + output + "N");


        // Convert unit of Moment
        // Example: Converting value from N.cm to kgf.mm
        input = 6;
        output = convertMoment(input, "N.cm", "kgf.mm"); // returns 6.118297277867569
        System.out.println(input + "N.cm -> " + output + "kgf.mm");


        // Convert unit of Pressure == Tensao
        // Example: Converting value from N/mm2 to kgf/cm2
        input = 7;
        output = convertPressure(input, "N/mm2", "kgf/cm2"); // returns 71.38013490845498
        System.out.println(input + "N/mm2 -> " + output + "kgf/cm2");


        // Convert unit of Temperature
        // Example: Converting value from ºC to F
        input = 8;
        output = convertTemperature(input, "ºC", "F"); // returns 46.4
        System.out.println(input + "ºC -> " + output + "F");


        // Changing value of gravity for force Conversion
        // Using the default standard gValue = 9.80665..
        input = 1;
        System.out.println(convertForce(input, "kgf", "N")); // returns 98.06649999999999
        gValue = 10; // Using the gValue = 10
        System.out.println(convertForce(input, "kgf", "N")); // returns 100

    }
}
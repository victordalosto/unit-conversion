package dace;
// package src;

public class Conversion {

    /** Value for gravity (g)
     * Default value = 9.80665 m/s-2 */
    public static double gValue = 9.80665;



    /**
     * Convert LENGTH (u¹) input value to millimeter
     * @param value double - input value to be converted to [mm]
     * @param unit  String - input unit type
     * @return double value converted in [mm] unit
     */
    private static double lengthEquivalence(double value, String unit) {
        System.out.println();
        // Remove numbers and convert to lowerstring
        unit = unit.replaceAll("[¹²³ªº°^/]", "").replaceAll("\\d", "").toLowerCase(); 
        // Convert some customary unit
        unit = unit.replace("ml", "cm"); // equivalent to cm³
        unit = unit.replace("l", "dm"); //  equivalent to dm³
        switch (unit){
            case "mm": value *= 1; break;
            case "cm": value *= 10; break;
            case "dm": value *= 100; break;
            case "m":  value *= 1000; break;
            case "km": value *= 1000*1000; break;
            case "in": value *= 25.4; break;
            case "ft": value *= 12*25.4; break;
            case "yd": value *= 3*12*25.4; break;
            case "mi": value *= 1609344; break;
            case "milha nautica": value *= 1852000; break;
            default: value *= 1;
        }
        return value;
    }



    /**
     * Convert FORCE input value to Newton [N]
     * @param value double - input value to be converted to [N]
     * @param unit  String - input unit type
     * @return double value converted in [N] unit
     */
    private static double forceEquivalence(double value, String unit) {
        // Remove numbers and convert to lowerstring
        unit = unit.replaceAll("[¹²³ªº°^/]", "").replaceAll("\\d", "").toLowerCase(); 
        switch(unit){
            case "n":  value *= 1; break;
            case "kn": value *= 1000; break;
            case "mn": value *= 1000*1000; break;
            case "gn": value *= 1000*1000*1000; break;
            case "kg":
            case "kgf": value *= gValue; break;
            case "t":
            case "tf": value *= gValue*1000; break;
            case "g":
            case "gf": value *= gValue/1000; break;
            case "lb":
            case "lbf":
            case "poundforce": value *= 4.4482216153; break;
            case "kip":
            case "kipf": value *= 4448.2216153; break; 
            default: value *= 1; break;
        }
        return value;
    }



    /**
     * Convert unit of LENGTH. <p> 
     * Convert the input into a value equivalent to the output type. [input¹] -> [output¹])<p>
     * [mm, cm, dm, m, km, in, ft, yd, mi]
     * @param value  double - value to be converted.
     * @param input  String - type of input value.
     * @param output String - type of output value.
     * @return double value converted in output unit.
     */
    public static double convertLength(double value, String input, String output) {
        double newValue;
        newValue = lengthEquivalence(value, input);
        newValue = newValue / lengthEquivalence(1, output);
        return newValue;
    }



    /**
     * Convert unit of AREA. <p> 
     * Convert the input into a value equivalent to the output type. [input²] -> [output²])<p>
     * [mm², cm², dm², m², km², in², ft², yd², mi²]
     * @param value  double - value to be converted.
     * @param input  String - type of input value.
     * @param output String - type of output value.
     * @return double value converted in output² unit.
     */
    public static double convertArea(double value, String input, String output) {
        double newValue;
        newValue = lengthEquivalence(value, input);
        newValue = lengthEquivalence(newValue, input);
        newValue = newValue / lengthEquivalence(1, output);
        newValue = newValue / lengthEquivalence(1, output);
        return newValue;
    }



    /**
     * Convert unit of VOLUME. <p> 
     * Convert the input into a value equivalent to the output type. [input³] -> [output³])<p>
     * [mm³, cm³, dm³, m³, km³, in³, ft³, yd³, mi³, L, mL]
     * @param value  double - value to be converted.
     * @param input  String - type of input value.
     * @param output String - type of output value.
     * @return double value converted in output³ unit.
     */
    public static double convertVolume(double value, String input, String output) {
        double newValue;
        newValue = lengthEquivalence(value, input);
        newValue = lengthEquivalence(newValue, input);
        newValue = lengthEquivalence(newValue, input);
        newValue = newValue / lengthEquivalence(1, output);
        newValue = newValue / lengthEquivalence(1, output);
        newValue = newValue / lengthEquivalence(1, output);
        return newValue;
    }



    /**
     * Convert unit of INERTIA. <p> 
     * Convert the input into a value equivalent to the output type. [input^4] -> [output^4])<p>
     * [mm4, cm4, dm4, m4, km4, in4, ft4, yd4, mi4]
     * @param value  double - value to be converted.
     * @param input  String - type of input value.
     * @param output String - type of output value.
     * @return double value converted in output4 unit.
     */
    public static double convertInertia(double value, String input, String output) {
        double newValue;
        newValue = lengthEquivalence(value, input);
        newValue = lengthEquivalence(newValue, input);
        newValue = lengthEquivalence(newValue, input);
        newValue = lengthEquivalence(newValue, input);
        newValue = newValue / lengthEquivalence(1, output);
        newValue = newValue / lengthEquivalence(1, output);
        newValue = newValue / lengthEquivalence(1, output);
        newValue = newValue / lengthEquivalence(1, output);
        return newValue;
    }



    /**
     * Convert unit of FORCE. <p>
     * Convert the input into a value equivalent to the output type.<p>
     * [N, kN, MN, GN, tf, kgf, gf, kipf, lbf]
     * @param value  double - value to be converted.
     * @param input  String - type of input value.
     * @param output String - type of output value.
     * @return double value converted in output force unit
     */
    public static double convertForce(double value, String input, String output) {
        double newValue;
        newValue = forceEquivalence(value, input);
        newValue = newValue / forceEquivalence(1, output);
        return newValue;
    }



    /**
     * Convert unit of Moment [Force.length]. <p>
     * [N, kN, MN, GN, tf, kgf, gf, kipf, lbf] *
     * [mm, cm, dm, m, km, in, ft, yd, mi]
     * @param value  double - value to be converted.
     * @param input  String - Input as "Force.length", separated by dot . - example: "N.mm" or "tf.cm"
     * @param output String - Input as "Force.length", separated by dot . - example: "kgf.mm" or "kN.m"
     * @return double value converted
     */
    public static double convertMoment(double value, String input, String output) {
        double newValue;
        // Split String into array [force, length] 
        String[] newInput = input.split("\\.");
        String[] newOutput = output.split("\\.");
        newValue = convertForce(value, newInput[0], newOutput[0]);
        newValue = convertLength(newValue, newInput[1], newOutput[1]);
        return newValue;
    }



    /**
     * Convert unit of pressure [Force/Area] <p>
     * Accepts: [MPa, KPa, GPa, KSI, PSI, bar] <p> 
     * or Force/Area: <p>
     * [N, kN, MN, GN, tf, kgf, gf, kipf, lbf] /<p>
     * [mm², cm², dm², m², km², in², ft², yd², mi²]
     * @param value  double - value to be converted.
     * @param input  String - Input as "Force/length²", separated by slash / - examples: "MPa" or "N/mm²"
     * @param output String - Input as "Force/length²", separated by slash / - examples: "kgf/mm²"
     * @return double value converted 
     */
    public static double convertPressure(double value, String input, String output) {
        input = input.toLowerCase();
        input = input.replace("mpa", "n/mm2");
        input = input.replace("kpa", "kn/m2");
        input = input.replace("gpa", "kn/mm2");
        input = input.replace("ksi", "kipf/in2");
        input = input.replace("psi", "lbf/in2");
        input = input.replace("bar", "kn/dm2");
        output = output.toLowerCase();
        output = output.replace("mpa", "n/mm2");
        output = output.replace("kpa", "kn/m2");
        output = output.replace("gpa", "kn/mm2");
        output = output.replace("ksi", "kipf/in2");
        output = output.replace("psi", "lbf/in2");
        output = output.replace("bar", "kn/dm2");
        double newValue;
        // Split String into array [force, length] 
        String[] newInput = input.split("/");
        String[] newOutput = output.split("/");
        newValue = convertForce(value, newInput[0], newOutput[0]);
        newValue = newValue / convertArea(1, newInput[1], newOutput[1]);
        return newValue;
    }



    /**
     * Convert unit of Temperature <p>
     * [°C (celsisus),  K (kelvin),  °F (Fahrenheit),  °R (Rankine),]
     * @param value  double - value to be converted.
     * @param input  String - Input as: "C" or "ºC"
     * @param output String - Input as: "C" or "ºC"
     * @return double value converted 
     */
    public static double convertTemperature(double value, String input, String output) {
        input = input.replaceAll("[¹²³ªº°^/]", "").replaceAll("\\d", "").toLowerCase();
        output = output.replaceAll("[¹²³ªº°^/]", "").replaceAll("\\d", "").toLowerCase();
        // Convert value from input to ºC
        switch (input) {
            case "k": value -= 273.15; break;
            case "f": value = (value-32)*5/9; break;
            case "r": value = (value - 491.67)*5/9; break;
            default:  value *= 1; break;
            }
        switch (output){
            case "k": value += 273.15; break;
            case "f": value = (value*9/5)+32; break;
            case "r": value = (value*9/5)+491.67; break;
            default: value *= 1; break;
            }
        return value;
    }
}

### A measurement conversion library for `Java`.
- 8 different types of conversion;
- The methods convert the input double value into a value equivalent to the output type;
- Just import or extends the class that handles all values conversion.

## Types of conversion
<details><summary>Length</summary>
<h5>mm, cm, dm, m, km, in, ft, yd, mi</h5>
</details>
<details><summary>Area</summary>
<h5>mm², cm², dm², m², km², in², ft², yd², mi²</h5>
</details>
<details><summary>Volume</summary>
<h5>mm³, cm³, dm³, m³, km³, in³, ft³, yd³, mi³, L, mL</h5>
</details>
<details><summary>Inertia</summary>
<h5>mm4, cm4, dm4, m4, km4, in4, ft4, yd4, mi4</h5>
</details>
<details><summary>Force</summary>
<h5>N, kN, MN, GN, tf, kgf, gf, kipf, lbf</h5>
</details>
<details><summary>Moment</summary>
<h5>Units are separated by a dot . String as: Force.length.

N.mm, N.cm, N.dm, N.m, N.km, N.in, N.ft, N.yd, N.mi, kN.mm, kN.cm, kN.dm, kN.m, kN.km, kN.in, kN.ft, kN.yd, kN.mi, MN.mm, MN.cm, MN.dm, MN.m, MN.km, MN.in, MN.ft, MN.yd, MN.mi, GN.mm, GN.cm, GN.dm, GN.m, GN.km, GN.in, GN.ft, GN.yd, GN.mi, tf.mm, tf.cm, tf.dm, tf.m, tf.km, tf.in, tf.ft, tf.yd, tf.mi, kgf.mm, kgf.cm, kgf.dm, kgf.m, kgf.km, kgf.in, kgf.ft, kgf.yd, kgf.mi, gf.mm, gf.cm, gf.dm, gf.m, gf.km, gf.in, gf.ft, gf.yd, gf.mi, kipf.mm, kipf.cm, kipf.dm, kipf.m, kipf.km, kipf.in, kipf.ft, kipf.yd, kipf.mi, lbf.mm, lbf.cm, lbf.dm, lbf.m, lbf.km, lbf.in, lbf.ft, lbf.yd, lbf.mi</h5>
</details>
<details><summary>Pressure</summary>
<h5>Units are separated by a slash \ - String as: Force/Area.

MPa, KPa, GPa, KSI, PSI, bar, N/mm², N/cm², N/dm², N/m², N/km², N/in², N/ft², N/yd², N/mi², kN/mm², kN/cm², kN/dm², kN/m², kN/km², kN/in², kN/ft², kN/yd², kN/mi², MN/mm², MN/cm², MN/dm², MN/m², MN/km², MN/in², MN/ft², MN/yd², MN/mi², GN/mm², GN/cm², GN/dm², GN/m², GN/km², GN/in², GN/ft², GN/yd², GN/mi², tf/mm², tf/cm², tf/dm², tf/m², tf/km², tf/in², tf/ft², tf/yd², tf/mi², kgf/mm², kgf/cm², kgf/dm², kgf/m², kgf/km², kgf/in², kgf/ft², kgf/yd², kgf/mi², gf/mm², gf/cm², gf/dm², gf/m², gf/km², gf/in², gf/ft², gf/yd², gf/mi², kipf/mm², kipf/cm², kipf/dm², kipf/m², kipf/km², kipf/in², kipf/ft², kipf/yd², kipf/mi², lbf/mm², lbf/cm², lbf/dm², lbf/m², lbf/km², lbf/in², lbf/ft², lbf/yd², lbf/mi²</h5>
</details>
<details><summary>Temperature</summary>
<h5>°C (celsisus),  K (kelvin),  °F (Fahrenheit),  °R (Rankine),</h5>
</details>


## How to use
The methods inputs ignore case sensitivity and handle special characters such as mm3 or mm³ or mm^3.

>Java code example of usage:
```java
// All conversion methods can be imported as follow:
import dalosto.conversion;
Class * extends Conversion {

// Class methods handles double values
double input;
double output;

// Convert unit of Length
// Example: Converting value from inches(in) to centimeter(cm)
input = 1;
output = convertLength(input, "in", "cm"); // returns 2.54

// Convert unit of Area
// Example: Converting value from milimeter^2(mm2) to centimeter^2(cm2)
input = 2;
output = convertArea(input, "cm2", "mm2"); // returns 200

// Convert unit of Volume
// Example: Converting value from Liters(L) to cm3(cm3)
input = 3;
output = convertVolume(input, "L", "cm3"); // returns 3000

// Convert unit of Inertia
// Example: Converting value from meters^4(m4) to ft4(ft4)
input = 4;
output = convertInertia(input, "m", "ft"); // returns 463.44706983580863

// Convert unit of Force
// Example: Converting value from kilopound-force(kipf) to Newton(N)
input = 5;
output = convertForce(input, "kipf", "N"); // returns 22241.1080765

// Convert unit of Moment. Values separated by a dot .
// Example: Converting value from N.cm to kgf.mm
input = 6;
output = convertMoment(input, "N.cm", "kgf.mm"); // returns 6.118297277867569

// Convert unit of Pressure == Tensao. Values separed by slash /
// Example: Converting value from N/mm2 to kgf/cm2
input = 7;
output = convertPressure(input, "N/mm2", "kgf/cm2"); // returns 71.38013490845498

// Convert unit of Temperature
// Example: Converting value from ºC to F
input = 8;
output = convertTemperature(input, "ºC", "F"); // returns 46.4

// Changing value of gravity for force Conversion
// Using the default standard gValue = 9.80665..
input = 1;
output = convertForce(input, "kgf", "N"); // returns 98.06649999999999
gValue = 10; // Using the gValue = 10
output = convertForce(input, "kgf", "N"); // returns 100
}
```

## Installation
Clone the UnitConversion repository to your local machine using `https://github.com/victordalosto/UnitConversion`, then add the Conversion.java file to your folder.

Then just import the Conversion.java or extends the Conversion.java to your Class and you can use the statics methods here implemented.


## About
All source code is implemented in Java.

Created by VictorDalosto.

## License
Licensed under <a href="https://github.com/victordalosto/UnitConversion/blob/main/LICENSE">MIT</a>.
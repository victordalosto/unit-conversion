# Unit converter

Unit Converter is a <strong>Java </strong> application for converting between various measurement units.
The units presented in this program are the most commonly used in the engineering.


The program basically converts the Measurement Unit and its value into another equivalent Unit.


## Types of conversion
Currently, this application supports the conversion of the following measurement units:

<details><summary><strong>Length</strong></summary>
<h5>mm , cm , dm , m , hm , km , in , ft , yd , um</h5>
</details>

<details><summary><strong>Area</strong></summary>
<h5>mm2 , cm2 , dm2 , m2 , hm2 , km2 , in2 , ft2 , yd2 , um2 </h5>
</details>

<details><summary><strong>Volume</strong></summary>
<h5>mm3 , cm3 , dm3 , m3 , hm3 , km3 , in3 , ft3 , yd3 , um3 , L, mL</h5>
</details>

<details><summary><strong>Inertia</strong></summary>
<h5>mm4 , cm4 , dm4 , m4 , km4 , in4 , ft4 , yd4 , mi4</h5>
</details>

<details><summary><strong>Temperature</strong></summary>
<h5>°C (celsisus),  K (kelvin),  °F (Fahrenheit),  °R (Rankine),</h5>
</details>

<!-- 
<details><summary><strong>Force</strong></summary>
<h5>N, kN, MN, GN, tf, kgf, gf, kipf, lbf</h5>
</details>

<details><summary><strong>Moment</strong></summary>
<h5>Units are separated by a dot . String as: Force.length.
N.mm, N.cm, N.dm, N.m, N.km, N.in, N.ft, N.yd, N.mi, kN.mm, kN.cm, kN.dm, kN.m, kN.km, kN.in, kN.ft, kN.yd, kN.mi, MN.mm, MN.cm, MN.dm, MN.m, MN.km, MN.in, MN.ft, MN.yd, MN.mi, GN.mm, GN.cm, GN.dm, GN.m, GN.km, GN.in, GN.ft, GN.yd, GN.mi, tf.mm, tf.cm, tf.dm, tf.m, tf.km, tf.in, tf.ft, tf.yd, tf.mi, kgf.mm, kgf.cm, kgf.dm, kgf.m, kgf.km, kgf.in, kgf.ft, kgf.yd, kgf.mi, gf.mm, gf.cm, gf.dm, gf.m, gf.km, gf.in, gf.ft, gf.yd, gf.mi, kipf.mm, kipf.cm, kipf.dm, kipf.m, kipf.km, kipf.in, kipf.ft, kipf.yd, kipf.mi, lbf.mm, lbf.cm, lbf.dm, lbf.m, lbf.km, lbf.in, lbf.ft, lbf.yd, lbf.mi</h5>
</details>

<details><summary><strong>Pressure</strong></summary>
<h5>Units are separated by a slash \ - String as: Force/Area.
MPa, KPa, GPa, KSI, PSI, bar, N/mm2, N/cm2, N/dm2, N/m2, N/km2, N/in2, N/ft2, N/yd2, N/mi2, kN/mm2, kN/cm2, kN/dm2, kN/m2, kN/km2, kN/in2, kN/ft2, kN/yd2, kN/mi2, MN/mm2, MN/cm2, MN/dm2, MN/m2, MN/km2, MN/in2, MN/ft2, MN/yd2, MN/mi2, GN/mm2, GN/cm2, GN/dm2, GN/m2, GN/km2, GN/in2, GN/ft2, GN/yd2, GN/mi2, tf/mm2, tf/cm2, tf/dm2, tf/m2, tf/km2, tf/in2, tf/ft2, tf/yd2, tf/mi2, kgf/mm2, kgf/cm2, kgf/dm2, kgf/m2, kgf/km2, kgf/in2, kgf/ft2, kgf/yd2, kgf/mi2, gf/mm2, gf/cm2, gf/dm2, gf/m2, gf/km2, gf/in2, gf/ft2, gf/yd2, gf/mi2, kipf/mm2, kipf/cm2, kipf/dm2, kipf/m2, kipf/km2, kipf/in2, kipf/ft2, kipf/yd2, kipf/mi2, lbf/mm2, lbf/cm2, lbf/dm2, lbf/m2, lbf/km2, lbf/in2, lbf/ft2, lbf/yd2, lbf/mi2</h5>
</details>
 -->

<br/><br/>

## How to use

>Java code example of usage:
```java

{

  @Autowired
  @Qualifier("Length")
  UnitFormulas formulas;
  
  void main() {
      Double value = 25.4;
      UnitType cm = Length.Types.CM;
      Unit unit = new Unit(value, cm);

      formulas.buildUnitToSI(unit);  
      // returns: Unit(value=0.254 , unitType=M);
    
      formulas.buildUnitIntoAnotherType(unit, Length.Types.MM);
      // returns: Unit(value=10.0, unitType=IN);
    
      formulas.buildUnitIntoAnotherType(unit, Length.Types.MM);
      // returns: Unit(value=254.0, unitType=MM);
  }
}
  
```
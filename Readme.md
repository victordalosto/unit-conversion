# Unit converter

### A measurement conversion library for `Java`.

- The program converts the Measurement Unit into a equivalent Unit;


## Types of conversion
<details><summary>Length</summary>
<h5>mm, cm, dm, m, km, in, ft, yd, mi</h5>
</details>
<!-- 
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
</details> -->

<br/><br/>

## How to use

The Test.java file provides a list with some examples of usage of the methods used for unit conversion.

>Java code example of usage:
```java

@Autowired
@Qualifier("LengthFormula")
UnitFormulas formulas;

void main() {
  Double value = 25.4;
  UnitType cm = Length.Types.CM;
  Unit unit = new Unit(value, cm);

  formulas.buildUnitIntoAnotherType(unit, Length.Types.MM);
  // returns: Unit(value=254.0, unitType=MM);

  
  formulas.buildUnitIntoAnotherType(unit, Length.Types.MM);
  // returns: Unit(value=10.0, unitType=IN);
}

```

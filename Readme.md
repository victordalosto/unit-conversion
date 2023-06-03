<h1 align="center"> Unit Conversion </h1>

Unit Conversion is a <strong> RESTful </strong> application that implements the Level 3 of the Richardson Maturity Model - HATEOAs.

The program was made in Spring and is used for conversion between various measurement units.

The units presented in this program are the most commonly used in engineering.
The program basically converts the Measurement Unit and its value into another equivalent Unit.
<br/>


<h2 align="center"> How to use </h2>

The program is now hosted on a server:
> https://unit-conversion.onrender.com

The program is now hosted on docker-hub. Just use the command:
> docker run -d -p 80:80 victordalosto/unitconversion


</br>

The entire usage of the application is self explanatory, guided by resources and its URI.
> <b>Endpoint</b>: localhost:80

```json
{
  "header": {
    "home": "http://localhost:80",
    "title": "Unit Conversion API",
    "about": "API used for conversion between measurement units most commonly used in the engineering",
    "description": "Given a quantity expressed in a certain measurement unit, the endpoint returns equivalent quantitys expressed in a different measurement unit",
    "reference": "https://github.com/victordalosto/unit-conversion"
  },
  "result": {
    "length": {
      "uri": "/api/length",
      "about": "This endpoint converts length measurement units.",
      "units": "[M, DM, CM, MM, HM, KM, UM, IN, FT, YD]"
    },
    "area": {
      "uri": "/api/area",
      "about": "This endpoint converts area measurement units.",
      "units": "[M2, DM2, CM2, MM2, HM2, KM2, UM2, IN2, FT2, YD2]"
    },
    "volume": {
      "uri": "/api/volume",
      "about": "This endpoint converts volume measurement units.",
      "units": "[M3, DM3, CM3, MM3, HM3, KM3, UM3, IN3, FT3, YD3, ML, L, GAL]"
    },
    "inertia": {
      "uri": "/api/inertia",
      "about": "This endpoint converts inertia measurement units.",
      "units": "[M4, DM4, CM4, MM4, HM4, KM4, UM4, IN4, FT4, YD4]"
    },
    "force": {
      "uri": "/api/force",
      "about": "This endpoint converts force measurement units.",
      "units": "[N, KN, MN, GN, TN, LB, OZ, POUND, KIP, GF, G, KGF, KG, T]"
    },
    "temperature": {
      "uri": "/api/temperature",
      "about": "This endpoint converts temperature measurement units.",
      "units": "[K, C, F, R]"
    },
    "time": {
      "uri": "/api/time",
      "about": "This endpoint converts time measurement units.",
      "units": "[S, MS, US, MIN, H, DAY, WEEK, MONTH, MONTH30, MONTH31, YEAR]"
    },
    "torque": {
      "uri": "/api/torque",
      "about": "This endpoint converts torque measurement units.",
      "units": "[N.M, N.DM, N.CM, N.MM, N.HM, N.KM, N.UM, N.IN, N.FT, N.YD, KN.M, KN.DM, KN.CM, KN.MM, KN.HM, KN.KM, KN.UM, KN.IN, KN.FT, KN.YD, MN.M, MN.DM, MN.CM, MN.MM, MN.HM, MN.KM, MN.UM, MN.IN, MN.FT, MN.YD, GN.M, GN.DM, GN.CM, GN.MM, GN.HM, GN.KM, GN.UM, GN.IN, GN.FT, GN.YD, TN.M, TN.DM, TN.CM, TN.MM, TN.HM, TN.KM, TN.UM, TN.IN, TN.FT, TN.YD, LB.M, LB.DM, LB.CM, LB.MM, LB.HM, LB.KM, LB.UM, LB.IN, LB.FT, LB.YD, OZ.M, OZ.DM, OZ.CM, OZ.MM, OZ.HM, OZ.KM, OZ.UM, OZ.IN, OZ.FT, OZ.YD, POUND.M, POUND.DM, POUND.CM, POUND.MM, POUND.HM, POUND.KM, POUND.UM, POUND.IN, POUND.FT, POUND.YD, KIP.M, KIP.DM, KIP.CM, KIP.MM, KIP.HM, KIP.KM, KIP.UM, KIP.IN, KIP.FT, KIP.YD, GF.M, GF.DM, GF.CM, GF.MM, GF.HM, GF.KM, GF.UM, GF.IN, GF.FT, GF.YD, G.M, G.DM, G.CM, G.MM, G.HM, G.KM, G.UM, G.IN, G.FT, G.YD, KGF.M, KGF.DM, KGF.CM, KGF.MM, KGF.HM, KGF.KM, KGF.UM, KGF.IN, KGF.FT, KGF.YD, KG.M, KG.DM, KG.CM, KG.MM, KG.HM, KG.KM, KG.UM, KG.IN, KG.FT, KG.YD, T.M, T.DM, T.CM, T.MM, T.HM, T.KM, T.UM, T.IN, T.FT, T.YD]"
    },
    "linear": {
      "uri": "/api/linear",
      "about": "This endpoint converts linear measurement units.",
      "units": "[N/M, N/DM, N/CM, N/MM, N/HM, N/KM, N/UM, N/IN, N/FT, N/YD, KN/M, KN/DM, KN/CM, KN/MM, KN/HM, KN/KM, KN/UM, KN/IN, KN/FT, KN/YD, MN/M, MN/DM, MN/CM, MN/MM, MN/HM, MN/KM, MN/UM, MN/IN, MN/FT, MN/YD, GN/M, GN/DM, GN/CM, GN/MM, GN/HM, GN/KM, GN/UM, GN/IN, GN/FT, GN/YD, TN/M, TN/DM, TN/CM, TN/MM, TN/HM, TN/KM, TN/UM, TN/IN, TN/FT, TN/YD, LB/M, LB/DM, LB/CM, LB/MM, LB/HM, LB/KM, LB/UM, LB/IN, LB/FT, LB/YD, OZ/M, OZ/DM, OZ/CM, OZ/MM, OZ/HM, OZ/KM, OZ/UM, OZ/IN, OZ/FT, OZ/YD, POUND/M, POUND/DM, POUND/CM, POUND/MM, POUND/HM, POUND/KM, POUND/UM, POUND/IN, POUND/FT, POUND/YD, KIP/M, KIP/DM, KIP/CM, KIP/MM, KIP/HM, KIP/KM, KIP/UM, KIP/IN, KIP/FT, KIP/YD, GF/M, GF/DM, GF/CM, GF/MM, GF/HM, GF/KM, GF/UM, GF/IN, GF/FT, GF/YD, G/M, G/DM, G/CM, G/MM, G/HM, G/KM, G/UM, G/IN, G/FT, G/YD, KGF/M, KGF/DM, KGF/CM, KGF/MM, KGF/HM, KGF/KM, KGF/UM, KGF/IN, KGF/FT, KGF/YD, KG/M, KG/DM, KG/CM, KG/MM, KG/HM, KG/KM, KG/UM, KG/IN, KG/FT, KG/YD, T/M, T/DM, T/CM, T/MM, T/HM, T/KM, T/UM, T/IN, T/FT, T/YD]"
    },
    "pressure": {
      "uri": "/api/pressure",
      "about": "This endpoint converts pressure measurement units.",
      "units": "[PA, KPA, MPA, PSI, KSI, N/M2, N/DM2, N/CM2, N/MM2, N/HM2, N/KM2, N/UM2, N/IN2, N/FT2, N/YD2, KN/M2, KN/DM2, KN/CM2, KN/MM2, KN/HM2, KN/KM2, KN/UM2, KN/IN2, KN/FT2, KN/YD2, MN/M2, MN/DM2, MN/CM2, MN/MM2, MN/HM2, MN/KM2, MN/UM2, MN/IN2, MN/FT2, MN/YD2, GN/M2, GN/DM2, GN/CM2, GN/MM2, GN/HM2, GN/KM2, GN/UM2, GN/IN2, GN/FT2, GN/YD2, TN/M2, TN/DM2, TN/CM2, TN/MM2, TN/HM2, TN/KM2, TN/UM2, TN/IN2, TN/FT2, TN/YD2, LB/M2, LB/DM2, LB/CM2, LB/MM2, LB/HM2, LB/KM2, LB/UM2, LB/IN2, LB/FT2, LB/YD2, OZ/M2, OZ/DM2, OZ/CM2, OZ/MM2, OZ/HM2, OZ/KM2, OZ/UM2, OZ/IN2, OZ/FT2, OZ/YD2, POUND/M2, POUND/DM2, POUND/CM2, POUND/MM2, POUND/HM2, POUND/KM2, POUND/UM2, POUND/IN2, POUND/FT2, POUND/YD2, KIP/M2, KIP/DM2, KIP/CM2, KIP/MM2, KIP/HM2, KIP/KM2, KIP/UM2, KIP/IN2, KIP/FT2, KIP/YD2, GF/M2, GF/DM2, GF/CM2, GF/MM2, GF/HM2, GF/KM2, GF/UM2, GF/IN2, GF/FT2, GF/YD2, G/M2, G/DM2, G/CM2, G/MM2, G/HM2, G/KM2, G/UM2, G/IN2, G/FT2, G/YD2, KGF/M2, KGF/DM2, KGF/CM2, KGF/MM2, KGF/HM2, KGF/KM2, KGF/UM2, KGF/IN2, KGF/FT2, KGF/YD2, KG/M2, KG/DM2, KG/CM2, KG/MM2, KG/HM2, KG/KM2, KG/UM2, KG/IN2, KG/FT2, KG/YD2, T/M2, T/DM2, T/CM2, T/MM2, T/HM2, T/KM2, T/UM2, T/IN2, T/FT2, T/YD2]"
    },
    "density": {
      "uri": "/api/density",
      "about": "This endpoint converts density measurement units.",
      "units": "[N/M3, N/DM3, N/CM3, N/MM3, N/HM3, N/KM3, N/UM3, N/IN3, N/FT3, N/YD3, N/ML, N/L, N/GAL, KN/M3, KN/DM3, KN/CM3, KN/MM3, KN/HM3, KN/KM3, KN/UM3, KN/IN3, KN/FT3, KN/YD3, KN/ML, KN/L, KN/GAL, MN/M3, MN/DM3, MN/CM3, MN/MM3, MN/HM3, MN/KM3, MN/UM3, MN/IN3, MN/FT3, MN/YD3, MN/ML, MN/L, MN/GAL, GN/M3, GN/DM3, GN/CM3, GN/MM3, GN/HM3, GN/KM3, GN/UM3, GN/IN3, GN/FT3, GN/YD3, GN/ML, GN/L, GN/GAL, TN/M3, TN/DM3, TN/CM3, TN/MM3, TN/HM3, TN/KM3, TN/UM3, TN/IN3, TN/FT3, TN/YD3, TN/ML, TN/L, TN/GAL, LB/M3, LB/DM3, LB/CM3, LB/MM3, LB/HM3, LB/KM3, LB/UM3, LB/IN3, LB/FT3, LB/YD3, LB/ML, LB/L, LB/GAL, OZ/M3, OZ/DM3, OZ/CM3, OZ/MM3, OZ/HM3, OZ/KM3, OZ/UM3, OZ/IN3, OZ/FT3, OZ/YD3, OZ/ML, OZ/L, OZ/GAL, POUND/M3, POUND/DM3, POUND/CM3, POUND/MM3, POUND/HM3, POUND/KM3, POUND/UM3, POUND/IN3, POUND/FT3, POUND/YD3, POUND/ML, POUND/L, POUND/GAL, KIP/M3, KIP/DM3, KIP/CM3, KIP/MM3, KIP/HM3, KIP/KM3, KIP/UM3, KIP/IN3, KIP/FT3, KIP/YD3, KIP/ML, KIP/L, KIP/GAL, GF/M3, GF/DM3, GF/CM3, GF/MM3, GF/HM3, GF/KM3, GF/UM3, GF/IN3, GF/FT3, GF/YD3, GF/ML, GF/L, GF/GAL, G/M3, G/DM3, G/CM3, G/MM3, G/HM3, G/KM3, G/UM3, G/IN3, G/FT3, G/YD3, G/ML, G/L, G/GAL, KGF/M3, KGF/DM3, KGF/CM3, KGF/MM3, KGF/HM3, KGF/KM3, KGF/UM3, KGF/IN3, KGF/FT3, KGF/YD3, KGF/ML, KGF/L, KGF/GAL, KG/M3, KG/DM3, KG/CM3, KG/MM3, KG/HM3, KG/KM3, KG/UM3, KG/IN3, KG/FT3, KG/YD3, KG/ML, KG/L, KG/GAL, T/M3, T/DM3, T/CM3, T/MM3, T/HM3, T/KM3, T/UM3, T/IN3, T/FT3, T/YD3, T/ML, T/L, T/GAL]"
    },
    "speed": {
      "uri": "/api/speed",
      "about": "This endpoint converts speed measurement units.",
      "units": "[M/S, M/MS, M/US, M/MIN, M/H, M/DAY, M/WEEK, M/MONTH, M/MONTH30, M/MONTH31, M/YEAR, DM/S, DM/MS, DM/US, DM/MIN, DM/H, DM/DAY, DM/WEEK, DM/MONTH, DM/MONTH30, DM/MONTH31, DM/YEAR, CM/S, CM/MS, CM/US, CM/MIN, CM/H, CM/DAY, CM/WEEK, CM/MONTH, CM/MONTH30, CM/MONTH31, CM/YEAR, MM/S, MM/MS, MM/US, MM/MIN, MM/H, MM/DAY, MM/WEEK, MM/MONTH, MM/MONTH30, MM/MONTH31, MM/YEAR, HM/S, HM/MS, HM/US, HM/MIN, HM/H, HM/DAY, HM/WEEK, HM/MONTH, HM/MONTH30, HM/MONTH31, HM/YEAR, KM/S, KM/MS, KM/US, KM/MIN, KM/H, KM/DAY, KM/WEEK, KM/MONTH, KM/MONTH30, KM/MONTH31, KM/YEAR, UM/S, UM/MS, UM/US, UM/MIN, UM/H, UM/DAY, UM/WEEK, UM/MONTH, UM/MONTH30, UM/MONTH31, UM/YEAR, IN/S, IN/MS, IN/US, IN/MIN, IN/H, IN/DAY, IN/WEEK, IN/MONTH, IN/MONTH30, IN/MONTH31, IN/YEAR, FT/S, FT/MS, FT/US, FT/MIN, FT/H, FT/DAY, FT/WEEK, FT/MONTH, FT/MONTH30, FT/MONTH31, FT/YEAR, YD/S, YD/MS, YD/US, YD/MIN, YD/H, YD/DAY, YD/WEEK, YD/MONTH, YD/MONTH30, YD/MONTH31, YD/YEAR]"
    }
  }
}
```
<br/>

<h3 align="center"><strong>STATUS</strong> </h3>

When entering any mentioned endpoint, it performs the appropriate conversion.

This same page will present the request based on 3 different statuses: 

* Info: If no parameter was given, it will show a dialog guiding you to an example of usage
* Error: If you typed invalid parameters, it will show you where you made the mistake.
* Success: Will show you the measurement unit converted.

<br/>



Info status:
> <b>Endpoint</b>: localhost:80/api/area
```json
{
  "header": {
    "status": "info",
    "input": "{value=null, type=null, target=null}",
    "uri": "/api/area",
    "home": "http://localhost:80"
  },
  "result": {
    "title": "This endpoint provides functionality to convert AREA measurement units.",
    "types": "[M2, DM2, CM2, MM2, HM2, KM2, UM2, IN2, FT2, YD2]",
    "example": "Check the example endpoint for a usage example.",
    "uri-example": "/example",
    "si": "Check the SI endpoint to convert the value to the International Standard",
    "uri-si": "/api/area/si"
  }
}
```
<br/>

Error status:
> <b>Endpoint</b>: localhost:80/api/length?value=5.55&type=INVALID
```json
{
  "header": {
    "status": "error",
    "input": "{value=5.55, type=INVALID, target=null}",
    "uri": "/api/length",
    "home": "http://localhost:80"
  },
  "result": {
    "ParameterException": "type INVALID not found.",
    "example": "If you dont know how to use this API, check the example endpoint.",
    "uri-example": "/example"
  }
}
```
<br/>

Sucess status:
> <b>Endpoint</b>: localhost:80/api/length?value=5.55&type=M&target=CM
```json
{
  "header": {
    "status": "success",
    "input": "{value=5.55, type=M, target=CM}",
    "uri": "/api/length",
    "home": "http://localhost:80"
  },
  "result": {
    "unit": "{value=555.0, type=CM}"
  }
}
```


### Example page
If you don't know how to use the API. just go to the /example end-point and it will prompt a full tutorial on how to use the API.
> <b>Endpoint</b>: localhost:80/example
```json
{
  "header": {
    "status": "info",
    "uri": "/example",
    "home": "http://localhost:80",
    "title": "This endpoint provides example in how to use this API to convert measurement units.",
    "description": "Given a quantity expressed in a unit type, the end-points returns the equivalent quantity in a different measurement unit."
  },
  "result": {
    "example": "How to convert 12345.67 M2 into DM2  ?",
    "GET  Request": "/api/area?value=12345.67&type=M2&target=DM2",
    "POST Request": "/api/area  Body: {'value': 12345.67, 'type': 'M2', 'target': 'DM2'}",
    "Response": "{ SUCCESS | ERROR | INFO }   {value=1234567.0, type=DM2}",
    "observation": "Parameters are resilient. Values can be represented using comma (1,23), dot (1.23), or contain noise (myVal is 1.23)",
    "observation2": "Types are also resilient. Types can be presented in: [ M2 ] or [ M² ] or [ M^2 ] or [ M_2 ] or [ M 2 ]..."
  }
}
```


Benchmark suggested that after Warmup, it can convert on average, 10.000 units per seconds.

Application licensed under the <a ref="https://github.com/victordalosto/UnitConversion/blob/master/LICENSE">MIT License</a>.
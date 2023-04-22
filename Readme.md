<h1 align="center"> Unit Converter </h1>

Unit Converter is a <strong> RESTful </strong> application that implements the Level 3 of the Richardson Maturity Model - HATEOAs.

The program was made in Spring and is used for conversion between various measurement units.

The units presented in this program are the most commonly used in engineering.
The program basically converts the Measurement Unit and its value into another equivalent Unit. 
<br/>


<h2 align="center"> How to use </h2>

The program is now hosted on docker-hub. Just use the command:
> docker run -d -p 8080:8080 victordalosto/unitconversion

</br>

The entire usage of the application is self explanatory, guided by resources and its URI.
> <b>Endpoint</b>: localhost:8080
```json
{
  "header": {
    "home": "http://localhost:8080",
    "title": "Unit Conversion API",
    "about": "API used for conversion between measurement units most commonly used in the engineering",
    "description": "Given a quantity expressed in a certain measurement unit, the endpoint returns equivalent quantitys expressed in a different measurement unit",
    "reference": "https://github.com/victordalosto/UnitConversion"
  },
  "result": {
    "area": {
      "uri": "/api/area",
      "about": "This endpoint converts area measurement units.",
      "units": "[DM2, MM2, M2, YD2, KM2, UM2, IN2, HM2, FT2, CM2]"
    },
    "force": {
      "uri": "/api/force",
      "about": "This endpoint converts force measurement units.",
      "units": "[KIP, TN, N, G, KN, MN, GN, LB, POUND, KG, T]"
    },
    "inertia": {
      "uri": "/api/inertia",
      "about": "This endpoint converts inertia measurement units.",
      "units": "[KM4, UM4, HM4, M4, DM4, IN4, MM4, FT4, CM4, YD4]"
    },
    "length": {
      "uri": "/api/length",
      "about": "This endpoint converts length measurement units.",
      "units": "[HM, FT, M, MM, KM, IN, DM, UM, CM, YD]"
    },
    "temperature": {
      "uri": "/api/temperature",
      "about": "This endpoint converts temperature measurement units.",
      "units": "[R, K, F, C]"
    },
    "time": {
      "uri": "/api/time",
      "about": "This endpoint converts time measurement units.",
      "units": "[US, YEAR, S, MS, H, MIN, WEEK, MONTH, MONTH31, DAY, MONTH30]"
    },
    "volume": {
      "uri": "/api/volume",
      "about": "This endpoint converts volume measurement units.",
      "units": "[KM3, HM3, FT3, MM3, M3, UM3, YD3, DM3, CM3, IN3, ML, L]"
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
> <b>Endpoint</b>: localhost:8080/api/area
```json
{
  "header": {
    "uri": "/api/area",
    "home": "http://localhost:8080",
    "input": "{value=null, type=null, target=null}"
  },
  "result": {
    "info": {
      "title": "This endpoint provides functionality to convert AREA measurement units.",
      "types": "[HM2, CM2, DM2, KM2, UM2, YD2, M2, MM2, IN2, FT2]",
      "about": "Check the example endpoint for a usage example.",
      "uri": "/example"
    }
  }
}
```
<br/>

Error status:
> <b>Endpoint</b>: localhost:8080/api/length?value=5.55&type=INVALID
```json
{
  "header": {
    "uri": "/api/length",
    "home": "http://localhost:8080",
    "input": "{value=5.55, type=INVALID, target=null}"
  },
  "result": {
    "error": {
      "ParameterException": "type INVALID not found.",
      "about": "Check the /example endpoint to verify the correct API usage.",
      "uri": "/example"
    }
  }
}
```
<br/>

Sucess status:
> <b>Endpoint</b>: localhost:8080/api/length?value=5.55&type=INVALID
```json
{
  "header": {
    "uri": "/api/length",
    "home": "http://localhost:8080",
    "input": "{value=5.55, type=M, target=CM}"
  },
  "result": {
    "sucess": {
      "unit": "{value=555.0, type=CM}"
    }
  }
}
```


<br/br/>

### Example page
If you don't know how to use the API. just go to the /example end-point and it will prompt a full tutorial on how to use the API.

```json
{
  "header": {
    "uri": "/example",
    "home": "http://localhost:8080",
    "title": "This endpoint provides example in how to use this API to convert measurement units.",
    "description": "Given a quantity expressed in a unit type, the end-points returns the equivalent quantity in a different measurement unit."
  },
  "result": {
    "info": {
      "example": "How to convert 12345.67 M2 into CM2  ?",
      "GET  Request": "/api/area?value=12345.67&type=M2&target=CM2",
      "POST Request": "/api/area  Body: {'value': 12345.67, 'type': 'M2', 'target': 'CM2'}",
      "Response": "{ SUCCESS | ERROR | INFO }   {value=1.234567E8, type=CM2}",
      "observation": "Parameters are resilient. Values can be represented using comma (1,23), dot (1.23), or contain noise (myVal is 1.23)",
      "observation2": "Types are also resilient. Types can be presented in: [ M2 ] or [ M² ] or [ M^2 ] or [ M_2 ] or [ M 2 ]..."
    }
  }
}
```

Application licensed under the <a ref="https://github.com/victordalosto/UnitConversion/blob/master/LICENSE">MIT License</a>.
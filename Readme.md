<h1 align="center"> Unit Converter </h1>

Unit Converter is a <strong> RESTful </strong> application, that implements the Level 3 of Richardson Maturity Model - HATEOAs, made in Spring, used for conversion between various measurement units.

The units presented in this program are the most commonly used in the engineering.
The program basically converts the Measurement Unit and its value into another equivalent Unit. 
<br/>


<h2 align="center"> How to use </h2>

The entirely usage of the application is guided by the resources and URI.
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
      "units": "[DM^2, MM^2, M^2, YD^2, KM^2, UM^2, IN^2, HM^2, FT^2, CM^2]"
    },
    "force": {
      "uri": "/api/force",
      "about": "This endpoint converts force measurement units.",
      "units": "[KIP, TN, N, G, KN, MN, GN, LB, POUND, KG, T]"
    },
    "inertia": {
      "uri": "/api/inertia",
      "about": "This endpoint converts inertia measurement units.",
      "units": "[KM^4, UM^4, HM^4, M^4, DM^4, IN^4, MM^4, FT^4, CM^4, YD^4]"
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
      "units": "[US, YEAR, S, MS, H, MIN, WEEK, MONTH, MONTH^31, DAY, MONTH^30]"
    },
    "volume": {
      "uri": "/api/volume",
      "about": "This endpoint converts volume measurement units.",
      "units": "[KM^3, HM^3, FT^3, MM^3, M^3, UM^3, YD^3, DM^3, CM^3, IN^3, ML, L]"
    }
  }
}
```
<br/>


When entering any mentioned endpoint to perform the appropriate conversion.
This same page will present the request based on 3 different status: 

* Info: If no parameter was given, it will show a dialog guiding you to a example of usage
* Error: If you typed invalid parameters, it will show you were you made the mistake.
* Sucess: Will show you the measurement unit converted.

<br/>

Info status:
> <b>Endpoint</b>: localhost:8080/api/length
```json
{
  "header": {
    "uri": "/api/length",
    "home": "http://localhost:8080",
    "input": "{value=null, type=null, target=null}"
  },
  "result": {
    "info": {
      "uri": "/api/length/example",
      "about": "Check the /example endpoint for a usage example."
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
      "uri": "/api/length/example"
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
    "uri": "/api/length/example",
    "home": "http://localhost:8080",
    "input": "{value=null, type=null, target=null}"
  },
  "result": {
    "info": {
      "ref": "/api/length",
      "title": "This endpoint provides functionality to convert LENGTH measurement units.",
      "description": "Given a quantity expressed in a unit type, the end-point returns the equivalent quantity in a different measurement unit.",
      "how-to": "Convert 12345.67 MM to KM",
      "GET": "/api/length?value=12345.67&type=MM&target=KM",
      "POST": "/api/length  Body ->  {'value': 12345.67, 'type': 'MM', 'target': 'KM'}",
      "result": "Contains a status of the request, and possibly the result of the conversion.",
      "status": "can be: { SUCESS | ERROR | INFO }",
      "unit": "Expressed in {value=0.01234567, type=KM}",
      "observation": "The params are resilients. Values can be separated using comma (1,23), dot (1.23) or contain noise (myValue=1.23)",
      "observation2": "Types are also resilient. Types can be presented in: [ M2 ] or [ M² ] or [ M^2 ] or [ M_2 ] or [ M 2 ]..."
    }
  }
}
```

Application licensed under the <a ref="https://github.com/victordalosto/UnitConversion/blob/master/LICENSE">MIT License</a>.
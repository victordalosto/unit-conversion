package dalosto.engineering.unitconversion.rest.domain;

import dalosto.engineering.unitconversion.service.ConversorService;

public class ExampleMessage {

    private EndpointInfo info;


    public ExampleMessage(EndpointInfo info) {
        this.info = info;
    }


    public String[] getMessage(ConversorService service) {
        String value = "12345.67";
        String type = info.getUnitFormula().getAllUnitTypesOfThisCategory().stream().findFirst().get().toString().replaceAll("_", "");
        String target = info.getUnitFormula().getAllUnitTypesOfThisCategory().stream().skip(1).findFirst().get().toString().replaceAll("_", "");
        String[] arr = new String[22];
        arr[0] = "ref";
        arr[1] = info.getURI();
        arr[2] = "title";
        arr[3] = "This endpoint provides functionality to convert " + info.getTitle().toUpperCase() + " measurement units.";
        arr[4] = "description";
        arr[5] = "Given a quantity expressed in a unit type, the end-point returns the equivalent quantity in a different measurement unit.";
        arr[6] = "how-to";
        arr[7] = "Convert " + value + " " + type + " to " + target;
        arr[8] = "GET";
        arr[9] = info.getURI() + "?value=" + value + "&type=" + type + "&target=" + target;
        arr[10] = "POST";
        arr[11] = info.getURI() + "  Body ->  {'value': " + value + ", 'type': '" + type + "', 'target': '" + target + "'}";
        arr[12] = "result";
        arr[13] = "Contains a status of the request, and possibly the result of the conversion.";
        arr[14] = "status";
        arr[15] = "can be: " +  RestStatus.possibleStatus() ;
        arr[16] = "unit";
        arr[17] = "Expressed in " + service.formatUnitDAOAndConvertToUnit(new UnitDAO(value, type, target), info.getUnitFormula());
        arr[18] = "observation";
        arr[19] = "The params are resilient. Values can be separated using comma (1,23), dot (1.23), or contain noise (myValue=1.23)";
        arr[20] = "observation2";
        arr[21] = "Types are also resilient. Types can be presented in: [ M2 ] or [ M² ] or [ M^2 ] or [ M_2 ] or [ M 2 ]...";
        return arr;
    }

}

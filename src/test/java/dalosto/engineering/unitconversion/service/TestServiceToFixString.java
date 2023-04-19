package dalosto.engineering.unitconversion.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestServiceToFixString {

    @Autowired
    private MapUnitTypeService mapUnitTypeService;


    @Test
    void serviceShouldThowAErrorIfStringIsEmpty() {
        assert (mapUnitTypeService.fixStringToFindUnityType("").equals(""));
    }


    @Test
    void serviceShouldMakeUpperCaseOfInputs() {
        assert (mapUnitTypeService.fixStringToFindUnityType("mM").equals("MM"));
    }


    @Test
    void serviceShouldFixStringContainingSpaces() {
        assert (mapUnitTypeService.fixStringToFindUnityType("  M  ").equals("M"));
        assert (mapUnitTypeService.fixStringToFindUnityType(" M  _  2 ").equals("M_2"));
        assert (mapUnitTypeService.fixStringToFindUnityType(" M _ 2 ").equals("M_2"));
    }


    @Test
    void serviceShouldFixStringThatDoesntContainsExponential() {
        assert (mapUnitTypeService.fixStringToFindUnityType("M").equals("M"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M2").equals("M_2"));
        assert (mapUnitTypeService.fixStringToFindUnityType("ME2").equals("M_2"));
        assert (mapUnitTypeService.fixStringToFindUnityType("ME3").equals("M_3"));
    }


    @Test
    void serviceShouldFixStringContaingEspecialCharacter() {
        assert (mapUnitTypeService.fixStringToFindUnityType("M!@#!").equals("M"));
        assert (mapUnitTypeService.fixStringToFindUnityType("!@#$%¨&&*()M_!@*#*!@)2!@#$%¨&*()").equals("M_2"));
        assert (mapUnitTypeService.fixStringToFindUnityType("!@#M~^!@#___2!").equals("M_2"));
    }


    @Test
    void serviceShouldFixStringToMapExponentialSymbol() {
        assert (mapUnitTypeService.fixStringToFindUnityType("M^2").equals("M_2"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M^^2").equals("M_2"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M^^^2").equals("M_2"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M~2").equals("M_2"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M~~2").equals("M_2"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M-2").equals("M_2"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M--2").equals("M_2"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M_2").equals("M_2"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M__2").equals("M_2"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M~^2").equals("M_2"));
    }


    @Test
    void serviceShouldFixStringContainingNumbersSuperScriptAndSubscript() {
        assert (mapUnitTypeService.fixStringToFindUnityType("M1").equals("M"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M¹").equals("M"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M²").equals("M_2"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M³").equals("M_3"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M⁴").equals("M_4"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M₁").equals("M"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M₂").equals("M_2"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M₃").equals("M_3"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M₄").equals("M_4"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M0").equals("M"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M⁰").equals("M"));
        assert (mapUnitTypeService.fixStringToFindUnityType("M₀").equals("M"));
    }

}

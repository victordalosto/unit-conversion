package dalosto.engineering.unitconversion.controller;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;
import dalosto.engineering.unitconversion.units.Length;


@RestController
@RequestMapping("/length")
public class LengthController {

    @Autowired
    @Qualifier("LengthFormula")
    UnitFormula unitFormula;



    @RequestMapping
    public Set<UnitType> home(@Valid Unit unit, BindingResult bindingResult) {
        Unit unita = new Unit(5, Length.Types.HM);
        System.out.println(unita);
        System.out.println(unit + "\n\n");
        if (bindingResult.hasErrors()) {
            return Length.Types.M.getAllUnitTypesOfThisCategory();
        }
        return null;
    }


}

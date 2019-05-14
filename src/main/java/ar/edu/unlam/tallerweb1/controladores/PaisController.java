package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.servicios.PaisServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.inject.Inject;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class PaisController {

    @Inject
    private PaisServices paisServices;

    private List lista = new ArrayList<String>();

/*    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String listPais(Model model) {
        model.addAttribute("paisesList", paisServices.getAllPais());
        return "home";
    }*/

    // pasar query string a la ruta y muestra en pantalla. Lo hace por requestParams
/*    @RequestMapping(value = "/saludar", method = RequestMethod.GET)
    public ModelAndView saludar (@RequestParam String name, @RequestParam String lastname) {
        ModelMap model = new ModelMap();
        model.put("name", name);
        model.put("lastname", lastname);
        return new ModelAndView("saludar", model);
    }*/

    // pasar parametros como slugs con PathVariable
    @RequestMapping("/saludar/{lastname}")
    public ModelAndView saludarPersona(@PathVariable String lastname) {
        ModelMap model = new ModelMap();
        lista.add(lastname);
        model.addAttribute("lista", lista);
        return new ModelAndView("saludar", model);
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView listPais() {
        ModelMap model = new ModelMap();
        model.addAttribute("paisesList", paisServices.getAllPais());
        return new ModelAndView("home", model);
    }

    @RequestMapping(value = "/addPais", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("add", "pais", new Pais());
    }

    @RequestMapping(value = "/addPais", method = RequestMethod.POST)
    public String submit(@ModelAttribute("pais")Pais pais,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        model.addAttribute("nombre", pais.getNombre());
        model.addAttribute("capital", pais.getCapital());
        model.addAttribute("id", pais.getId());
        model.addAttribute("habitantes", pais.getHabitantes());
        model.addAttribute("idioma", pais.getIdioma());
        model.addAttribute("continente", pais.getContinente());
        return "add";
    }

}

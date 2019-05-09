package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.servicios.PaisServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.inject.Inject;

@Controller
public class PaisController {

    @Inject
    private PaisServices paisServices;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String listPais(Model model) {
        model.addAttribute("paisesList", paisServices.getAllPais());
        return "home";
    }

    @RequestMapping(value = "/addPais", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("add", "pais", new Pais());
    }

    @RequestMapping(value = "/addPais", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("pais")Pais pais,
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

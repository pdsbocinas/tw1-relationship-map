package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaisController {

    @RequestMapping("/{operacion}/{palabra}")
    public ModelAndView convertirPalabra(@PathVariable String palabra, @PathVariable String operacion) {

        ModelMap model = new ModelMap();

        model.put("palabra", palabra);
        model.put("operacion", operacion);

        String resultado;

        switch(operacion){
            case "mayuscula":
                resultado = palabra.toUpperCase();
                model.put("resultado", resultado);
                break;
            case "minuscula":
                resultado = palabra.toLowerCase();
                model.put("resultado", resultado);
                break;
            case "invertir":
                resultado = new StringBuilder(palabra).reverse().toString();
                model.put("resultado", resultado);
                break;

            case "length":
                Integer cantidad = null;
                cantidad = palabra.length();
                model.put("resultado", cantidad);
                break;

            default:
                resultado = "operacion invalida";
                model.put("resultado", resultado);
        }

        return new ModelAndView("convertir-palabra", model);
    }

}

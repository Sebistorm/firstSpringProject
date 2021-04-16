package com.example.firstspringproject.Controller;

import com.example.firstspringproject.Model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;

import java.text.DecimalFormat;
import java.util.Objects;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "home/index";
    }

    @PostMapping("/personData")
    public String personData(@ModelAttribute Person person, Model model) {
        model.addAttribute("PersonData", person);
        return "home/showPersonData";
    }

    @GetMapping("/converter")
    public String converter() {
        return "home/converter";
    }

    @RequestMapping(value = "/convert", method = RequestMethod.POST, params = "c2f")
    public String c2f(WebRequest wr, Model model) {
        int userInput = Integer.parseInt(Objects.requireNonNull(wr.getParameter("converter")));
        double total = (userInput * 1.8) + 32;
        model.addAttribute("total", total);
        return "home/converter";
    }

    @RequestMapping(value = "/convert", method = RequestMethod.POST, params = "f2c")
    public String f2c(WebRequest wr, Model model) {
        int userInput = Integer.parseInt(Objects.requireNonNull(wr.getParameter("converter")));
        DecimalFormat df = new DecimalFormat("##.##");
        String total = df.format((userInput - 32) / 1.8);
        model.addAttribute("total", total);
        return "home/converter";
    }
}

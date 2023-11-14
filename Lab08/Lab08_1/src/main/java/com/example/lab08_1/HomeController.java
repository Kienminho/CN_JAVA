package com.example.lab08_1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {return "index";}

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {return "about";}

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact() {return "contact";}

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public ModelAndView contactPost(@RequestParam(value = "email") String email, @RequestParam(value = "description") String des) {
        ModelAndView mv = new ModelAndView("contact");
        mv.addObject("email", email);
        mv.addObject("description", des);
        return mv;
    }
}

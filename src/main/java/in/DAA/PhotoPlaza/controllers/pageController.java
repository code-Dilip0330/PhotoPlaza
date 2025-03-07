package in.DAA.PhotoPlaza.controllers;

import in.DAA.PhotoPlaza.dto.ProfileDetailsDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pageController {
    @GetMapping("/navbar")
    public String showIndex(){
        return "page/navbar";
    }
    @GetMapping("/")
    public String showLandingPage(){
        return "page/landingPage";
    }

}
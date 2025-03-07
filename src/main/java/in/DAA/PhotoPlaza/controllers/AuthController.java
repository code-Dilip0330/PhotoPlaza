package in.DAA.PhotoPlaza.controllers;

import in.DAA.PhotoPlaza.dto.LoginDto;
import in.DAA.PhotoPlaza.dto.UserDto;
import in.DAA.PhotoPlaza.entites.User;
import in.DAA.PhotoPlaza.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public  class AuthController {

        private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String register(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user",user);
        model.addAttribute("section","register");
        return "page/landingPage";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserDto userDto , BindingResult result, Model model){
//         Valid password strength
//        if(userDto.getPassword().length() < 8){
//            result.rejectValue("password","error.password","Password must contain at least 8 characters");
//        }

//       check duplicate  and existing user
        Optional<User> existingUser = userService.findUserByEmail(userDto.getEmail());
        if(existingUser.isPresent() && existingUser.get().getEmail() != null && !existingUser.get().getEmail().isEmpty()){
            result.rejectValue("email","error.user","This email is already registered");
        }
        //        form validation
        if (result.hasErrors()){
            model.addAttribute("user",userDto);
            model.addAttribute("section", "register");
            return "page/landingPage";
        }
        userService.saveUser(userDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginDto", new LoginDto());
        model.addAttribute("section","login");
        return "page/landingPage";
    }

}
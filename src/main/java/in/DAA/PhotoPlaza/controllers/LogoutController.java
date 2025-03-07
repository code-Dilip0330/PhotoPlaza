package in.DAA.PhotoPlaza.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController {

    // logout users
    @GetMapping("/logout-page")
    public String showLogout() {
        return "auth/logout";
    }

    // Handles the actual logout
    @PostMapping("/logout-action")
    public String logoutAction(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, null);
        return "redirect:/login?logout=true"; // Redirect to login page
    }

}




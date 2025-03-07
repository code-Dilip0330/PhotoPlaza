package in.DAA.PhotoPlaza.security;

import in.DAA.PhotoPlaza.entites.User;
import in.DAA.PhotoPlaza.entites.UserPrincipal;
import in.DAA.PhotoPlaza.services.ProfileDetailsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private ProfileDetailsService profileDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
            ) throws IOException, ServletException {
        logger.info("CustomAuthSuccessHandler called for user: {}", authentication.getName());
        // Get the authenticated user
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();
        // Check if the user has a profile
        boolean hasProfile = profileDetailsService.hasProfile(user.getId());
        if(!hasProfile){
            response.sendRedirect("/createProfile");
            return;
        } else {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for(GrantedAuthority authority : authorities){
                if (authority.getAuthority().equals("ROLE_ADMIN")){
                    response.sendRedirect("/admin/dashboard");
                    return;
                } else if(authority.getAuthority().equals("ROLE_SELLER")){
                    response.sendRedirect("/photographer/dashboard");
                    return;
                } else if(authority.getAuthority().equals("ROLE_USER")){
                    response.sendRedirect("/user/dashboard");
                    return;
                }
            }
/*            // Check for SELLER before USER
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    response.sendRedirect("/admin/dashboard");
                    return;
                } else if (authority.getAuthority().equals("ROLE_SELLER")) { // Check SELLER first
                    response.sendRedirect("/photographer/dashboard");
                    return;
                }
            }

            // Fallback to USER or other roles
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals("ROLE_USER")) {
//                    response.sendRedirect("/createProfile");
                response.sendRedirect("/user/dashboard");
                    return;
                }
            }*/
        }
//        Default for no roles match
        response.sendRedirect("/");
        return;
    }
}


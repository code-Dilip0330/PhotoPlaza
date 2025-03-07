package in.DAA.PhotoPlaza.config;

import in.DAA.PhotoPlaza.security.CustomAuthSuccessHandler;
import in.DAA.PhotoPlaza.security.CustomAuthFailureHandler;
import in.DAA.PhotoPlaza.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//        disable
//        httpSecurity.csrf(AbstractHttpConfigurer::disable);
//        this is for authorize url
        httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/user/**").authenticated();
            auth.requestMatchers("/photographer/**").authenticated();
//            auth.requestMatchers("/login").authenticated();
            auth.requestMatchers("/admin/**").authenticated();
            auth.anyRequest().permitAll();
        });

        httpSecurity.formLogin(form ->{
            form.loginPage("/login"); //Custom login page
            form.loginProcessingUrl("/authenticate");
//            form.defaultSuccessUrl("/admin", true); // Redirect with GET
//            form.failureForwardUrl("/login?error=true");
            form.failureUrl("/login?error=true"); //  Fixed
            form.usernameParameter("userNameOrEmail");
            form.passwordParameter("password");
            form.successHandler(authenticationSuccessHandler());
            form.failureHandler(authenticationFailureHandler());
        });

//        httpSecurity.formLogin(form ->{
//            form.loginPage("/login"); //Custom login page
//            form.loginProcessingUrl("/authenticate");
//            form.defaultSuccessUrl("/admin", true); // Redirect with GET
////            form.failureForwardUrl("/login?error=true");
//            form.failureUrl("/login?error=true"); //  Fixed
//            form.usernameParameter("userNameOrEmail");
//            form.passwordParameter("password");
//            form.successHandler(authenticationSuccessHandler());
//            form.failureHandler(authenticationFailureHandler());
//        });

        httpSecurity.logout(logout -> {
            logout.logoutUrl("/logout-action")
                    .logoutSuccessUrl("/?logout=true")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");
        });
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new CustomAuthSuccessHandler();
    }
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new CustomAuthFailureHandler();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
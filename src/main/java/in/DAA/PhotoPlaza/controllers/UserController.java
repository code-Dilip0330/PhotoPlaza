package in.DAA.PhotoPlaza.controllers;

import in.DAA.PhotoPlaza.dto.BusinessAccountDto;
import in.DAA.PhotoPlaza.dto.ProductDto;
import in.DAA.PhotoPlaza.dto.ProfileDetailsDto;
import in.DAA.PhotoPlaza.dto.UserDto;
import in.DAA.PhotoPlaza.entites.User;
import in.DAA.PhotoPlaza.entites.UserPrincipal;
import in.DAA.PhotoPlaza.repositories.UserRepository;
import in.DAA.PhotoPlaza.exceptions.ResourceNotFoundException;
import in.DAA.PhotoPlaza.services.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private ProfileDetailsService profileDetailsService;
    private UserService userService;
    private UserRepository userRepository;
    private  BusinessAccountService businessAccountService;
    private ProductService productService;

    @ModelAttribute
    public void showProfile(Model model, Authentication authentication) {
        if (authentication != null) {
            // Get the UserPrincipal from the Authentication object
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            // Get the User entity from UserPrincipal
            User user = userPrincipal.getUser(); // Assuming you have getUser() method
            // Get the user ID
            Long userId = user.getId();

            ProfileDetailsDto profileDetailsDto;
            try {
                profileDetailsDto = profileDetailsService.getProfileDetailsById(userId);
            } catch (ResourceNotFoundException e) {
                // If no profile is found, initialize a new ProfileDetailsDto
                profileDetailsDto = new ProfileDetailsDto();
            }
//            ProfileDetailsDto profileDetailsDto = profileDetailsService.getProfileDetailsById(userId);
//            if (profileDetailsDto == null) {
//                profileDetailsDto = new ProfileDetailsDto();
////                throw new IllegalArgumentException("Product with ID " + userId + " not found!");
//            }
            UserDto userDto = userService.findUserById(userId);

            model.addAttribute("profile", profileDetailsDto);
            model.addAttribute("name", userDto);
        }
    }
    @ModelAttribute
    public void showRandomProducts(
            @RequestParam(defaultValue = "29") int limit, // Number of random products to fetch
            Model model) {
        List<ProductDto> products = productService.getRandomProducts(limit);
        model.addAttribute("products", products);
    }

    @GetMapping("/dashboard")
    public String showUserPage(){
        return "user/dashboard";
    }

    //  profile Details
    @GetMapping("/profileDetails")
    public String showProfileDetails(Model model){
        // Initialize a Profile object if not already present
        model.addAttribute("profile", new ProfileDetailsDto());
        model.addAttribute("section","profileDetails");
        return "user/dashboard";
//        return "auth/profileDetails";
    }
    @PostMapping("/profileDetails")
    public String saveProfileDetails(
            @ModelAttribute("profile") ProfileDetailsDto profileDetailsDto,
            Authentication authentication
    ) throws IOException {
        // Get the authenticated user from the Authentication object
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();

        // Set the user ID in the profile details
        profileDetailsDto.setUserId(user.getId());

        // Convert MultipartFile to byte[] and set it in the DTO
        if (profileDetailsDto.getImage() != null && !profileDetailsDto.getImage().isEmpty()) {
            byte[] imageBytes = profileDetailsDto.getImage().getBytes();
            profileDetailsDto.setImageBytes(imageBytes);
        }

        // Save the profile details
        profileDetailsService.CreateProfileDetails(profileDetailsDto, user);

        // Redirect to the user dashboard
        return "redirect:/user/dashboard";
    }
    /*@PostMapping("/profileDetails")
    public String saveProfileDetails(@ModelAttribute("profile")ProfileDetailsDto profileDetailsDto,Authentication authentication)throws IOException{
        // Get the UserPrincipal first
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        // Then get the actual User entity from UserPrincipal
        User user = userPrincipal.getUser(); // Assuming you have getUser() method

        //        Retrieve current authenticated user
        authentication = SecurityContextHolder.getContext().getAuthentication();

        String usernameOrEmail = authentication.getName();

//        User user = userRepository.findByUserNameIgnoreCaseOrEmailIgnoreCase(usernameOrEmail ,usernameOrEmail)
//                .orElseThrow(() -> new RuntimeException("User not found"));
        // Add debug logging
        System.out.println("Authentication object: " + authentication);
        System.out.println("Username/Email from auth: " + usernameOrEmail);

        Optional<User> userOptional = userRepository.findByUserNameIgnoreCaseOrEmailIgnoreCase(usernameOrEmail, usernameOrEmail);

        // Add more debug logging
        System.out.println("User found in DB: " + userOptional.isPresent());

        user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));

        profileDetailsDto.setUserId(user.getId());

//        Convert MultipartFile to byte[] and set it in the entity
        byte[] imagesBytes = profileDetailsDto.getImage().getBytes();
        profileDetailsDto.setImageBytes(imagesBytes);
        profileDetailsService.CreateProfileDetails(profileDetailsDto,user);
        return "redirect:/user/dashboard";
    }*/
    //    switchAccount
    @GetMapping("/switchAccount")
    public String showBusinessAccount(Model model, Authentication authentication){
        // Get the UserPrincipal first
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        // Then get the actual User entity from UserPrincipal
        User user = userPrincipal.getUser();
        if(businessAccountService.hasBusinessAccount(user)){
            return "redirect:/photographer/dashboard";
        }
        // If no business account exists, show the form
        model.addAttribute("businessAccount", new BusinessAccountDto());
        return "auth/BusinessAccount";
    }
    @PostMapping("/switchAccount")
    public String saveBusinessAccount(@ModelAttribute(
            "businessAccount") BusinessAccountDto businessAccountDto,
            Authentication authentication)
    throws IOException{
        // Get the UserPrincipal first
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        // Then get the actual User entity from UserPrincipal
        User user = userPrincipal.getUser(); // Assuming you have getUser() method

//        //        Retrieve current authenticated user
//        authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        String usernameOrEmail = authentication.getName();

//        User user = userRepository.findByUserNameIgnoreCaseOrEmailIgnoreCase(usernameOrEmail ,usernameOrEmail)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        // Add debug logging
//        System.out.println("Authentication object: " + authentication);
//        System.out.println("Username/Email from auth: " + usernameOrEmail);

//        Optional<User> userOptional = userRepository.findByUserNameIgnoreCaseOrEmailIgnoreCase(usernameOrEmail, usernameOrEmail);

        // Add more debug logging
//        System.out.println("User found in DB: " + userOptional.isPresent());

//        user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));

        businessAccountDto.setUserId(user.getId());

        businessAccountService.createBusinessAccount(businessAccountDto,user);

        return "redirect:/photographer/dashboard";
    }
}

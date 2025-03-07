package in.DAA.PhotoPlaza.controllers;

import in.DAA.PhotoPlaza.dto.ProfileDetailsDto;
import in.DAA.PhotoPlaza.services.ProductService;
import in.DAA.PhotoPlaza.services.ProfileDetailsService;
import in.DAA.PhotoPlaza.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ProfileController {

    private ProfileDetailsService profileDetailsService;
    private UserService userService;
    private ProductService productService;

    @GetMapping("/createProfile")
    public String ProfileDetails(Model model){
        // Initialize a Profile object if not already present
        model.addAttribute("profile", new ProfileDetailsDto());
        return "auth/createProfile";
    }

//    @GetMapping
//    public String showProfile( Model model,Authentication authentication){
//        // Get the UserPrincipal from the Authentication object
//        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//
//        // Get the User entity from UserPrincipal
//        User user = userPrincipal.getUser(); // Assuming you have getUser() method
//
//        // Get the user ID
//        Long userId = user.getId();
//
//        ProfileDetailsDto profileDetailsDto = profileDetailsService.getProfileDetailsById(userId);
//        if (profileDetailsDto == null) {
//            throw new IllegalArgumentException("Product with ID " + userId + " not found!");
//        }
//        model.addAttribute("profile",profileDetailsDto);
//
//        UserDto userDto = userService.findUserById(userId);
//        model.addAttribute("name", userDto);
//
//        long numberOfProducts = productService.getNumberOfProductByUserId(userId);
////        determine performance
//        String userLevel = determineUserLevel(numberOfProducts);
//        model.addAttribute("performance",userLevel);
//
//        return "photographer/photographerDashboard";
//    }
//
//    private String determineUserLevel(long numberOfProducts) {
//        if (numberOfProducts >= 50) {
//            return "Platinum Member";
//        } else if (numberOfProducts >= 30) {
//            return "Gold Member";
//        } else if (numberOfProducts >= 10) {
//            return "Silver Member";
//        } else {
//            return "Bronze Member";
//        }
//    }
}

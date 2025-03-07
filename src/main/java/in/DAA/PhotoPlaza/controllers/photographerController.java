package in.DAA.PhotoPlaza.controllers;

import in.DAA.PhotoPlaza.dto.CategoryDto;
import in.DAA.PhotoPlaza.dto.ProductDto;
import in.DAA.PhotoPlaza.dto.ProfileDetailsDto;
import in.DAA.PhotoPlaza.dto.UserDto;
import in.DAA.PhotoPlaza.entites.User;
import in.DAA.PhotoPlaza.entites.UserPrincipal;
import in.DAA.PhotoPlaza.repositories.UserRepository;
import in.DAA.PhotoPlaza.services.CategoryService;
import in.DAA.PhotoPlaza.services.ProductService;
import in.DAA.PhotoPlaza.services.ProfileDetailsService;
import in.DAA.PhotoPlaza.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/photographer")
//@PreAuthorize("hasRole('PHOTOGRAPHER')")
@AllArgsConstructor
public class photographerController {

    private ProfileDetailsService profileDetailsService;
    private UserService userService;
    private CategoryService categoryService;
    private ProductService productService;
    private UserRepository userRepository;

    @ModelAttribute
    public void showProfile(Model model, Authentication authentication) {
        if (authentication != null){
            // Get the UserPrincipal from the Authentication object
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            // Get the User entity from UserPrincipal
            User user = userPrincipal.getUser(); // Assuming you have getUser() method
            // Get the user ID
            Long userId = user.getId();

            ProfileDetailsDto profileDetailsDto = profileDetailsService.getProfileDetailsById(userId);
            if (profileDetailsDto == null) {
                throw new IllegalArgumentException("Product with ID " + userId + " not found!");
            }

            UserDto userDto = userService.findUserById(userId);

            long numberOfProducts = productService.getNumberOfProductByUserId(userId);
//        determine performance
            String userLevel = determineUserLevel(numberOfProducts);

            model.addAttribute("profile", profileDetailsDto);
            model.addAttribute("name", userDto);
            model.addAttribute("performance", userLevel);
        }
    }
    private String determineUserLevel(long numberOfProducts) {
        if (numberOfProducts >= 50) {
            return "Platinum Member";
        } else if (numberOfProducts >= 30) {
            return "Gold Member";
        } else if (numberOfProducts >= 10) {
            return "Silver Member";
        } else {
            return "Bronze Member";
        }
    }
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("section", "dashboard");
        return "photographer/photographerDashboard";
    }
    @GetMapping("/manageProduct")
    public String showManageProduct(Model model) {
//        Retrieve current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String usernameOrEmail = authentication.getName();

        // Debug: Print the logged-in user's email/username
        System.out.println("Logged-in user's email/username: " + usernameOrEmail);

        List<User> users = userRepository.findAll();
        users.forEach(user -> System.out.println(user.getEmail() + " - " + user.getUserName() + " _ " + user.getId()));

//        User user = userRepository.findByEmailIgnoreCase(usernameOrEmail)
        User user = userRepository.findByUserNameIgnoreCaseOrEmailIgnoreCase(usernameOrEmail ,usernameOrEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

//        Get user ID
        Long userId = user.getId();

        List<ProductDto> products = productService.getProductByUserId(userId);
        model.addAttribute("products",products);
        model.addAttribute("section", "manageProduct");
        return "photographer/photographerDashboard";
    }

    @GetMapping("/manageProduct/product")
    public String showAddProduct(Model model){
        ProductDto productDto = new ProductDto();
        model.addAttribute("product", productDto);
        List<CategoryDto> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("section","addProduct");
        return "photographer/photographerDashboard";
    }
    @GetMapping("/manageCategory")
    public String showMangeCategory(Model model) {
        model.addAttribute("section", "manageCategory");
        return "photographer/photographerDashboard";
    }
    @GetMapping("/viewOrder")
    public String showViewOrder(Model model) {
        model.addAttribute("section", "viewOrder");
        return "photographer/photographerDashboard";
    }
    @GetMapping("/customer")
    public String showCustomer(Model model) {
        model.addAttribute("section", "customer");
        return "photographer/photographerDashboard";
    }
    @GetMapping("/sales")
    public String showSales(Model model) {
        model.addAttribute("section", "sales");
        return "photographer/photographerDashboard";
    }
    @GetMapping("/commission")
    public String showCommission(Model model) {
        model.addAttribute("section", "commission");
        return "photographer/photographerDashboard";
    }
    @GetMapping("/analytics")
    public String showAnalytics(Model model) {
        model.addAttribute("section", "analytics");
        return "photographer/photographerDashboard";
    }
    @GetMapping("/loyaltyTiers")
    public String showLoyaltyTiers(Model model) {
        model.addAttribute("section", "loyaltyTiers");
        return "photographer/photographerDashboard";
    }
    @GetMapping("/notification")
    public String showNotification(Model model) {
        model.addAttribute("section", "notification");
        return "photographer/photographerDashboard";
    }
    @GetMapping("/setting")
    public String showSetting(Model model) {
        model.addAttribute("section", "setting");
        return "photographer/photographerDashboard";
    }
    @GetMapping("/logout-page")
    public String showLogout(Model model) {
        model.addAttribute("section", "logout");
        return "photographer/photographerDashboard";
    }

    @PostMapping("/newProduct")
    public String saveNewProduct(@ModelAttribute("product") ProductDto productDto ) throws IOException {
//        Retrieve current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String usernameOrEmail = authentication.getName();

        // Debug: Print the logged-in user's email/username
        System.out.println("Logged-in user's email/username: " + usernameOrEmail);

        List<User> users = userRepository.findAll();
        users.forEach(user -> System.out.println(user.getEmail() + " - " + user.getUserName() + " _ " + user.getId()));

//        User user = userRepository.findByEmailIgnoreCase(usernameOrEmail)
        User user = userRepository.findByUserNameIgnoreCaseOrEmailIgnoreCase(usernameOrEmail ,usernameOrEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

//        Set userId in the productDto
        productDto.setUserId(user.getId());

        // Convert MultipartFile to byte[] and set it in the entity
        byte[] imageBytes = productDto.getImage().getBytes();
        productDto.setImageBytes(imageBytes);
        productService.createProduct(productDto);
        return "redirect:/photographer/manageProduct";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return "redirect:/photographer/manageProduct";
    }
//    @GetMapping("/editProduct")
//    public String showUpdateProduct(Model model) {
//        model.addAttribute("section", "edit");
//        return "photographer/photographerDashboard";
//    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model){
        ProductDto productDto = productService.getProductById(id);
        if (productDto == null) {
            throw new IllegalArgumentException("Product with ID " + id + " not found!");
        }
        model.addAttribute("product",productDto);
        List<CategoryDto> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("section", "updateProduct");
        return "photographer/photographerDashboard";
    }

    @PostMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute("product") ProductDto productDto, @RequestParam("image")MultipartFile image) throws IOException{
        if (!image.isEmpty()) {
            // If a new image is uploaded, convert it to byte[]
            productDto.setImageBytes(image.getBytes());
        } else {
            // If no new image is uploaded, retain the existing image
            ProductDto existingItemDto = productService.getProductById(id);
            productDto.setImageBytes(existingItemDto.getImageBytes());
        }
        productDto.setId(id);
        productService.updateProduct(id, productDto);
        return "redirect:/photographer/manageProduct";
    }
}

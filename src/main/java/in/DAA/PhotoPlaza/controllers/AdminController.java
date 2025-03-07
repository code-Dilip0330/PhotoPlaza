package in.DAA.PhotoPlaza.controllers;

import in.DAA.PhotoPlaza.dto.CategoryDto;
import in.DAA.PhotoPlaza.dto.ProductDto;
import in.DAA.PhotoPlaza.dto.ProfileDetailsDto;
import in.DAA.PhotoPlaza.dto.UserDto;
import in.DAA.PhotoPlaza.entites.User;
import in.DAA.PhotoPlaza.entites.UserPrincipal;
import in.DAA.PhotoPlaza.repositories.ProductRepository;
import in.DAA.PhotoPlaza.repositories.UserRepository;
import in.DAA.PhotoPlaza.services.CategoryService;
import in.DAA.PhotoPlaza.services.ProductService;
import in.DAA.PhotoPlaza.services.ProfileDetailsService;
import in.DAA.PhotoPlaza.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

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
    public String  showDashboard(Model model){
        model.addAttribute("section","dashboard" );
        return "admin/adminDashboard";
    }

//    @GetMapping("/manageProduct")
//    public String showMangeProduct(Model model) {
//        model.addAttribute("section", "manageProduct");
//        return "admin/adminDashboard";
//    }
    @GetMapping("/manageProduct")
    public String showManageProduct(Model model) {
        List<ProductDto> products = productService.getAllProduct();
        model.addAttribute("products",products);
        model.addAttribute("section", "manageProduct");
        return "admin/adminDashboard";
    }
    @GetMapping("/manageCategory")
    public String showManageCategory(Model model) {
        List<CategoryDto> categories = categoryService.getAllCategory();
        model.addAttribute("categories",categories);
        model.addAttribute("section","manageCategory");
        return "admin/adminDashboard";
    }

    @GetMapping("/newCategory")
    public String addNewCategory(Model model){
        model.addAttribute("section","addCategory");
        return "admin/adminDashboard";
    }

    @PostMapping("/newCategory")
    public String saveNewCategory(@ModelAttribute("category") CategoryDto categoryDto) throws IOException {
        categoryService.createCategory(categoryDto);
        return "redirect:/admin/manageCategory";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return "redirect:/admin/manageCategory";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Long id , Model model){
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        model.addAttribute("category",categoryDto);
        model.addAttribute("section","updateCategory");
        return "admin/adminDashboard";
    }

    @PostMapping("/updateCategory/{id}")
    public String updateCategory(@PathVariable("id") Long id , @ModelAttribute("category") CategoryDto categoryDto){
        categoryDto.setId(id);
        categoryService.updateCategory(id, categoryDto);
        return "redirect:/admin/manageCategory";
    }

    @GetMapping("/manageOrder")
    public String showOrder(Model model) {
        model.addAttribute("section", "manageOrder");
        return "admin/adminDashboard";
    }

    @GetMapping("/customers")
    public String showCustomers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("customers",users);
        model.addAttribute("section","customers");
        return "admin/adminDashboard";
    }
    @GetMapping("/analytics")
    public String showAnalytics(Model model) {
        model.addAttribute("section", "analytics");
        return "admin/adminDashboard";
    }

    @GetMapping("/notification")
    public String showNotification(Model model) {
        model.addAttribute("section", "notification");
        return "admin/adminDashboard";
    }
    @GetMapping("/setting")
    public String showSetting(Model model) {
        model.addAttribute("section", "setting");
        return "admin/adminDashboard";
    }
    @GetMapping("/logout-page")
    public String showLogout(Model model) {
        model.addAttribute("section", "logout");
        return "admin/adminDashboard";
    }
}

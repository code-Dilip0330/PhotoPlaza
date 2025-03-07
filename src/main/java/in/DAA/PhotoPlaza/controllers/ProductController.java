package in.DAA.PhotoPlaza.controllers;

import in.DAA.PhotoPlaza.dto.ProductDto;
import in.DAA.PhotoPlaza.services.ProductService;
import in.DAA.PhotoPlaza.services.ProfileDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
@Controller
@AllArgsConstructor
public class ProductController {

//    @Autowired
    private ProductService productService;
    private ProfileDetailsService profileDetailsService;

    @GetMapping("/randomProductsByCategory")
    public String showRandomProductsByCategory(@RequestParam String category, @RequestParam(defaultValue = "10") int limit , Model model){
        List<ProductDto> products = productService.getRandomProductsByCategory(category,limit);
        model.addAttribute("products",products);
        model.addAttribute("section","randomProductByCategory");
        return "";
    }

    @GetMapping("/productsByCategory")
    public String showProductsByCategory(Model model) {
        Map<String, List<ProductDto>> productsByCategory = productService.getProductsGroupedByCategory();
        model.addAttribute("productsByCategory", productsByCategory);
        model.addAttribute("section", "productsByCategory");
        return "photographer/photographerDashboard";
    }

    @GetMapping("/randomProducts")
    public String showRandomProducts(
            @RequestParam(defaultValue = "29") int limit, // Number of random products to fetch
            Model model) {
        List<ProductDto> products = productService.getRandomProducts(limit);
        // Fetch profile details for each product's user
//        for(ProductDto product : products){
//            ProfileDetailsDto profileDetails = profileDetailsService.getProfileDetailsById(product.getUserId());
//            product.setProfileDetails(profileDetails);
//        }

        model.addAttribute("products", products);
        return "user/masonry";
    }

}
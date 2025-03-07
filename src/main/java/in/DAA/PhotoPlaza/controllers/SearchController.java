package in.DAA.PhotoPlaza.controllers;

import in.DAA.PhotoPlaza.entites.Product;
import in.DAA.PhotoPlaza.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class SearchController {

    private ProductService productService;

    @GetMapping("/search")
    public String searchProducts(@Param("keyword") String keyword, Model model){
        System.out.println("Keyword : "+keyword);
        List<Product> searchResults = productService.searchProducts(keyword);
        model.addAttribute("keyword",keyword);
        model.addAttribute("searchResults",searchResults);
        return "user/dashboard";
    }
}

package in.DAA.PhotoPlaza.services;

import in.DAA.PhotoPlaza.dto.ProductDto;
import in.DAA.PhotoPlaza.entites.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<Product> searchProducts(String keyword);

    List<ProductDto> getAllProduct();

    void createProduct(ProductDto productDto);

    ProductDto getProductById(Long id);

    void updateProduct(Long id , ProductDto productDto);

    void deleteProduct(Long id);

    List<ProductDto> getProductByUserId(Long userId);

    // Fetch random products within a category
    List<ProductDto> getRandomProductsByCategory(String category, int limit);

    // Fetch products grouped by category
    Map<String, List<ProductDto>> getProductsGroupedByCategory();

    // Fetch random products across all categories
    List<ProductDto> getRandomProducts(int limit);

    long getNumberOfProductByUserId(Long userId);
}

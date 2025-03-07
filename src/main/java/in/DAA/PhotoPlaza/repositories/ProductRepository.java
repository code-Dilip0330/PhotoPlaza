package in.DAA.PhotoPlaza.repositories;

import in.DAA.PhotoPlaza.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    // Fetch products by category
    List<Product> findByCategory(String category);
    List<Product> findByUserId(Long userId);
    long countByUserId(Long userId); // To count the number of products for a user

//    @Query (value ="SELECT * FROM Products "
//                  +"MATCH(title, description, category,keywords)"
//                  +"AGAINST (?1)",
//                  nativeQuery = true)
    @Query(value = "SELECT * FROM Products "
            + "WHERE MATCH(title, description, category, keywords) AGAINST (?1)",
            nativeQuery = true)
    List<Product> searchProducts(String keyword);
}

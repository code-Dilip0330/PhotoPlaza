package in.DAA.PhotoPlaza.services.impl;

import in.DAA.PhotoPlaza.dto.ProductDto;
import in.DAA.PhotoPlaza.entites.Product;
import in.DAA.PhotoPlaza.entites.User;
import in.DAA.PhotoPlaza.repositories.ProductRepository;
import in.DAA.PhotoPlaza.repositories.UserRepository;
import in.DAA.PhotoPlaza.services.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Override
    public List<Product> searchProducts(String keyword){
        return productRepository.searchProducts(keyword);
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map((product) -> {
                    ProductDto productDto = modelMapper.map(product, ProductDto.class);
                    if (product.getImage() != null) {
                        productDto.setImageBase64(Base64.getEncoder().encodeToString(product.getImage())); // Convert to Base64
                    }
                    return productDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductByUserId(Long userId) {
//        Fetch user by user ID
        List<Product> products = productRepository.findByUserId(userId);
//      Convert To DTOs
        return products.stream()
                .map((product) -> {
                    ProductDto productDto = modelMapper.map(product, ProductDto.class);
                    if (product.getImage() != null) {
                        productDto.setImageBase64(Base64.getEncoder().encodeToString(product.getImage())); // Convert to Base64
                    }
                    return productDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void createProduct(ProductDto productDto) {
//        the above code is just written for change Dto class into model class because repository only work with model(entity)
        Product product = modelMapper.map(productDto, Product.class);
        product.setImage(productDto.getImageBytes());
        product.setUploadDate(new Date());

//        validate userId is not null
        if (productDto.getUserId() == null){
            throw new IllegalArgumentException("User Id must not be null ");
        }

        // Ensure a valid user is assigned
        User user = userRepository.findById(productDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        product.setUser(user);
        productRepository.save(product);

    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        if (product.getImage() != null) {
            productDto.setImageBase64(Base64.getEncoder().encodeToString(product.getImage())); // Convert to Base64
        }
        return productDto;
    }

    @Override
    public void updateProduct(Long id, ProductDto productDto) {
        Product existingItem = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        // Update fields
        existingItem.setTitle(productDto.getTitle());
        existingItem.setPrice(productDto.getPrice());
        existingItem.setCategory(productDto.getCategory());
        existingItem.setDescription(productDto.getDescription());
        existingItem.setKeywords(productDto.getKeywords());

        // Update image only if a new one is provided
        if (productDto.getImageBytes() != null) {
            existingItem.setImage(productDto.getImageBytes());
        }
        existingItem.setUploadDate(new Date()); // Update upload date
        productRepository.save(existingItem);
    }

    @Override
    public long getNumberOfProductByUserId(Long userId) {
        return productRepository.countByUserId(userId);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


    /*Random photo retrieve*/
    @Override
    public List<ProductDto> getRandomProductsByCategory(String category, int limit) {
        List<Product> products = productRepository.findByCategory(category);
//        Shuffle the list to randomize the order
        Collections.shuffle(products);
//        limit the number of products to the specified limit
        if(products.size()>limit){
            products = products.subList(0,limit);
        }
//        convert to DTOs
        return products.stream()
                .map((product) -> {
                   ProductDto productDto = modelMapper.map(product,ProductDto.class);
                   if(product.getImage() != null){
                       productDto.setImageBase64(Base64.getEncoder()
                               .encodeToString(product.getImage()));
                   }
                   return productDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<ProductDto>> getProductsGroupedByCategory() {
        List<Product> products = productRepository.findAll();
        Map<String , List<ProductDto>> productsByCategory = new HashMap<>();
        for(Product product : products){
            ProductDto productDto = modelMapper.map(product ,ProductDto.class);
            if(product.getImage() != null){
                productDto.setImageBase64(Base64.getEncoder()
                        .encodeToString(product.getImage()));
            }
//            Group products by category
            productsByCategory.computeIfAbsent(product.getCategory(), k -> new ArrayList<>()).add(productDto);
        }
        return productsByCategory;
    }


    @Override
    public List<ProductDto> getRandomProducts(int limit) {
        List<Product> products = productRepository.findAll();
//        Randomize the list
        Collections.shuffle(products);
//        limit the results
        if(products.size()>limit){
            products = products.subList(0,limit);
        }

        //        convert to DTOs
        return products.stream()
                .map((product) -> {
                    ProductDto productDto = modelMapper.map(product,ProductDto.class);
                    if(product.getImage() != null){
                        productDto.setImageBase64(Base64.getEncoder()
                                .encodeToString(product.getImage()));
                    }
                    return productDto;
                })
                .collect(Collectors.toList());
    }

}
//    @Override
//    public void createProduct(ProductDto productDto) {
////        the above code is just written for change Dto class into model class because repository only work with model(entity)
//        Product product = modelMapper.map(productDto, Product.class);
//        product.setImage(productDto.getImageBytes());
//        product.setUploadDate(new Date());
//
//        productRepository.save(product);
//
//    }

//    @Override
//    public Map<String, List<ProductDto>> getProductsGroupedByCategory() {
//        return productRepository.findAll().stream()
//                .map(product -> {
//                    ProductDto productDto = modelMapper.map(product, ProductDto.class);
//                    if (product.getImage() != null) {
//                        productDto.setImageBase64(Base64.getEncoder().encodeToString(product.getImage()));
//                    }
//                    return productDto;
//                })
//                .collect(Collectors.groupingBy(ProductDto::getCategory));
//    }



package in.DAA.PhotoPlaza.services;

import in.DAA.PhotoPlaza.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryDto categoryDto);
    void updateCategory(Long id,CategoryDto categoryDto);
    void deleteCategory(Long id);
    List<CategoryDto> getAllCategory();
    CategoryDto getCategoryById(Long id);
}

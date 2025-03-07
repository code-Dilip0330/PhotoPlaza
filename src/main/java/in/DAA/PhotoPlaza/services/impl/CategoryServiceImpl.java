package in.DAA.PhotoPlaza.services.impl;

import in.DAA.PhotoPlaza.dto.CategoryDto;
import in.DAA.PhotoPlaza.entites.Category;
import in.DAA.PhotoPlaza.repositories.CategoryRepository;
import in.DAA.PhotoPlaza.services.CategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    @Override
    public void createCategory(CategoryDto categoryDto) {
        //        the above code is just written for change Dto class into model class because repository only work with model(entity)
        Category category = modelMapper.map(categoryDto,Category.class);
        category.setCreatedDate(new Date());
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Long id, CategoryDto categoryDto) {
        Category existingCategory;
        existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        //Update Fields
        existingCategory.setCategoryName(categoryDto.getCategoryName());
        existingCategory.setCreatedDate(new Date());
        existingCategory.setStatus(categoryDto.getStatus());
        categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> modelMapper.map(category,CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found!!!"));
        return modelMapper.map(category ,CategoryDto.class);
    }
}

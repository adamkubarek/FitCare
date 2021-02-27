package pl.javastyle.fitcare.product;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.javastyle.fitcare.category.Category;
import pl.javastyle.fitcare.category.CategoryDAO;
import pl.javastyle.fitcare.core.BaseDTO;
import pl.javastyle.fitcare.core.BaseEntity;
import pl.javastyle.fitcare.core.Context;
import pl.javastyle.fitcare.core.DataFactory;
import pl.javastyle.fitcare.core.exceptions.ApplicationException;
import pl.javastyle.fitcare.core.exceptions.DbErrors;

@Service
public class ProductDataFactory implements DataFactory {

    private final CategoryDAO categoryDAO;

    public ProductDataFactory(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public BaseEntity createEntity(BaseDTO baseDTO) {
        ProductDTO productDTO = (ProductDTO) baseDTO;
        Product product = new Product(productDTO);
        product.setUser(Context.getUser());
        Category categoryName;
        try {
            categoryName = categoryDAO.findCategoryByName(productDTO.getCategory());
        } catch (EmptyResultDataAccessException e) {
            throw new ApplicationException(DbErrors.CATEGORY_NOT_FOUND, productDTO.getCategory());
        }
        product.setCategory(categoryName);

        return product;
    }

    @Override
    public BaseDTO createDTO(BaseEntity baseEntity) {
        return new ProductDTO((Product) baseEntity);
    }
}

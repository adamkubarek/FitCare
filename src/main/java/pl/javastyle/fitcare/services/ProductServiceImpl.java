package pl.javastyle.fitcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.domain.Product;
import pl.javastyle.fitcare.repositories.interfaces.ProductDAO;
import pl.javastyle.fitcare.rest.dto.ProductDTO;
import pl.javastyle.fitcare.services.interfaces.ProductService;
import pl.javastyle.fitcare.services.mappers.ProductMapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;
    private ProductMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO, ProductMapper mapper) {
        this.productDAO = productDAO;
        this.mapper = mapper;
    }

    @Override
    public ProductDTO addNewProduct(ProductDTO productDTO) {
        Product addedProduct = productDAO.saveProduct(mapper.dtoToProduct(productDTO));
        return mapper.productToDto(addedProduct);
    }

    @Override
    public ProductDTO deleteProduct(Product product) {
        return mapper.productToDto(productDAO.deleteProduct(product.getId()));
    }

    @Override
    public List<ProductDTO> sortAllProductsByCategory() {
        return productDAO.getAllProducts().stream()
                .map(mapper::productToDto)
                .sorted(Comparator.comparing(ProductDTO::getCategory))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> sortAllProductsByName() {
        return productDAO.getAllProducts().stream()
                .map(mapper::productToDto)
                .sorted(Comparator.comparing(ProductDTO::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> sortAllProductsByCalories() {
        return productDAO.getAllProducts().stream()
                .map(mapper::productToDto)
                .sorted(Comparator.comparing(ProductDTO::getCalories, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long productId) {
        productDTO.setId(productId);
        Product savedProduct = productDAO.saveProduct(mapper.dtoToProduct(productDTO));
        return mapper.productToDto(savedProduct);
    }
}

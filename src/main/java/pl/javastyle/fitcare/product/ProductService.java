package pl.javastyle.fitcare.product;

import java.util.List;

public interface ProductService {

    ProductDTO getProductById(Long id);

    List<ProductDTO> getSortedProducts(String sortedBy);

    ProductDTO addNewProduct(ProductDTO product);

    ProductDTO updateProduct(ProductDTO product, Long productId);

    ProductDTO patchProduct(ProductDTO patcher, Long productId);

    ProductDTO deleteProduct(Long id);

}

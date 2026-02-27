package businessLayer;

import dataAccessLayer.ProductDAO;
import model.Product;

import java.util.List;

/**
 * Business Logic Layer for Product operations.
 */
public class ProductBLL {
    private final ProductDAO productDAO;

    public ProductBLL() {
        this.productDAO = new ProductDAO();
    }

    /**
     * Inserts a new product.
     * @param product The product to insert
     */
    public void insertProduct(Product product) {
        productDAO.insertProduct(product);
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }
}

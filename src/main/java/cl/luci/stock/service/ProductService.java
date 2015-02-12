package cl.luci.stock.service;

import cl.luci.stock.dao.ProductDAO;
import cl.luci.stock.model.Manufacturer;
import cl.luci.stock.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oreste Luci
 */
@Service("productService")
public class ProductService extends AbstractService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ManufacturerService manufacturerService;

    public void create(Product product) throws Exception {

        if (product==null) {
            throw new Exception("Product vacio");
        }

        if (product.getManufacturer()==null) {
            throw new Exception("Manufacturer vacio");
        }

        Manufacturer manufacturer = manufacturerService.findById(product.getManufacturer().getId());

        if (manufacturer==null) {
            throw new Exception("Manufacturer vacio");
        }

        product.setManufacturer(manufacturer);

        validate(product);

        productDAO.create(product);
    }

    public Product findById(Long id) {
        return productDAO.findById(id);
    }

    public void deleteById(Long id) {
        productDAO.deleteById(id);
    }

    public void update(Product product) throws Exception {
        validate(product);
        productDAO.update(product);
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public void addStock(Long code,Long stock) throws Exception {
        newStock(code,stock,true);
    }

    public void subtractStock(Long code,Long stock) throws Exception {
        newStock(code,stock,false);
    }

    /**
     * Method adds or subtracts from the stock
     * @param code
     * @param amount
     * @param add
     * @throws Exception
     */
    private void newStock(Long code,Long amount,boolean add) throws Exception {

        if (code==null) {
            throw new Exception("ID no puede ser nulo.");
        }

        if (amount ==null) {
            throw new Exception("Stock no puede ser nulo.");
        }

        if (amount.longValue()<1) {
            throw new Exception("Value debe ser mayor que 0.");
        }

        Product product = findById(code);

        if (product == null) {
            throw new Exception("Producto no encontrado.");
        }

        Long actual_stock = product.getStock();

        if (!add && actual_stock < amount) {
            throw new Exception("No hay suficiente Stock. El stock actual es: " + actual_stock);
        }

        int sign = 1;
        if (!add) {
            sign = -1;
        }

        long new_stock = actual_stock.longValue() + (amount.longValue()*sign);

        product.setStock(new Long(new_stock));

        update(product);
    }

    /**
     * Returns the list of low stock items.
     * @param limit Threshold for low stock items (inclusive)
     * @return
     * @throws Exception
     */
    public List<Product> underStock(Long limit) throws Exception {

        if (limit==null || limit.longValue()<0) {
            throw new Exception("Limite no valido.");
        }
        return productDAO.underStock(limit);
    }
}

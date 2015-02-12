package cl.luci.stock.dao;

import cl.luci.stock.model.Manufacturer;
import org.springframework.stereotype.Repository;

/**
 * Manufacturers persistance class
 * @author Oreste Luci
 */
@Repository("vendorDAO")
public class ManufacturerDAO extends AbstractJpaDAO<Manufacturer> {

    public ManufacturerDAO(){
        setClazz(Manufacturer.class);
    }

    public void create(Manufacturer product) throws Exception {

        Manufacturer manufacturer = this.findById(product.getId());

        if (manufacturer!=null) {
            throw new Exception("Manufacturer does not exist");
        }

        super.create(product);
    }

}

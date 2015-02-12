package cl.luci.stock.service;

import cl.luci.stock.dao.ManufacturerDAO;
import cl.luci.stock.model.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CRUD operations for the Manufacturers
 * @author Oreste Luci
 */
@Service("manufacturerService")
public class ManufacturerService extends AbstractService {

    @Autowired
    private ManufacturerDAO manufacturerDAO;

    public void create(Manufacturer manufacturer) throws Exception {
        validate(manufacturer);
        manufacturerDAO.create(manufacturer);
    }

    public Manufacturer findById(Long id) {
        return manufacturerDAO.findById(id);
    }

    public void deleteById(Long id) {
        manufacturerDAO.deleteById(id);
    }

    public void update(Manufacturer manufacturer) throws Exception {
        validate(manufacturer);
        manufacturerDAO.update(manufacturer);
    }

    public List<Manufacturer> findAll() {
        return manufacturerDAO.findAll();
    }
}

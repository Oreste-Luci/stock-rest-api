package cl.luci.stock.dao;

import cl.luci.stock.model.Manufacturer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.fail;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Oreste Luci
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@ActiveProfiles(profiles = "test")
public class ManufacturerDAOTest {

    @Autowired
    private ManufacturerDAO manufacturerDAO;

    @Test
    public void createManufacturer() {

        try {
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setId(new Long(100));
            manufacturer.setName("Test Manufacturer");

            manufacturerDAO.create(manufacturer);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void createManufacturerError() {

        try {
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setId(new Long(101));

            manufacturerDAO.create(manufacturer);

            fail("Manufacturer Created");

        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void deleteManufacturer() {

        try {
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setId(new Long(102));
            manufacturer.setName("Test Manufacturer 3");

            manufacturerDAO.create(manufacturer);

            manufacturerDAO.deleteById(manufacturer.getId());

            assertTrue(true);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void updateManufacturer() {

        try {
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setId(new Long(104));
            manufacturer.setName("Test Manufacturer 4");

            manufacturerDAO.create(manufacturer);

            manufacturer.setName("Test Manufacturer 4.1");

            manufacturerDAO.update(manufacturer);

            Manufacturer m2 = manufacturerDAO.findById(manufacturer.getId());

            assertTrue(m2.getName().equalsIgnoreCase(manufacturer.getName()));

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}

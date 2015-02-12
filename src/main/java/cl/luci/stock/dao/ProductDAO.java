package cl.luci.stock.dao;

import cl.luci.stock.model.Product;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class for the persistance of Products
 * @author Oreste Luci
 */
@Repository("productDAO")
public class ProductDAO extends AbstractJpaDAO<Product> {

    public ProductDAO(){
        setClazz(Product.class);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Product> underStock(Long amount) {

        Session session = getSession();
        Transaction tx = session.beginTransaction();

        String hql = "FROM cl.luci.stock.model.Product P WHERE P.stock <= :limit";
        Query query = session.createQuery(hql);
        query.setParameter("limit",amount);
        List results = query.list();
        tx.commit();
        session.close();
        return results;
    }

    public void create(Product product) throws Exception {

        Product exisiting = this.findById(product.getProductCode());

        if (exisiting!=null) {
            throw new Exception("Product already exists");
        }

        super.create(product);
    }
}

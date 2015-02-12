package cl.luci.stock.rest;

import cl.luci.stock.service.ProductService;
import cl.luci.stock.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Oreste Luci
 */
@Component
@Path("/product")
public class RestProductService extends AbstractRestService {

    @Autowired
    private ProductService productService;

    /**
     * Adds a new product to the DB
     * @param product
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Product product) {
        try {
            productService.create(product);
            return buildResponse(Response.Status.CREATED.getStatusCode());
        } catch (Exception e) {
            return buildResponse(e);
        }
    }

    /**
     * Obtains a product given its ID
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {

        Product product = productService.findById(id);

        if (product !=null) {
            return buildResponse(Response.Status.OK.getStatusCode(), product);
        } else {
            return notFound();
        }
    }

    /**
     * Returns the whole list of products
     * @return
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Product> products = productService.findAll();
        return buildResponse(Response.Status.OK.getStatusCode(), products);
    }

    /**
     * Deletes a product from the DB
     * @param id
     * @return
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        try {
            productService.deleteById(id);
            return buildResponse(Response.Status.OK.getStatusCode());
        } catch (Exception e) {
            return notFound(e);
        }
    }

    /**
     * Updates a product
     * @param product
     * @return
     */
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Product product) {
        try {
            productService.update(product);
            return buildResponse(Response.Status.OK.getStatusCode());
        } catch (Exception e) {
            return notFound(e);
        }
    }

    /**
     * Adds the given amount to the stock
     * @param code identifies the product
     * @param amount amount to be added to the stock
     * @return
     */
    @GET
    @Path("/stock_add/{code}/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStock(@PathParam("code") Long code, @PathParam("amount") Long amount) {
        try {
            productService.addStock(code, amount);
            return buildResponse(Response.Status.OK.getStatusCode());
        } catch (Exception e) {
            return buildResponse(e);
        }
    }

    /**
     * Subtracts an amount from the stock
     * @param code identifies the product
     * @param amount amount to subtract
     * @return
     */
    @GET
    @Path("/stock_subtract/{code}/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response subtractStock(@PathParam("code") Long code, @PathParam("amount") Long amount) {
        try {
            productService.subtractStock(code, amount);
            return buildResponse(Response.Status.OK.getStatusCode());
        } catch (Exception e) {
            return buildResponse(e);
        }
    }

    /**
     * Obtains the list of products below the given amount (inclusive)
     * @param amount threshold for stock search (inclusive)
     * @return
     */
    @GET
    @Path("/under_stock/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response underStock(@PathParam("amount") Long amount) {
        try {
            List<Product> products = productService.underStock(amount);
            return buildResponse(Response.Status.OK.getStatusCode(),products);
        } catch (Exception e) {
            return buildResponse(e);
        }
    }

}

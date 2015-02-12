package cl.luci.stock.rest;

import cl.luci.stock.service.ManufacturerService;
import cl.luci.stock.model.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Rest API for CRUD operations for Manufacturers
 */
@Component
@Path("/manufacturer")
public class RestManufacturerService extends AbstractRestService {

    @Autowired
    private ManufacturerService manufacturerService;

    /**
     * Returns a Manufacturer given Its ID
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        Manufacturer manufacturer = manufacturerService.findById(id);
        if (manufacturer !=null) {
            return buildResponse(Response.Status.OK.getStatusCode(), manufacturer);
        } else {
            return notFound();
        }
    }

    /**
     * Stores a new manufacturer in the system
     * @param manufacturer Manufacturer to be added. All attributes are required.
     * @return HTTP response code
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Manufacturer manufacturer) {
        try {
            manufacturerService.create(manufacturer);
            return buildResponse(Response.Status.CREATED.getStatusCode());
        } catch (Exception e) {
            return buildResponse(e);
        }
    }

    /**
     * @return Returns all available manufacturers.
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        return buildResponse(Response.Status.OK.getStatusCode(), manufacturers);
    }

    /**
     * Deletes an existing manufacturer.
     * @param id
     * @return
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        try {
            manufacturerService.deleteById(id);
            return buildResponse(Response.Status.OK.getStatusCode());
        } catch (Exception e) {
            return notFound(e);
        }
    }

    /**
     * Updates an existing manufacturer.
     * @param manufacturer
     * @return
     */
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Manufacturer manufacturer) {
        try {
            manufacturerService.update(manufacturer);
            return buildResponse(Response.Status.OK.getStatusCode());
        } catch (Exception e) {
            return notFound(e);
        }
    }
}

package cl.luci.stock.rest;

import javax.ws.rs.core.Response;

/**
 * Generic functionalities for the REST services.
 * @author Oreste Luci
 */
public abstract class AbstractRestService {

    /**
     * Builds a response with the return code.
     * @param code
     * @return
     */
    protected Response buildResponse(int code) {
        return buildResponse(code,null);
    }

    /**
     * Builds a response with a response body.
     * @param code
     * @param o
     * @return
     */
    protected Response buildResponse(int code,Object o) {
        if (o != null) {
            return Response.status(code).entity(o).build();
        } else {
            return Response.status(code).build();
        }
    }

    /**
     * Builds an error message.
     * @param e
     * @return
     */
    protected Response buildResponse(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(),e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity(errorResponse).build();
    }

    /**
     * Builds a not found message.
     * @return
     */
    protected Response notFound(Exception e) {

        String message = "Not Found";

        if (e != null) {
            message = e.getMessage();
        }

        ErrorResponse errorResponse = new ErrorResponse(Response.Status.NOT_FOUND.getStatusCode(),message);
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity(errorResponse).build();
    }

    protected Response notFound() {
        return notFound(null);
    }
}

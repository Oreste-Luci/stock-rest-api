package cl.luci.stock.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Error response object used by REST API
 * @author Oreste Luci
 */
public class ErrorResponse {

    @JsonProperty("response_code")
    private int responseCode;

    @JsonProperty("message")
    private String message;

    public ErrorResponse(int responseCode,String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

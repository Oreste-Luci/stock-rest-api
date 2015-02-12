package cl.luci.stock.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
/**
 * Represents a Product Manufacturer
 * @author Oreste Luci
 */
@Entity
public class Manufacturer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull(message = "'id' is required.")
    @Min(value = 1, message = "'id' must be greater then 0.")
    @JsonProperty("manufacturer_id")
    private Long id;

    @NotNull(message = "'name' is required.")
    @Size(min = 2, max = 256,message = "'name' must have a length between 2 y 256.")
    @JsonProperty("manufacturer_name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

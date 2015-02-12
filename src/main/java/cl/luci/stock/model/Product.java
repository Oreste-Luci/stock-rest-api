package cl.luci.stock.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Represents a product
 * @author Oreste Luci
 */
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull(message = "'product_code' is required.")
    @JsonProperty("product_code")
    private Long productCode;

    @NotNull(message = "'name' is required.")
    @Size(min = 2, max = 256,message = "'name' must be between 2 and 256 characters.")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "'price' is required.")
    @Min(value = 0, message = "'price' cannot be negative.")
    @JsonProperty("price")
    private Float price;

    @JsonProperty("manufacturer")
    @JsonUnwrapped
    @ManyToOne
    @JoinColumn(name = "id")
    private Manufacturer manufacturer;

    @NotNull(message = "'type' is required.")
    @JsonProperty("type")
    @Enumerated(EnumType.STRING)
    private ProductTypeEnum type;

    @NotNull(message = "'stock' is required.")
    @Min(value = 0, message = "'stock' cannot be negative.")
    @JsonProperty("stock")
    private Long stock;

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public ProductTypeEnum getType() {
        return type;
    }

    public void setType(ProductTypeEnum type) {
        this.type = type;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}

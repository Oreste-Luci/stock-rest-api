package cl.luci.stock.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Product Types
 * @author Oreste Luci
 */
public enum ProductTypeEnum {

    TV_VIDEO, HOME_AUDIO, PHOTO_VIDEO, CELL_PHONE, VIDEO_GAMES, LAPTOPS, DESKTOPS, TABLETS;

    @JsonCreator
    public static ProductTypeEnum fromValue(String value) {
        for (ProductTypeEnum myEnum : values()) {
            if (myEnum.name().equals(value)) {
                return myEnum;
            }
        }
        return null;
    }
}

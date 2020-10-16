package ch.mobi.ueliloetscher.learning.usermanagement.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UserDeleteDTO implements Serializable {

    @NotBlank(message = "id is required")
    private String id;

    public UserDeleteDTO() {
    }

    public UserDeleteDTO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

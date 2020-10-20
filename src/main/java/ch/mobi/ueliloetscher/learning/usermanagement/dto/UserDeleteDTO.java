package ch.mobi.ueliloetscher.learning.usermanagement.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserDeleteDTO implements Serializable {

    @NotNull(message = "id is required")
    private Long id;

    public UserDeleteDTO() {
    }

    public UserDeleteDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

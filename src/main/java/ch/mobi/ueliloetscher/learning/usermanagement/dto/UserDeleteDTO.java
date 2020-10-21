package ch.mobi.ueliloetscher.learning.usermanagement.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

public class UserDeleteDTO implements Serializable {

    @NotNull(message = "id is required")
    @Positive(message = "is has to be positive")
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

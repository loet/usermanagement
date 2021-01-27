package ch.mobi.ueliloetscher.learning.usermanagement.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class AddDepartmentDTO implements Serializable {

    @NotBlank(message = "name required")
    private String name;

    public AddDepartmentDTO() {
    }

    public AddDepartmentDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

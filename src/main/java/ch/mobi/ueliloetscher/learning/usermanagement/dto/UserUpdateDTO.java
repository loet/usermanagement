package ch.mobi.ueliloetscher.learning.usermanagement.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

public class UserUpdateDTO implements Serializable {

    @NotNull(message = "id is required")
    private Long id;
    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;
    @Email(message = "a valid email is required")
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "UUID is required")
    private String UUID;
    @NotBlank(message = "modified is required for updated")
    private String modified;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}

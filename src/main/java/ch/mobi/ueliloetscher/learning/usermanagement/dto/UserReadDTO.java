package ch.mobi.ueliloetscher.learning.usermanagement.dto;

import ch.mobi.ueliloetscher.learning.usermanagement.validation.datetime.ValidDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UserReadDTO implements Serializable {

    private String id;
    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;
    @Email(message = "email is required")
    private String email;
    @ValidDateTime
    private String modified;

    public UserReadDTO() {
    }

    public UserReadDTO(String id, String firstName, String lastName, String email, String modified) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.modified = modified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }


}

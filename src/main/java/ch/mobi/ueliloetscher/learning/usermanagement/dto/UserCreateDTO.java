package ch.mobi.ueliloetscher.learning.usermanagement.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UserCreateDTO implements Serializable {

    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;
    @Email(message = "a valid email address is required")
    @NotBlank(message = "email is required")
    private String email;

    public UserCreateDTO() {
    }

    public UserCreateDTO(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

}

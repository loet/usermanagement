package ch.mobi.ueliloetscher.learning.usermanagement.boundary;

import ch.mobi.ueliloetscher.learning.usermanagement.control.*;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserCreateDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserDeleteDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserReadDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserUpdateDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Collection;

@Stateless
public class UserManagementBean {

    @Inject
    private UserGetService userGetService;
    @Inject
    private UserAddService userAddService;
    @Inject
    private UserUpdateService userUpdateService;
    @Inject
    private UserSearchService userSearchService;
    @Inject
    private UserDeleteService userDeleteService;


    public Collection<@Valid UserReadDTO> getAllUsers() {
        return this.userSearchService.getAllUsers();
    }

    @Valid
    public UserReadDTO getUser(Long id) {
        return this.userGetService.getUser(id);
    }

    @Valid
    public UserReadDTO addUser(@Valid UserCreateDTO userCreateDTO) {
        return this.userAddService.addUser(userCreateDTO);
    }

    @Valid
    public UserReadDTO updateUser(@Valid UserUpdateDTO userUpdateDTO) {
        return this.userUpdateService.updateUser(userUpdateDTO);
    }

    public boolean deleteUser(@Valid UserDeleteDTO userDeleteDTO) {
        return this.userDeleteService.deleteUser(userDeleteDTO);
    }

}

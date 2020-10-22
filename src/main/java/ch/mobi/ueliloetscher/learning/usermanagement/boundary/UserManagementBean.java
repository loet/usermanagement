package ch.mobi.ueliloetscher.learning.usermanagement.boundary;

import ch.mobi.ueliloetscher.learning.usermanagement.dto.*;
import ch.mobi.ueliloetscher.learning.usermanagement.util.ClientRequest;
import ch.mobi.ueliloetscher.learning.usermanagement.control.*;
import ch.mobi.ueliloetscher.learning.usermanagement.validation.ConstraintViolationInterceptor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.validation.Valid;
import java.util.Collection;

@Stateless
@Interceptors(ConstraintViolationInterceptor.class)
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
    @Inject
    private FdtGetService fdtGetService;
    @Inject
    ClientRequest clientRequest;


    public Collection<@Valid UserReadDTO> getAllUsers() {
        System.out.println("Authorization from bean: " + this.clientRequest.getAuthorizationHeader());
        return this.userSearchService.getAllUsers();
    }

    public UserReadDTO getUser(Long id) {
        System.out.println("Authorization from bean: " + this.clientRequest.getAuthorizationHeader());
        return this.userGetService.getUser(id);
    }

    public UserReadDTO addUser(@Valid UserCreateDTO userCreateDTO) {
        System.out.println("Authorization from bean: " + this.clientRequest.getAuthorizationHeader());
        return this.userAddService.addUser(userCreateDTO);
    }

    public UserReadDTO updateUser(@Valid UserUpdateDTO userUpdateDTO) {
        System.out.println("Authorization from bean: " + this.clientRequest.getAuthorizationHeader());
        return this.userUpdateService.updateUser(userUpdateDTO);
    }

    public boolean deleteUser(@Valid UserDeleteDTO userDeleteDTO) {
        System.out.println("Authorization from bean: " + this.clientRequest.getAuthorizationHeader());
        return this.userDeleteService.deleteUser(userDeleteDTO);
    }

    public Collection<DataTypeDTO> getCountryCodes() {
        return this.fdtGetService.getCodes(400054);
    }

}

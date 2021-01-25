package ch.mobi.ueliloetscher.learning.usermanagement.boundary;

import ch.mobi.ueliloetscher.learning.usermanagement.dto.*;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.usermanagement.util.ClientRequest;
import ch.mobi.ueliloetscher.learning.usermanagement.control.*;
import ch.mobi.ueliloetscher.learning.usermanagement.validation.ConstraintViolationInterceptor;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.ejb.*;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
// @Interceptors(ConstraintViolationInterceptor.class)
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

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    // @RolesAllowed("Administrator")
    public UserReadDTO addUser(@Valid UserCreateDTO userCreateDTO) {
        System.out.println("Authorization from bean: " + this.clientRequest.getAuthorizationHeader());
        return this.userAddService.addUser(userCreateDTO);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserReadDTO updateUser(@Valid UserUpdateDTO userUpdateDTO) {
        System.out.println("Authorization from bean: " + this.clientRequest.getAuthorizationHeader());
        return this.userUpdateService.updateUser(userUpdateDTO);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteUser(@Valid UserDeleteDTO userDeleteDTO) {
        System.out.println("Authorization from bean: " + this.clientRequest.getAuthorizationHeader());
        return this.userDeleteService.deleteUser(userDeleteDTO);
    }

    public Collection<DataTypeDTO> getCountryCodes() {
        return this.fdtGetService.getCodes(400054);
    }


}

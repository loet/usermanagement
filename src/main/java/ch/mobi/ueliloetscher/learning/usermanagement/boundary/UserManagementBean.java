package ch.mobi.ueliloetscher.learning.usermanagement.boundary;

import ch.mobi.ueliloetscher.learning.usermanagement.dto.*;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.usermanagement.util.ClientRequest;
import ch.mobi.ueliloetscher.learning.usermanagement.control.*;
import ch.mobi.ueliloetscher.learning.usermanagement.validation.ConstraintViolationInterceptor;

import javax.ejb.*;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.validation.Valid;
import java.util.Collection;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
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
    @Inject
    private EmployeeAddService employeeAddService;
    @Inject
    private EmployeeUpdateService employeeUpdateService;
    @Inject
    private EmployeeSearchService employeeSearchService;
    @Inject
    private EmployeeGetService employeeGetService;
    @Inject
    private EmployeeDeleteService employeeDeleteService;


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

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Employee addEmployee(Employee employee) {
        return this.employeeAddService.addEmployee(employee);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Employee updateEmployee(Employee employee) {
        return this.employeeUpdateService.updateEmployee(employee);
    }

    public Employee getEmployee(Integer eid) {
        return this.employeeGetService.getEmployee(eid);
    }

    public Collection<Employee> getAllEmployees() {
        return this.employeeSearchService.getAllEmployees();
    }

    public Collection<Employee> searchEmployees(String ename) {
        return this.employeeSearchService.searchEmployees(ename);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteEmployee(Integer eid) {
        return this.employeeDeleteService.deleteEmployee(eid);
    }

}

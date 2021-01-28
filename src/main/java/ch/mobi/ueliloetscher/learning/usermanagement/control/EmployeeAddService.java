package ch.mobi.ueliloetscher.learning.usermanagement.control;

import ch.mobi.ueliloetscher.learning.usermanagement.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;

@ApplicationScoped
public class EmployeeAddService {

    @Inject
    private EmployeeStorage employeeStorage;

    public Employee addEmployee(@Valid Employee employee) {
        return this.employeeStorage.store(employee);
    }

}

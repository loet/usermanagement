package ch.mobi.ueliloetscher.learning.usermanagement.control;

import ch.mobi.ueliloetscher.learning.usermanagement.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.BadRequestException;

@ApplicationScoped
public class EmployeeUpdateService {

    @Inject
    private EmployeeStorage employeeStorage;

    @PersistenceContext(unitName = "usermanagement", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public Employee updateEmployee(int eid, Employee employee) throws IllegalArgumentException {
        if (employee.getEid() != eid) {
            throw new IllegalArgumentException("eid and employee.eid do not match");
        }

        Employee existing = this.em.find(Employee.class, eid);
        if (existing == null) {
            return null;
        }

        return this.employeeStorage.store(employee);
    }

}

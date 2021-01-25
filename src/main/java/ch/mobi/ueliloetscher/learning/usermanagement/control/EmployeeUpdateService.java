package ch.mobi.ueliloetscher.learning.usermanagement.control;

import ch.mobi.ueliloetscher.learning.usermanagement.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import static org.apache.commons.beanutils.PropertyUtils.copyProperties;

@ApplicationScoped
public class EmployeeUpdateService {

    @PersistenceContext(unitName = "usermanagement", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public Employee updateEmployee(int eid, Employee employeeUpdate) {
        employeeUpdate.setEname_search(employeeUpdate.getEname().toLowerCase());
        Employee existing = this.em.find(Employee.class, eid);
        if (existing == null) {
            return null;
        }
        try {
            copyProperties(existing, employeeUpdate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return existing;
    }

}

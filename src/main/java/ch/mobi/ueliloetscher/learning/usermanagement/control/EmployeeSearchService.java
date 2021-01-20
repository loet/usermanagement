package ch.mobi.ueliloetscher.learning.usermanagement.control;

import ch.mobi.ueliloetscher.learning.usermanagement.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@ApplicationScoped
public class EmployeeSearchService {

    @PersistenceContext(unitName = "usermanagement", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public Collection<Employee> getAllEmployees() {
        Query query = em.createQuery("SELECT e FROM Employee e");
        Collection<Employee> allEmployees = (Collection<Employee>) query.getResultList();
        return allEmployees;
    }

    public Collection<Employee> searchEmployees(String ename) {
        Query query = em.createNamedQuery("search employees by ename");

        query.setParameter("ename", ename);
        List<Employee> employees = query.getResultList();
        return employees;
    }

}

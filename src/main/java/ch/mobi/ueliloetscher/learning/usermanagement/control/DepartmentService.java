package ch.mobi.ueliloetscher.learning.usermanagement.control;

import ch.mobi.ueliloetscher.learning.usermanagement.entity.Department;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class DepartmentService {

    @PersistenceContext(unitName = "usermanagement", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public Department searchDepartment(String name) {
        Query query = em.createNamedQuery("search department");

        query.setParameter("name", name);
        List<Department> employees = query.getResultList();
        if (employees.size() < 1) {
            return null;
        }
        return employees.get(0);
    }

    public Department addDepartment(Department department) {
        em.persist(department);
        return department;
    }

}

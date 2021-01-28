package ch.mobi.ueliloetscher.learning.usermanagement.control;

import ch.mobi.ueliloetscher.learning.usermanagement.entity.Department;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Skill;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@ApplicationScoped
public class EmployeeStorage {

    @Inject
    private DepartmentService departmentService;

    @Inject
    private SkillService skillService;

    @PersistenceContext(unitName = "usermanagement", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public Employee store(Employee employee) {
        // set lowercase name for search statements
        employee.setEname_search(employee.getEname().toLowerCase());

        // handle department
        Department persistentDepartment = this.departmentService.searchDepartment(employee.getDepartment().getName());
        if (persistentDepartment != null) {
            employee.getDepartment().setDid(persistentDepartment.getDid());
        } else {
            this.departmentService.addDepartment(employee.getDepartment());
        }

        //handle skills
        employee.getSkills().stream()
                .forEach(skill -> {
                    Skill persistentSkill = this.skillService.searchSkill(skill.getSkill());
                    if (persistentSkill != null) {
                        skill.setId(persistentSkill.getId());
                    } else {
                        this.skillService.addSkill(skill);
                    }
                });

        //store employee
        return em.merge(employee);
    }

}

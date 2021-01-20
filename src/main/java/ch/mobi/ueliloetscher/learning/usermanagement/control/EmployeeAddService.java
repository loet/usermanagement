package ch.mobi.ueliloetscher.learning.usermanagement.control;

import ch.mobi.ueliloetscher.learning.usermanagement.entity.Department;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Skill;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EmployeeAddService {

    @Inject
    private DepartmentService departmentService;

    @Inject
    private SkillService skillService;

    @PersistenceContext(unitName = "usermanagement", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public Employee addEmployee(Employee employee) {
        employee.setEname_search(employee.getEname().toLowerCase());

        // handle department
        Department department = this.departmentService.searchDepartment(employee.getDepartment().getName());
        if (department == null) {
            department = this.departmentService.addDepartment(employee.getDepartment());
        }
        employee.setDepartment(department);

        //handle skills
        List<Skill> skills = new ArrayList<>();
        employee.getSkills().stream()
                .forEach(skill -> {
                    Skill foundSkill = this.skillService.searchSkill(skill.getSkill());
                    if (foundSkill == null) {
                        foundSkill = this.skillService.addSkill(skill);
                    }
                    skills.add(foundSkill);
                });
        employee.setSkills(skills);

        //store employee
        em.persist(employee);
        return employee;
    }

}

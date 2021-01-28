package ch.mobi.ueliloetscher.learning.usermanagement.control;

import ch.mobi.ueliloetscher.learning.usermanagement.dto.AddEmployeeDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Department;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Skill;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.validation.Valid;
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

    public Employee addEmployee(@Valid Employee employee) {
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
        em.persist(employee);

        return employee;
    }
/*
    public Employee addEmployeeWithDTO(@Valid AddEmployeeDTO addEmployeeDTO) {
        Employee employee = new Employee();
        employee.setEname(addEmployeeDTO.getEname());
        employee.setDeg(addEmployeeDTO.getDeg());
        employee.setSalary(addEmployeeDTO.getSalary());
        employee.setEname_search(addEmployeeDTO.getEname().toLowerCase());

        // handle department
        Department department = this.departmentService.searchDepartment(addEmployeeDTO.getDepartment().getName());
        if (department == null) {
            department = new Department();
            department.setName(addEmployeeDTO.getDepartment().getName());
            this.departmentService.addDepartment(department);
        }
        employee.setDepartment(department);

        //handle skills
        List<Skill> skills = new ArrayList<>();
        addEmployeeDTO.getSkills().stream()
                .forEach(skillDTO -> {
                    Skill skill = this.skillService.searchSkill(skillDTO.getSkill());
                    if (skill == null) {
                        skill = new Skill();
                        skill.setSkill(skillDTO.getSkill());
                        this.skillService.addSkill(skill);
                    }
                    skills.add(skill);
                });
        employee.setSkills(skills);

        //store employee
        em.persist(employee);

        return employee;
    }
    */


}

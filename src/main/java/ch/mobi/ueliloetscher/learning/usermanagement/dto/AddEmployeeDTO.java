package ch.mobi.ueliloetscher.learning.usermanagement.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddEmployeeDTO implements Serializable {

    @NotBlank(message = "ename is required")
    private String ename;
    @NotNull(message = "salary required")
    private Double salary;
    @NotBlank(message = "deg is required")
    private String deg;
    @NotNull(message = "department required")
    private AddDepartmentDTO department;
    @NotEmpty(message = "skills required")
    private List<AddSkillDTO> skills = new ArrayList<>();

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    public AddDepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(AddDepartmentDTO department) {
        this.department = department;
    }

    public List<AddSkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<AddSkillDTO> skills) {
        this.skills = skills;
    }
}

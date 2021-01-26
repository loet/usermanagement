package ch.mobi.ueliloetscher.learning.usermanagement.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
//@NamedQuery(query = "Select e from Employee e where e.ename like :ename ", name="search employees by ename")
@NamedQuery(query = "Select e from Employee e where e.ename_search like lower(concat('%', :ename,'%')) ", name="search employees by ename")
public class Employee implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eid;
    private String ename;
    private String ename_search;
    private double salary;
    private String deg;

    @ManyToOne
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Skill> skills = new ArrayList<>();

    public Employee(int eid, String ename, double salary, String deg) {
        super( );
        this.eid = eid;
        this.ename = ename;
        this.salary = salary;
        this.deg = deg;
    }

    public Employee( ) {
        super();
    }

    public int getEid( ) {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname( ) {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEname_search() {
        return ename_search;
    }

    public void setEname_search(String ename_search) {
        this.ename_search = ename_search;
    }

    public double getSalary( ) {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDeg( ) {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Employee [eid=" + eid + ", ename=" + ename + ", salary=" + salary + ", deg=" + deg + "]";
    }
}

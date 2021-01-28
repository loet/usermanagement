package ch.mobi.ueliloetscher.learning.usermanagement.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@NamedQuery(query = "Select d from Department d where d.name = :name", name = "search department")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer did;
    private String name;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Department(Integer did, String name) {
        this.did = did;
        this.name = name;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package ch.mobi.ueliloetscher.learning.usermanagement.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@NamedQuery(query = "Select skill from Skill skill where skill.skill = :skill", name = "search skill")
public class Skill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String skill;

    public Skill() {
    }

    public Skill(String skill) {
        this.skill = skill;
    }

    public Skill(int id, String skill) {
        this.id = id;
        this.skill = skill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}

package ch.mobi.ueliloetscher.learning.usermanagement.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class AddSkillDTO implements Serializable {

    @NotBlank(message = "sill required")
    private String skill;

    public AddSkillDTO() {
    }

    public AddSkillDTO(String skill) {
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}

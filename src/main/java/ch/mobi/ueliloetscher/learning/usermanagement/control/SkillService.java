package ch.mobi.ueliloetscher.learning.usermanagement.control;

import ch.mobi.ueliloetscher.learning.usermanagement.entity.Department;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Skill;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class SkillService {

    @PersistenceContext(unitName = "usermanagement", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public Skill searchSkill(String skill) {
        Query query = em.createNamedQuery("search skill");

        query.setParameter("skill", skill);
        List<Skill> skills = query.getResultList();
        if (skills.size() < 1) {
            return null;
        }
        return skills.get(0);
    }

    public Skill addSkill(Skill skill) {
        em.persist(skill);
        return skill;
    }

}

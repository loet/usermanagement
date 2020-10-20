package ch.mobi.ueliloetscher.learning.usermanagement.control;

import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserDeleteDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserReadDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.User;
import org.apache.commons.beanutils.PropertyUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@ApplicationScoped
public class UserDeleteService {

    @PersistenceContext(unitName = "usermanagement", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public boolean deleteUser(UserDeleteDTO userDeleteDTO) {
        User user = em.find(User.class, userDeleteDTO.getId());
        if (user == null) {
            return false;
        }
        em.remove(user);
        return true;
    }

}

package ch.mobi.ueliloetscher.learning.usermanagement.control;

import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserReadDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserUpdateDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.User;
import org.apache.commons.beanutils.PropertyUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class UserGetService {

    @PersistenceContext(unitName = "usermanagement", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public UserReadDTO getUser(Long id) {
        User user = em.find(User.class, id);
        if (user == null) {
            return null;
        }
        UserReadDTO userReadDTO = new UserReadDTO();
        try {
            PropertyUtils.copyProperties(userReadDTO, user);
            return userReadDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

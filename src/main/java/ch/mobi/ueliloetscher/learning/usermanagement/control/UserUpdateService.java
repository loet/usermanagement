package ch.mobi.ueliloetscher.learning.usermanagement.control;

import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserCreateDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserReadDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserUpdateDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.User;
import org.apache.commons.beanutils.PropertyUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class UserUpdateService {

    @PersistenceContext(unitName = "usermanagement", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public UserReadDTO updateUser(UserUpdateDTO userUpdateDTO) {
        User user = em.find(User.class, userUpdateDTO.getId());
        if (user == null) {
            return null;
        }
        try {
            PropertyUtils.copyProperties(user, userUpdateDTO);
            String modified = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            user.setModified(modified);
            em.persist(user);
            UserReadDTO userReadDTO = new UserReadDTO();
            PropertyUtils.copyProperties(userReadDTO, user);
            return userReadDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package ch.mobi.ueliloetscher.learning.usermanagement.control;

import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserCreateDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserReadDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.User;
import org.apache.commons.beanutils.PropertyUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@ApplicationScoped
public class UserAddService {

    @PersistenceContext(unitName = "usermanagement", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public UserReadDTO addUser(UserCreateDTO userCreateDTO) {
        User user = new User();
        try {
            PropertyUtils.copyProperties(user, userCreateDTO);
            String modified = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            user.setModified(modified);
            user.setUUID(UUID.randomUUID().toString());
            em.persist(user);
            UserReadDTO createdUser = new UserReadDTO();
            PropertyUtils.copyProperties(createdUser, user);
            return createdUser;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

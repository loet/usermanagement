package ch.mobi.ueliloetscher.learning.usermanagement.control;

import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserReadDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.User;
import org.apache.commons.beanutils.PropertyUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;

import static org.apache.commons.beanutils.PropertyUtils.copyProperties;

@ApplicationScoped
public class UserSearchService {

    @PersistenceContext(unitName = "usermanagement", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public Collection<UserReadDTO> getAllUsers() {
        Query query = em.createQuery("SELECT u FROM User u");
        Collection<User> allUsers = (Collection<User>) query.getResultList();
        Collection<UserReadDTO> allUsersDTO = new ArrayList<UserReadDTO>();
        try {
            for (User user : allUsers) {
                UserReadDTO userReadDTO = new UserReadDTO();
                copyProperties(userReadDTO, user);
                allUsersDTO.add(userReadDTO);
            }
            return allUsersDTO;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}

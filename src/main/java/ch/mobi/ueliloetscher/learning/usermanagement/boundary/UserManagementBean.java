package ch.mobi.ueliloetscher.learning.usermanagement.boundary;

import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserCreateDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserDeleteDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserReadDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserUpdateDTO;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.UUID;

@Stateless
public class UserManagementBean {

    @Inject
    private UserCache userCache;

    public Collection<@Valid UserReadDTO> getAllUsers() {
        return this.userCache.getAllUsers();
    }

    @Valid
    public UserReadDTO getUser(String id) {
        Collection<UserReadDTO> allUsers = this.userCache.getAllUsers();
        for (UserReadDTO user : allUsers) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Valid
    public UserReadDTO addUser(@Valid UserCreateDTO userCreateDTO) {
        UserReadDTO userReadDTO = new UserReadDTO();
        String uuid = UUID.randomUUID().toString();
        userReadDTO.setId(uuid);
        String modified = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        userReadDTO.setModified(modified);
        try {
            PropertyUtils.copyProperties(userReadDTO, userCreateDTO);
        } catch (Exception e) {
            throw new RuntimeException("could not copy properties form userCreateDTO to userReadDTO", e);
        }
        this.userCache.getAllUsers().add(userReadDTO);
        return userReadDTO;
    }

    @Valid
    public UserReadDTO updateUser(@Valid UserUpdateDTO userUpdateDTO) {
        UserReadDTO userReadDTO = this.getUser(userUpdateDTO.getId());
        if (userReadDTO == null) {
            return null;
        }
        try {
            PropertyUtils.copyProperties(userReadDTO, userUpdateDTO);
            String modified = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            userReadDTO.setModified(modified);
        } catch (Exception e) {
            throw new RuntimeException("could not copy properties form userUpdateDTO to userReadDTO", e);
        }
        return userReadDTO;
    }

    public boolean deleteUser(@Valid UserDeleteDTO userDeleteDTO) {
        boolean isDeleted = CollectionUtils.filterInverse(
                this.userCache.getAllUsers(),
                new Predicate<UserReadDTO>() {
                    public boolean evaluate(UserReadDTO customer) {
                        return customer.getId().equals(userDeleteDTO.getId());
                    }
                });
        return isDeleted;
    }

}

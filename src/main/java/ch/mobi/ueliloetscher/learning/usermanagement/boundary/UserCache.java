package ch.mobi.ueliloetscher.learning.usermanagement.boundary;

import ch.mobi.ueliloetscher.learning.usermanagement.dto.UserReadDTO;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.Collection;

@Singleton
@Startup
public class UserCache {

    private Collection<UserReadDTO> users;

    @PostConstruct
    void init() {
        users = new ArrayList<UserReadDTO>();
    }

    public Collection<UserReadDTO> getAllUsers() {
        return this.users;
    }

}

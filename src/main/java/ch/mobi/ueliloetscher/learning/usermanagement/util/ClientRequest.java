package ch.mobi.ueliloetscher.learning.usermanagement.util;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ClientRequest {

    private String authorizationHeader;

    public String getAuthorizationHeader() {
        return authorizationHeader;
    }

    public void setAuthorizationHeader(String authorizationHeader) {
        this.authorizationHeader = authorizationHeader;
    }

}

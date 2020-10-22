package ch.mobi.ueliloetscher.learning.usermanagement.util;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

public class ClientRequestFilter implements ContainerRequestFilter {

    @Inject
    private ClientRequest authorization;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String authHeader = requestContext.getHeaderString("Authorization");
        this.authorization.setAuthorizationHeader(authHeader);

    }
}

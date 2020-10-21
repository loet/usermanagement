package ch.mobi.ueliloetscher.learning.usermanagement.validation;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Response;

public class RestValidationInterceptor {

    @AroundInvoke
    public Object log(final InvocationContext context) throws Exception {
        try {
            return context.proceed();
        } catch (RuntimeException ex) {
            if (ex.getCause() != null && ex.getCause().getCause() instanceof ValidationException) {
                ValidationException valEx = (ValidationException) ex.getCause().getCause();
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new ViolationMessageDTO(valEx.getMessages())).build();
            } else {
                throw ex;
            }
        }
    }
}

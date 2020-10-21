package ch.mobi.ueliloetscher.learning.usermanagement.validation;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolationException;

public class ConstraintViolationInterceptor {

    @AroundInvoke
    public Object extractConstraintViolations(final InvocationContext context) throws Exception {
        try {
            return context.proceed();
        } catch (ConstraintViolationException ex) {
            throw new ValidationException(ex.getConstraintViolations());
        }
    }
}

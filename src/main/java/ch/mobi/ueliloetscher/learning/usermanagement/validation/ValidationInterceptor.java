package ch.mobi.ueliloetscher.learning.usermanagement.validation;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolationException;

public class ValidationInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        try {
            return ctx.proceed();
        } catch (ConstraintViolationException ex) {
            throw new ValidationException(ex.getConstraintViolations());
        }
    }

}

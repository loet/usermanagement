package ch.mobi.ueliloetscher.learning.usermanagement.validation;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class ValidationException extends Exception {

    private Set<ConstraintViolation<?>> violations;

    public ValidationException(Set<ConstraintViolation<?>> violations) {
        this.violations = violations;
    }

    public Set<ConstraintViolation<?>> getViolations() {
        return violations;
    }

    public Collection<String> getMessages() {
        Collection<String> messages = new ArrayList<String>();
        for (ConstraintViolation violation : this.violations) {
            messages.add(violation.getMessage());
        }
        return messages;
    }
}

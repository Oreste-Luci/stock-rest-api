package cl.luci.stock.service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Oreste Luci
 */
public class AbstractService {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * Validates Model and throws Exception if errors are found.
     * @param o
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected void validate(Object o) throws Exception {

        if (o==null) {
            throw new Exception("Object cannot be null");
        }

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(o);

        if (!constraintViolations.isEmpty()){

            StringBuffer errors = new StringBuffer();

            for (ConstraintViolation violation : constraintViolations) {
                errors.append(violation.getMessage()).append(" ");
            }

            throw new Exception(errors.toString());
        }
    }
}

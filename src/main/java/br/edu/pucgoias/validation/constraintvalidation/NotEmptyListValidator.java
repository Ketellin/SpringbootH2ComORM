package br.edu.pucgoias.validation.constraintvalidation;

import br.edu.pucgoias.validation.NotEmptyList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {
    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * A implementaçao desse método deve indicar se a lista é válida, ou seja,
     * a lista não é nula, nem vazia
     * @param list - lista a ser validada
     * @param constraintValidatorContext -
     * @return
     */
    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && !list.isEmpty();
    }
}

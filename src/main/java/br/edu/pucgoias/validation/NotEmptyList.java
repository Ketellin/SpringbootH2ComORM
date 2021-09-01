package br.edu.pucgoias.validation;

import br.edu.pucgoias.validation.constraintvalidation.NotEmptyListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME) //Faz com que a anotação personalizada seja verificada em tempo de execuçao
@Target(ElementType.FIELD) //Onde a anotação poderá ser usada. Nesse caso, será para atributos
@Constraint(validatedBy = NotEmptyListValidator.class) //Indica que é uma annotation de validação.
                            //Será necessário criar uma classe que vai implementar os métodos da interface de
                            // constraint e indicar seu nome como parametro de validateBy.
                            //Essa classe é quem efetivamente irá tratar a validaçao
public @interface NotEmptyList {

    String message() default "A lista não pode ser vazia.";

    Class<?> [] groups() default {};

    Class<? extends Payload> [] payload() default {};

}

package ua.autostock.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.autostock.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CheckUniqueTelephoneValidator implements ConstraintValidator<CheckUniqueTelephone, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (userRepository.findByTelephone(value) == null){
            return true;
        }
        return false;
    }
}

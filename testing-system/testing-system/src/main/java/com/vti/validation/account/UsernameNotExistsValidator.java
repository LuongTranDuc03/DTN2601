package com.vti.validation.account;

import com.vti.repository.IAccountRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UsernameNotExistsValidator implements ConstraintValidator<UserNameNotExists, String> {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {

        if (StringUtils.isEmpty(username)) {
            return true;
        }
        return !accountRepository.existsByUsername(username);
    }
}

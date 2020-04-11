package com.jwatgroupb.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jwatgroupb.entity.ProfileUserEntity;
import com.jwatgroupb.service.UserService;

@Component
public class ProfileUserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProfileUserEntity userProfile = (ProfileUserEntity) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phonenumber", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty");
        
        if (userService.findByPhonenumber(userProfile.getPhonenumber()) != null) {
            errors.rejectValue("phonenumber", "Duplicate.infoForm.Phonenumber");
        }
    }
}

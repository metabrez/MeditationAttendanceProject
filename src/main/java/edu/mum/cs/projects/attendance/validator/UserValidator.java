package edu.mum.cs.projects.attendance.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.mum.cs.projects.attendance.domain.entity.User;
import edu.mum.cs.projects.attendance.service.UserService;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty");
//        if (user.getUserName().length() < 6 || user.getUserName().length() > 32) {
//            errors.rejectValue("username", "Size.userForm.username");
//        }
        if (userService.findByUserName(user.getUserName()) != null) {
            errors.rejectValue("userName", "Duplicate.userForm.username");
        }
        System.out.println("validate==============>[" + user.getPassword() + "]"); 
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
//        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
//            errors.rejectValue("password", "Size.userForm.password");
//        }

//        if (!user.getPasswordConfirm().equals(user.getPassword())) {
//            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
//        }
    }
}

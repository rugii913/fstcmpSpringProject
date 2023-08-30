package org.example.oopPractice.testCodePassword;

import org.example.oopPractice.password.PasswordGenerator;

public class WrongFixedPasswordGenerator implements PasswordGenerator {

    @Override
    public String generatePassword() {
        return "ab"; // 2글자
    }
}

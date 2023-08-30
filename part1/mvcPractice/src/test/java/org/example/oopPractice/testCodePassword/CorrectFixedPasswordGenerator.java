package org.example.oopPractice.testCodePassword;

import org.example.oopPractice.password.PasswordGenerator;

public class CorrectFixedPasswordGenerator implements PasswordGenerator {

    @Override
    public String generatePassword() {
        return "aabbccdd"; // 8글자
    }
}

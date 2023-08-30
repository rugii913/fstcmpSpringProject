package org.example.oopPractice.testCodePassword;

public class CorrectFixedPasswordGenerator implements PasswordGenerator {

    @Override
    public String generatePassword() {
        return "aabbccdd"; // 8글자
    }
}

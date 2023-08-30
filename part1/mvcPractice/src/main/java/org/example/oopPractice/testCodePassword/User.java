package org.example.oopPractice.testCodePassword;

public class User {
    private String password;

    public String getPassword() {
        return password;
    }

    public void initPassword(PasswordGenerator passwordGenerator) {
        // as-is 방식: 내부에서 생성 -> 강한 결합도
        // RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();
        // String password = randomPasswordGenerator.generatePassword();

        // to-be 방식: 인터페이스를 두고 주입받음 -> 낮은 결합도
        String password = passwordGenerator.generatePassword();

        /*
         * 비밀번호는 8자 이상 12자 이하여야 한다.
         */
        if (password.length() >= 8 && password.length() <= 12) {
            this.password = password;
        }
    }
}

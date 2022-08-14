package org.miro.testproject.pages.utils.users;

import org.apache.commons.lang3.RandomStringUtils;

public class User {
    private String username;
    private String email;
    private String password;

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setEmail(String email) {
        this.email = email.toLowerCase();
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setRandomUsername(int length) {
        return setUsername(generateRandomUsername(length));
    }

    public User setRandomEmail(String domain) {
        return setEmail(generateRandomEmail(domain));
    }

    public User setRandomPassword() {
        return setPassword(generateRandomPassword());
    }

    public User generateRandomUser() {
        return new User()
                .setRandomUsername(10)
                .setRandomEmail("randomemail.com")
                .setRandomPassword();
    }

    private String generateRandomUsername(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    private String generateRandomEmail(String domain) {
        return RandomStringUtils.randomAlphanumeric(10) + "@" + domain;
    }

    private String generateRandomPassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}

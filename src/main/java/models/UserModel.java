package models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields from JSON
public class UserModel {
    private String email;
    private String password;
    private String name;

    // No-args constructor (required for Jackson)
    public UserModel() {
    }

    // Constructor with parameters
    public UserModel(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        return "UserModel{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

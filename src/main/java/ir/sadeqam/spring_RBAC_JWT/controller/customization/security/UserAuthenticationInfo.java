package ir.sadeqam.spring_RBAC_JWT.controller.customization.security;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class UserAuthenticationInfo {

    @NotBlank(message = "username can't be blank")
    private String username;

    @NotBlank(message = "password can't be blank")
    private String password; //TODO use more constraints for this filed

    public UserAuthenticationInfo() {
    }

    public UserAuthenticationInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        return Objects.equals(username, ((UserAuthenticationInfo) other).getUsername());
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserAuthenticationInfo{ username='%s', password='%s' }".formatted(username, password);
    }
}

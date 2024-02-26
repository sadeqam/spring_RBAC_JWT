package ir.sadeqam.spring_RBAC_JWT.controller.customization.security;

import org.springframework.security.core.AuthenticationException;

public class TokenVerificationException extends AuthenticationException {
    public TokenVerificationException(String msg) {
        super(msg);
    }

}

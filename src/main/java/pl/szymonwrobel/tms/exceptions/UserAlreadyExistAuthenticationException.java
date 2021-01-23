package pl.szymonwrobel.tms.exceptions;

import javax.security.sasl.AuthenticationException;

public class UserAlreadyExistAuthenticationException extends AuthenticationException {

    public UserAlreadyExistAuthenticationException(final String message) {
        super(message);
    }
}

package com.humanit.portal_api.exceptions.authenticationApi;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.humanit.portal_api.exceptions.ErrorEnum.USER_NOT_AUTHENTICATED;

@Getter
public class UserCredentialsNotAuthenticatedException extends Exception {
    private final String message;
    private final String code;
    private final HttpStatus status;

    public UserCredentialsNotAuthenticatedException(String username) {
        super(USER_NOT_AUTHENTICATED.getMessage(username));
        this.message = USER_NOT_AUTHENTICATED.getMessage(username);
        this.code = USER_NOT_AUTHENTICATED.getCode();
        this.status = USER_NOT_AUTHENTICATED.getStatus();
    }
}

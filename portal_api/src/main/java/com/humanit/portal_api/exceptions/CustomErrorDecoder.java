package com.humanit.portal_api.exceptions;

import com.humanit.portal_api.exceptions.authenticationApi.RefreshTokenExpiredException;
import com.humanit.portal_api.exceptions.authenticationApi.RefreshTokenNotFoundException;
import com.humanit.portal_api.exceptions.authenticationApi.UserCredentialsNotAuthenticatedException;
import com.humanit.portal_api.exceptions.generic.GenericException;
import com.humanit.portal_api.exceptions.jwt.InvalidAuthorizationHeaderException;
import com.humanit.portal_api.exceptions.jwt.InvalidTokenException;
import com.humanit.portal_api.exceptions.salaryApi.CollaboratorByIdNotFoundException;
import com.humanit.portal_api.exceptions.salaryApi.SalaryByIdNotFoundException;
import com.humanit.portal_api.exceptions.salaryApi.SalaryComponentByIdNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

@Component
public class CustomErrorDecoder implements ErrorDecoder {
   private final HttpServletRequest request = getHttpRequest();

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 400 -> new InvalidAuthorizationHeaderException();
            case 401 -> getUnauthorizedException(response, request);
            case 404 -> getNotFoundException(response, request);
            default -> new GenericException();
        };
    }

    private HttpServletRequest getHttpRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes != null ? attributes.getRequest() : null;
    }

    private Exception getUnauthorizedException(Response response, HttpServletRequest request) {
        String responseBody = response.body().toString();

        if (responseBody.contains("Token is invalid")) {
            return new InvalidTokenException();
        } else if (responseBody.contains("not authenticated")) {
            return new UserCredentialsNotAuthenticatedException((String) request.getAttribute("username"));
        } else if (responseBody.contains("session has expired")) {
            return new RefreshTokenExpiredException();
        } else {
            return new GenericException();
        }
    }

    private Exception getNotFoundException(Response response, HttpServletRequest request) {
        String responseBody = response.body().toString();

        if (responseBody.contains("component")) {
            return new SalaryComponentByIdNotFoundException((UUID) request.getAttribute("id"));
        } else if (responseBody.contains("salary")) {
            return new SalaryByIdNotFoundException((UUID) request.getAttribute("id"));
        } else if (responseBody.contains("Collaborator")) {
            return new CollaboratorByIdNotFoundException((UUID) request.getAttribute("id"));
        } else if (responseBody.contains("Refresh token")) {
            return new RefreshTokenNotFoundException((String) request.getAttribute("token"));
        } else {
            return new GenericException();
        }
    }
}

package org.hngxfreelunch.LiquidApplicationApi.exceptions;

import org.springframework.http.HttpStatus;

public class OrganizationNotFoundException extends FreeLunchException {

    public OrganizationNotFoundException() {
        this("Organization not found");
    }
    public OrganizationNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

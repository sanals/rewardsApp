package com.rewards.app.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException() {
        super(BAD_REQUEST);
    }

    public BadRequestException(String reason) {
        super(BAD_REQUEST, reason);
    }

    public BadRequestException(String reason, Throwable cause) {
        super(BAD_REQUEST, reason, cause);
    }
}

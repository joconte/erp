package fr.epsi.erp.config;

import fr.epsi.erp.dto.ApiError;
import fr.epsi.erp.exception.ExceptionFonctionnnelle;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApplicationControllerAdvice {

    /** Provides handling for exceptions throughout this service. */
    @ExceptionHandler({ ExceptionFonctionnnelle.class })
    public final ResponseEntity<ApiError> handleException(ExceptionFonctionnnelle ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        HttpStatus status = HttpStatus.BAD_REQUEST;

        String errorMessage = ex.getMessage();

        ApiError apiError = new ApiError(errorMessage);

        return new ResponseEntity<>(apiError, headers, status);
    }
}

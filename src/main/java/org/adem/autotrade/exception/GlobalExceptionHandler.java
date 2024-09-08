package org.adem.autotrade.exception;

import org.adem.autotrade.repository.AdvantageRepository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarNotFoundException.class)
    public Map<String, String> handleCarNotFoundException(CarNotFoundException carNotFoundException) {
        Map<String, String> map = new HashMap<>();
        map.put("Message:", carNotFoundException.getMessage());
        return map;
    }

    @ExceptionHandler(AnnouncementNotFoundException.class)
    public Map<String, String> handleAnnouncementNotFoundException(AnnouncementNotFoundException announcementNotFoundException) {
        Map<String, String> map = new HashMap<>();
        map.put("Message:", announcementNotFoundException.getMessage());
        return map;
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public Map<String, String> handleEmailAlreadyInUseException(EmailAlreadyInUseException emailAlreadyInUseException) {
        Map<String, String> map = new HashMap<>();
        map.put("Message:", emailAlreadyInUseException.getMessage());
        return map;
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public Map<String, String> handleInvalidCredentials(InvalidCredentialsException invalidCredentialsException) {
        Map<String, String> map = new HashMap<>();
        map.put("Message:", invalidCredentialsException.getMessage());
        return map;
    }
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        Map<String, String> map = new HashMap<>();
        map.put("Message:", userNotFoundException.getMessage());
        return map;
    }

    @ExceptionHandler(AdvantageNotFoundException.class)
    public Map<String, String> handleAdvantageNotFoundException(AdvantageNotFoundException advantageNotFoundException) {
        Map<String, String> map = new HashMap<>();
        map.put("Message:", advantageNotFoundException.getMessage());
        return map;
    }
}

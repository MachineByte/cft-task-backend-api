package ru.cft.task_backend.api.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import ru.cft.task_backend.api.dto.ErrorDto;

import java.util.Map;

/**
 * CustomErrorController is a controller that handles errors and provides custom error responses.
 * It implements the {@link ErrorController} interface and uses Spring Boot's {@link ErrorAttributes}
 * to extract error details.
 */
@Controller
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Log4j2
public class CustomErrorController implements ErrorController {
    private static final String PATH = "/error";
    private final ErrorAttributes errorAttributes;

    @RequestMapping(CustomErrorController.PATH)
    public ResponseEntity<ErrorDto> error(WebRequest webRequest){
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(
                webRequest,
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.ERROR, ErrorAttributeOptions.Include.MESSAGE, ErrorAttributeOptions.Include.STATUS)
        );

        ErrorDto error = ErrorDto.builder()
                .error(((String) attributes.get("error")))
                .errorDescription(((String) attributes.get("message")))
                .build();

        return ResponseEntity
                .status(((Integer) attributes.get("status")))
                .body(error);
    }
}

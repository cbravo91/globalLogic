package com.evualuation.globallogic.exception.handler;

import com.evualuation.globallogic.dto.ErrorDTO;
import com.evualuation.globallogic.dto.ResponseErrorDTO;
import com.evualuation.globallogic.exception.BadRequestException;
import com.evualuation.globallogic.exception.JwtProcessException;
import com.evualuation.globallogic.exception.NotFoundException;
import com.evualuation.globallogic.exception.UnprocessableEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value= NotFoundException.class)
    public ResponseEntity<ResponseErrorDTO> handleNotFoundException(NotFoundException ex){
        ResponseErrorDTO  error = new ResponseErrorDTO();
        error.setError(List.of(new ErrorDTO(Timestamp.valueOf(LocalDateTime.now()),ex.getMessage(),404)));
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(value= MethodArgumentNotValidException .class)
    public ResponseEntity<ResponseErrorDTO> handleBadRequestException(MethodArgumentNotValidException ex){
        int statusCode = ex.getStatusCode().value();
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO();
        responseErrorDTO.setError(
                ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> new ErrorDTO(Timestamp.valueOf(LocalDateTime.now()),error.getDefaultMessage(),statusCode))
                .collect(Collectors.toList()));
        return ResponseEntity.status(ex.getStatusCode()).body(responseErrorDTO);
    }

    @ExceptionHandler(value= JwtProcessException.class)
    public ResponseEntity<ResponseErrorDTO> handleJwtProcessException(JwtProcessException ex){
        ResponseErrorDTO  error = new ResponseErrorDTO();
             error.setError(List.of(new ErrorDTO(Timestamp.valueOf(LocalDateTime.now()),ex.getMessage(),412)));
        return  ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(error);
    }

    @ExceptionHandler(value= UnprocessableEntityException.class)
    public ResponseEntity<ResponseErrorDTO> handleUnprocessableEntityException(UnprocessableEntityException ex){
        ResponseErrorDTO  error = new ResponseErrorDTO();
        error.setError(List.of(new ErrorDTO(Timestamp.valueOf(LocalDateTime.now()),ex.getMessage(),422)));
        return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }
}
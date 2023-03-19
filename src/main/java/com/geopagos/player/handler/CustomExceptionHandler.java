package com.geopagos.player.handler;

import com.geopagos.player.exceptions.RecordNotFoundException;
import com.geopagos.player.model.api.ModelApiException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<ModelApiException> handleRecordNotFoundException(RecordNotFoundException ex) {

        ModelApiException exceptionDetail = new ModelApiException();
        exceptionDetail.setCode(ex.getCode());
        exceptionDetail.setDescription(ex.getDescription());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDetail);

    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ModelApiException> handleException(Exception ex) {

        log.error("Unexpected Exception ::", ex);

        ModelApiException exceptionDetail = new ModelApiException();
        exceptionDetail.setCode(CommonErrors.DEFAULT_ERROR_CODE);
        exceptionDetail.setDescription(CommonErrors.DEFAULT_ERROR_DESC);
        exceptionDetail.setErrors(Collections.singletonList(ex.getMessage()));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDetail);

    }
}

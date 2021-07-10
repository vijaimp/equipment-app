package com.kone.manage.equipment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateEquipmentException extends RuntimeException {
    public DuplicateEquipmentException(String msg) {
        super(msg);
    }
}

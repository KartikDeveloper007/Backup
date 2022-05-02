package com.uoons.users.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ControllerException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String errorcode;
    private String errorMessage;
}
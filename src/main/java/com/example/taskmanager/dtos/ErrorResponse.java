package com.example.taskmanager.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private String Message;

    public ErrorResponse(String message)
    {
        this.Message = message;
    }
}

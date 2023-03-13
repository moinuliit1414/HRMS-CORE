package com.synesis.hrmis.dto.responseDTO;

import lombok.Data;

@Data
public class ErrorResponse<T> {
    private boolean isSuccess;
    private T replyMessage;

    public ErrorResponse(T replyMessage) {
        this.isSuccess = false;
        this.replyMessage = replyMessage;
    }
}

package com.synesis.hrmis.dto.responseDTO;

import lombok.Data;

@Data
public class SuccessResponse<T> {
    private boolean isSuccess;
    private T replyMessage;

    public SuccessResponse(T replyMessage) {
        this.isSuccess = true;
        this.replyMessage = replyMessage;
    }
}

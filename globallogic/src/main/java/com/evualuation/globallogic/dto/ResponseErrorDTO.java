package com.evualuation.globallogic.dto;

import java.util.List;

public class ResponseErrorDTO {
    private List<ErrorDTO> error;

    public List<ErrorDTO> getError() {
        return error;
    }

    public void setError(List<ErrorDTO> error) {
        this.error = error;
    }
}

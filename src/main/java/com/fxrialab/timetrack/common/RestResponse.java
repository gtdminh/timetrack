package com.fxrialab.timetrack.common;

public class RestResponse {

    public RestResponse(ResponseCode code){
        setCode(code);
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    private ResponseCode code;
}

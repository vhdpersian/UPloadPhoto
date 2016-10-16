package com.example.api.restfull;

/**
 * Created by Administrator on 10/11/2016.
 */

public  enum  RequestType {
    Token_Register("token"),
    Upload_Image("upload");

    private String methodName;

    private RequestType(final String methodName) {
        this.methodName = methodName;
    }

    public String GetRequestMethod() {
        return this.methodName;
    }
}

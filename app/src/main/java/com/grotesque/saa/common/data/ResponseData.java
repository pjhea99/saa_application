package com.grotesque.saa.common.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 경환 on 2016-04-09.
 */
public class ResponseData {
    @SerializedName("message_typ")
    String message_type;

    @SerializedName("error")
    int error;

    @SerializedName("message")
    String message;

    public ResponseData(String message_type, int error, String message) {
        this.message_type = message_type;
        this.error = error;
        this.message = message;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

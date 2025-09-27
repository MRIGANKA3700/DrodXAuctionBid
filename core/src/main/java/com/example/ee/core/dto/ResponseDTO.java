package com.example.ee.core.dto;

public class ResponseDTO implements java.io.Serializable {

    private    Object message;
    private boolean success;

    public ResponseDTO() {}

    public ResponseDTO(Object message, boolean success) {
        this.message = message;
        this.success = success;
    }
    public Object getMessage() {
        return message;
    }
    public void setMessage(Object message) {
        this.message = message;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    @Override
    public String toString() {
        return "ResponseDTO{" +
                "message=" + message +
                ", success=" + success +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseDTO)) return false;

        ResponseDTO that = (ResponseDTO) o;

        if (success != that.success) return false;
        return message != null ? message.equals(that.message) : that.message == null;
    }
    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (success ? 1 : 0);
        return result;
    }
}

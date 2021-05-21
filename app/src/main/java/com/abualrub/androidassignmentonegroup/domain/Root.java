package com.abualrub.androidassignmentonegroup.domain;

// *********************************
// MADE BY OSID ABU-ALRUB (1183096)
// *********************************
public class Root<T> {

    private String error;
    private boolean isError;
    private T data;

    public Root(){

    }

    public Root(String error,boolean isError,T data){
        this.error = error;
        this.isError = isError;
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

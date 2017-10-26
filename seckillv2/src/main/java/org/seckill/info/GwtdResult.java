package org.seckill.info;

import org.seckill.entity.GwtdServer;

import java.util.List;

/**
 * Created by shunge on 2017/5/25.
 */
public class GwtdResult<T> {

    private  boolean success;
    private String error;
    private List<T> data;

    public GwtdResult(boolean success){
        this.success = success;
    }

    public GwtdResult(boolean success, List<T> data) {
        this.success = success;
        this.data = data;
    }

    public GwtdResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

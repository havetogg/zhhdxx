package com.havetogg.ssm.result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/3/17.
 */
public class CommonResult<T> implements Serializable {
    private int type = 0;
    private String message = "";
    private List<T> datas;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}

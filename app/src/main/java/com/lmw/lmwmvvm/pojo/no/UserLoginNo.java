package com.lmw.lmwmvvm.pojo.no;

import com.lmw.common.net.pojo.no.BaseNo;
import com.lmw.lmwmvvm.pojo.bo.UserLoginBo;

public class UserLoginNo extends BaseNo {

    private UserLoginBo data;

    public UserLoginBo getData() {
        return data;
    }

    public void setData(UserLoginBo data) {
        this.data = data;
    }

}

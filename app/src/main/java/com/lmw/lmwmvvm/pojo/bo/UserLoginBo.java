package com.lmw.lmwmvvm.pojo.bo;


public class UserLoginBo {

    private String userId;

    //登录票据，标号不能修改
    private String token;
    public boolean loadSuccess;

    public String content;

    public boolean isLoadSuccess() {
        return loadSuccess;
    }

    public void setLoadSuccess(boolean loadSuccess) {
        this.loadSuccess = loadSuccess;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

}

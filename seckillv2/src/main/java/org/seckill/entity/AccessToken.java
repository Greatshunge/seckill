package org.seckill.entity;

public class AccessToken {

    private String accessToken;
    private int exiresIn;//有效时间,7200s

    public AccessToken() {
    }

    public AccessToken(String accessToken, int exiresIn) {
        this.accessToken = accessToken;
        this.exiresIn = exiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExiresIn() {
        return exiresIn;
    }

    public void setExiresIn(int exiresIn) {
        this.exiresIn = exiresIn;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "accessToken='" + accessToken + '\'' +
                ", exiresIn=" + exiresIn +
                '}';
    }
}

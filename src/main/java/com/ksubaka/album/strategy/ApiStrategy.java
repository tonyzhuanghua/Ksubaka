package com.ksubaka.album.strategy;

/**
 * Created by zhuanghua on 16/8/26.
 */
public abstract class ApiStrategy {
    protected String uri;
    protected String method;

    public ApiStrategy(String uri, String method) {
        this.uri = uri;
        this.method = method;
    }

    public abstract String retrieveData(String searchString);
}

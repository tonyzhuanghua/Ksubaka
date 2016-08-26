package com.ksubaka.album;

/**
 * Created by zhuanghua on 16/8/26.
 */
public abstract class AlbumStrategy {
    protected String url;
    protected String method;

    public AlbumStrategy(String url, String method) {
        this.url = url;
        this.method = method;
    }

    abstract String retrieveAlbum(String searchString);
}

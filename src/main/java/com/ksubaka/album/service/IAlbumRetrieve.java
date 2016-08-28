package com.ksubaka.album.service;

import com.ksubaka.album.dto.AlbumDto;

import java.util.List;

/**
 * Created by zhuanghua on 16/8/28.
 */
public interface IAlbumRetrieve {
     List<AlbumDto> retrieveAlbum(String keywords);
}

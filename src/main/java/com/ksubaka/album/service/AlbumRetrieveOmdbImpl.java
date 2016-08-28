package com.ksubaka.album.service;

import com.google.gson.*;
import com.ksubaka.album.dto.AlbumDto;
import com.ksubaka.album.strategy.OmdbStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuanghua on 16/8/28.
 */
public class AlbumRetrieveOmdbImpl extends AbstractAlbumRetrieveTemplate implements IAlbumRetrieve {

    private List<AlbumDto> list = new ArrayList<AlbumDto>();

    public List<AlbumDto> retrieveAlbum(String keywords) {
        super.setSearchString(keywords);
        super.setApiStrategy(new OmdbStrategy());
        super.retrieveData();
        convertResponseToDto(response);
        return this.list;
    }

    @Override
    public void convertResponseToDto(String response) {

        try {
            JsonElement jsonElement = new JsonParser().parse(response);
            JsonArray jarray = jsonElement.getAsJsonObject().getAsJsonArray("Search");
            if (jarray != null) {
                for (Object o : jarray) {
                    JsonObject json = (JsonObject) o;
                    AlbumDto dto = new AlbumDto();
                    dto.setTitle(json.get("Title").getAsString());
                    dto.setImdbId(json.get("imdbID").getAsString());
                    dto.setType(json.get("Type").getAsString());
                    dto.setYear(json.get("Year").getAsString());
                    list.add(dto);
                }
            }

        } catch (JsonParseException e) {
            e.printStackTrace();
        }

    }

}

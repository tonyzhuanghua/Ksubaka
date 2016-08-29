package com.ksubaka.album.service;

import com.google.gson.*;
import com.ksubaka.album.dto.AlbumDto;
import com.ksubaka.album.strategy.ImdbStrategy;
import com.ksubaka.album.strategy.ImdbSubStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuanghua on 16/8/28.
 */
public class AlbumRetrieveImdbImpl extends AbstractAlbumRetrieveTemplate implements IAlbumRetrieve {

    private List<AlbumDto> list = new ArrayList<AlbumDto>();

    public List<AlbumDto> retrieveAlbum(String keywords) {
        super.setSearchString(keywords);
        super.setApiStrategy(new ImdbStrategy());
        super.retrieveData();

        String actorId = getActorId(response); // retrieve imdb Id firstly
        if (actorId != null) {
            super.setSearchString(actorId);
            super.setApiStrategy(new ImdbSubStrategy());
            super.retrieveData(); // use id to retrieve works

            convertResponseToDto(response);
        }
        return this.list;
    }

    private String getActorId(String response) {
        String id = null;
        try {
            JsonElement jsonElement = new JsonParser().parse(response);
            JsonArray jarray = jsonElement.getAsJsonObject().getAsJsonArray("name_exact");
            if (jarray != null) {
                for (Object o : jarray) {
                    JsonObject json = (JsonObject) o;
                    id = json.get("id").getAsString();
                    if (id != null && !id.equals("")) break;
                }
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public void convertResponseToDto(String response) {

        try {
            JsonElement jsonElement = new JsonParser().parse(response);
            JsonArray jarray = jsonElement.getAsJsonObject().get("data").getAsJsonObject().getAsJsonArray("filmography");
            if (jarray != null) {
                for (Object o : jarray) {
                    JsonObject json = (JsonObject) o;
                    AlbumDto dto = new AlbumDto();
                    dto.setTitle(json.get("title").getAsString());
                    String info = json.get("info").getAsString();
                    int s1 = info.split("tt\\d+")[0].length();
                    int s2 = info.split("tt\\d+")[1].length();
                    dto.setImdbId(info.substring(s1, info.length() - s2));
                    dto.setType("movie");
                    String year = json.get("year").getAsString();
                    dto.setYear(year.substring(year.length() - 4));
                    list.add(dto);
                }
            }

        } catch (JsonParseException e) {
            e.printStackTrace();
        }

    }

}

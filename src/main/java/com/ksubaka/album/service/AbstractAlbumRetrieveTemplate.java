package com.ksubaka.album.service;

import com.ksubaka.album.strategy.ApiStrategy;

/**
 * Created by zhuanghua on 16/8/28.
 */
public abstract class AbstractAlbumRetrieveTemplate {

   private String searchString;
   private ApiStrategy apiStrategy;
   protected String response;

   protected final void retrieveData(){
      this.response = apiStrategy.retrieveData(searchString);
   }

   protected abstract void convertResponseToDto(String response);

   public void setSearchString(String searchString) {
      this.searchString = searchString;
   }

   public void setApiStrategy(ApiStrategy apiStrategy) {
      this.apiStrategy = apiStrategy;
   }
}

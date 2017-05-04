package com.popularmovies.vpaliy.data.entity;

import com.google.gson.annotations.SerializedName;
import com.popularmovies.vpaliy.domain.model.Trailer;

import java.util.ArrayList;
import java.util.List;

public class TrailerEntity {

    private int movieId;

    @SerializedName("name")
    private String trailerTitle;

    @SerializedName("key")
    private String trailerUrl;


    public int getMovieId() {
        return movieId;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public String getTrailerTitle() {
        return trailerTitle;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setTrailerTitle(String trailerTitle) {
        this.trailerTitle = trailerTitle;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public static List<Trailer> convert(List<TrailerEntity> list){
        if(list==null)return null;
        List<Trailer> result=new ArrayList<>(list.size());
        for(TrailerEntity entity:list){
            Trailer trailer=new Trailer(entity.getMovieId(),entity.getTrailerUrl(),entity.getTrailerTitle());
            result.add(trailer);
        }
        return result;
    }

    public static List<TrailerEntity> convertBack(List<Trailer> list){
        if(list==null)return null;
        List<TrailerEntity> result=new ArrayList<>(list.size());
        for(Trailer trailer:list){
            TrailerEntity trailerEntity=new TrailerEntity();
            trailerEntity.setMovieId(trailer.getMovieId());
            trailerEntity.setTrailerTitle(trailer.getTrailerTitle());
            trailerEntity.setTrailerUrl(trailer.getTrailerUrl());
            result.add(trailerEntity);
        }
        return result;
    }

}

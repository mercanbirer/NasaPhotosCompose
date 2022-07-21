package com.example.nasaphotoscompose.service

import com.example.nasaphotoscompose.model.NasaPhotos
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface NasaApi {

    @GET("/mars-photos/api/v1/rovers/{roverType}/photos?")
    suspend fun getPhotos(
        @Path("roverType") roverType: String?,
        @QueryMap map: Map<String,String>
    ): NasaPhotos

    @GET("/mars-photos/api/v1/rovers/{roverType}/photos?")
    suspend fun getPhotosCamera(
        @Path("roverType") roverType: String?,
        @QueryMap map: Map<String, String>
    ): NasaPhotos
}
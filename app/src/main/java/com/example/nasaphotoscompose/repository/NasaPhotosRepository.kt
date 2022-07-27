package com.example.nasaphotoscompose.repository

import com.example.nasaphotoscompose.model.NasaPhotos
import com.example.nasaphotoscompose.service.NasaApi
import javax.inject.Inject

class NasaPhotosRepository  @Inject constructor(
    private val api : NasaApi
): NasaApi {
    override suspend fun getPhotos(roverType: String?, map: Map<String, String>): NasaPhotos {
       return api.getPhotos(roverType = roverType, map = map)
    }

    override suspend fun getPhotosCamera(roverType: String?, map: Map<String, String>): NasaPhotos {
        return api.getPhotosCamera(roverType = roverType, map = map)
    }
}
package com.example.nasaphotoscompose.usecase

import com.example.nasaphotoscompose.di.Resource
import com.example.nasaphotoscompose.model.NasaPhotos
import com.example.nasaphotoscompose.repository.NasaPhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class NasaPhotosUseCase  @Inject constructor(
    private val repository: NasaPhotosRepository
) {
    fun getNasaPhotos(roverType: String?, map: Map<String, String>): Flow<Resource<NasaPhotos>> {
        return flow {
            emit(Resource.Loading())
            val users = withContext(Dispatchers.IO) {
                repository.getPhotos(roverType = roverType,map = map)
            }
            emit(Resource.Success(users))
        }.catch() {
            Timber.tag("error").e("")
            emit(Resource.Error("An unexpected error occured"))
        }.catch() {
            Timber.tag("error").e("")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
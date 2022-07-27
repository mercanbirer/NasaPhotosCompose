package com.example.nasaphotoscompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaphotoscompose.di.Resource
import com.example.nasaphotoscompose.model.NasaPhotos
import com.example.nasaphotoscompose.usecase.NasaPhotosUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class NasaPhotosViewModel @Inject constructor(
    private val usecase: NasaPhotosUseCase
) : ViewModel() {

    private val _apiNasaPhotos = MutableStateFlow<Resource<NasaPhotos>>(Resource.Idle())
    var apiNasaPhotos: StateFlow<Resource<NasaPhotos>> = _apiNasaPhotos

    fun getNasaPhotos(roverType: String?, map: Map<String, String>) {
        viewModelScope.launch {
            usecase.getNasaPhotos(roverType = roverType, map = map).collect {
                when (it) {
                    is Resource.Loading -> {
                        _apiNasaPhotos.value = Resource.Loading()
                    }
                    is Resource.Success -> {
                        _apiNasaPhotos.value = Resource.Success(it.data!!)
                    }
                    is Resource.Error -> {
                        _apiNasaPhotos.value = Resource.Error("error")
                    }
                    is Resource.Idle -> {
                        _apiNasaPhotos.value = Resource.Idle()
                    }
                }
            }
        }

    }
}
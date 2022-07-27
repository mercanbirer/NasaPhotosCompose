package com.example.nasaphotoscompose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.example.nasaphotoscompose.di.Resource
import com.example.nasaphotoscompose.util.RoverTypes
import com.example.nasaphotoscompose.util.map
import com.example.nasaphotoscompose.viewmodel.NasaPhotosViewModel
import timber.log.Timber

@Composable
fun NasaPhotosScreen(
    navController: NavController,
    viewModel: NasaPhotosViewModel = hiltViewModel()
) {

    val nasaPhotos = viewModel.apiNasaPhotos.collectAsState()
    var selectedRover = RoverTypes.CURIOSITY
    LaunchedEffect(Unit, block = {
        viewModel.getNasaPhotos(selectedRover.typeName, map = map(
            page = 1
        )
        )
    })


    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            color = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                text = "NASA PHOTOS",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
        when (nasaPhotos.value) {
            is Resource.Loading -> {}
            is Resource.Error -> {
                if (nasaPhotos.value.message != null) {
                    Timber.tag("Error").e(nasaPhotos.value.message)
                }
            }
            is Resource.Success -> {
                val photos = nasaPhotos.value.data!!.photos
                LazyColumn {
                    items(items = photos) {
                        Card(
                            modifier = Modifier
                                .padding(30.dp, 20.dp)
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            shape = RoundedCornerShape(18.dp),
                            elevation = 4.dp
                        ) {

                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .padding(40.dp)
                                    .fillMaxWidth()
                            ) {
                                Image(painter = rememberImagePainter(
                                    data = it.img_src,
                                    builder = {
                                        size(OriginalSize)
                                        scale(Scale.FILL)
                                        transformations(RoundedCornersTransformation())
                                    }
                                ), contentDescription = "" )
                            }
                        }
                    }
                }
            }
            else -> {}
        }
    }
}
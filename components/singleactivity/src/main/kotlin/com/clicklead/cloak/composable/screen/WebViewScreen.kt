package com.clicklead.cloak.composable.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.clicklead.cloak.composable.layout.WebViewLayout
import com.clicklead.cloak.viewmodel.MainViewModel
import dev.icerock.moko.mvvm.livedata.compose.observeAsState
import org.koin.compose.koinInject


@Composable
fun WebViewScreen() {
    val viewModel = koinInject<MainViewModel>()
    val placeholder = remember(viewModel) { viewModel.placeholder }.observeAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(visible = placeholder.value == true) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Увы и ах, ссылочка не пришла")
            }
        }
        AnimatedVisibility(visible = placeholder.value == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        AnimatedVisibility(visible = placeholder.value == false) {
            val rawUrl = remember(viewModel) { viewModel.url }.observeAsState().value
            Column {
                Text(text = "Ваша сырая ссылка: $rawUrl")
                WebViewLayout(url = "http://fposttestb.xyz/testing.html",
                    //ЗАХАРДКОДИЛ ДЛЯ  ДЕМОНСТРАЦИИ СЕРВИСА "http://fposttestb.xyz/testing.html"
                    // а так передать условно нужно  viewModel.url.value
                    onChangeUrl = {
                        //_url->viewModel.setUrl(_url)
                        //ЗАКОММЕНТИЛ ДЛЯ ДЕМОНСТРАЦИИ СЕРВИСА "http://fposttestb.xyz/testing.html"
                    })

            }
        }
    }
}

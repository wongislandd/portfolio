package com.wongislandd.portfolio.programs.webprogram

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.multiplatform.webview.web.LoadingState
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState

@Composable
fun WebHost(url: String, modifier: Modifier = Modifier) {
    val state = rememberWebViewState(url)
    Box(modifier = modifier.background(MaterialTheme.colors.background)) {
        when (state.loadingState) {
            is LoadingState.Initializing -> {
                CircularProgressIndicator(modifier = Modifier.size(64.dp).align(Alignment.Center), color = MaterialTheme.colors.onBackground)
            }
            else -> { // do nothing }
            }
        }
        WebView(state, modifier = Modifier.fillMaxSize())
    }
}
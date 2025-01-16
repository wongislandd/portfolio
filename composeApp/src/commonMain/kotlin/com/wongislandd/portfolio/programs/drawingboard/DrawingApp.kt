package com.wongislandd.portfolio.programs.drawingboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.portfolio.programs.infinityindex.infra.navigation.NavHostControllerProvider
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun DrawingApp(
    modifier: Modifier = Modifier
) {
    KoinContext {
        NavHostControllerProvider {
            Box(
                modifier = modifier.fillMaxSize()
                    .background(color = MaterialTheme.colors.surface)
            ) {
                DrawingBoardScreen()
            }
        }
    }
}
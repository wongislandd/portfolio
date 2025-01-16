package com.wongislandd.portfolio.programs.infinityindex

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.portfolio.programs.infinityindex.infra.navigation.AppNavHost
import com.wongislandd.portfolio.programs.infinityindex.infra.navigation.NavHostControllerProvider
import com.wongislandd.portfolio.programs.infinityindex.ui.MarvelTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun InfinityIndexApp(
    modifier: Modifier = Modifier
) {
    MarvelTheme {
        KoinContext {
            NavHostControllerProvider {
                Box(
                    modifier = modifier.fillMaxSize()
                        .background(color = MaterialTheme.colors.surface)
                ) {
                    AppNavHost()
                }
            }
        }
    }
}
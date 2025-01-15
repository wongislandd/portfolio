package com.wongislandd.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.wongislandd.nexus.navigation.NavHostControllerProvider
import com.wongislandd.nexus.theming.AppTheme
import com.wongislandd.portfolio.desktop.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun PortfolioApp(modifier: Modifier = Modifier) {
    val appViewModel = koinViewModel<AppViewModel>()
    AppTheme {
        KoinContext {
            CompositionLocalProvider(LocalAppViewModel provides appViewModel) {
                NavHostControllerProvider {
                    Box(
                        modifier = modifier.fillMaxSize()
                            .background(color = MaterialTheme.colors.surface)
                    ) {
                        HomeScreen()
                        //PortfolioNavHost()
                    }
                }
            }
        }
    }
}
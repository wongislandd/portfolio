package com.wongislandd.portfolio.landingpage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wongislandd.nexus.navigation.LocalNavHostController
import com.wongislandd.portfolio.LocalAppViewModel
import com.wongislandd.portfolio.navigation.PortfolioTopAppBar

@Composable
fun LandingPage(modifier: Modifier = Modifier) {
    val navController = LocalNavHostController.current
    val appViewModel = LocalAppViewModel.current
    PortfolioTopAppBar {
        Box(modifier = modifier.fillMaxSize()) {
            Text(
                "this portfolio is a WIP",
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.h3,
                modifier = Modifier.align(
                    Alignment.TopCenter
                ).padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
    }

}
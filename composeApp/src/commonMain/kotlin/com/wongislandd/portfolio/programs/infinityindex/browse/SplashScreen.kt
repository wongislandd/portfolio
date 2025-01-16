package com.wongislandd.portfolio.programs.infinityindex.browse

import NavigationItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wongislandd.portfolio.programs.infinityindex.ComicConstants
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.Bolt
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.DominoMask
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.Freeze
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.Infinity
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.MenuBook
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.Planet
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.Rocket
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.Storm
import com.wongislandd.portfolio.programs.infinityindex.infra.navigation.LocalNavHostController
import com.wongislandd.portfolio.programs.infinityindex.infra.navigation.NavigationHelper
import kotlinx.coroutines.delay

private val splashScreenIcons = listOf(
    Infinity,
    DominoMask,
    Bolt,
    MenuBook,
    Freeze,
    Planet,
    Storm,
    Rocket
)

@Composable
fun AlternatingIcons(
    icons: List<ImageVector>,
    iconColor: Color = MaterialTheme.colors.onPrimary,
    iconSize: Dp = 24.dp,
    modifier: Modifier = Modifier,
    intervalMillis: Long = 200
) {
    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(icons) {
        while (true) {
            delay(intervalMillis)
            currentIndex = (currentIndex + 1) % icons.size
        }
    }

    Icon(
        imageVector = icons[currentIndex],
        contentDescription = null,
        modifier = modifier.size(iconSize),
        tint = iconColor
    )
}

@Composable
fun InfinityIndexSplashScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavHostController.current
    LaunchedEffect(Unit) {
        delay(ComicConstants.SPLASH_SCREEN_DURATION)
        navController.navigate(NavigationHelper.getBrowseRoute()) {
            popUpTo(NavigationItem.Splash.route) { inclusive = true }
        }
    }
    InfinityIndexLoadingScreen(modifier)
}

@Composable
fun InfinityIndexLoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize().background(MaterialTheme.colors.primary)) {
        AlternatingIcons(
            icons = splashScreenIcons,
            iconSize = 68.dp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
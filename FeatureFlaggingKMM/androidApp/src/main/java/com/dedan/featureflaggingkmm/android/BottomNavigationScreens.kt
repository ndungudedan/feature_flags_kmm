package com.dedan.featureflaggingkmm.android

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreens(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Winter : BottomNavigationScreens(Screen.Winter.route, R.string.winter_screen, Icons.Filled.Snowboarding)
    object Summer : BottomNavigationScreens(Screen.Summer.route, R.string.summer_screen, Icons.Filled.WbSunny)
    object Autumn : BottomNavigationScreens(Screen.Autumn.route, R.string.autumn_screen, Icons.Filled.WbShade)
    object Spring : BottomNavigationScreens(Screen.Spring.route, R.string.spring_screen, Icons.Filled.WbCloudy)
}

sealed class Screen(val route: String) {
    object Index: Screen("index")
    object Winter: Screen("winter")
    object Summer: Screen("summer")
    object Autumn: Screen("autumn")
    object Spring: Screen("spring")
}
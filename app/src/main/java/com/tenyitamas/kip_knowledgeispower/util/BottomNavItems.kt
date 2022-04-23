package com.tenyitamas.kip_knowledgeispower.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tenyitamas.kip_knowledgeispower.R
import com.tenyitamas.kip_knowledgeispower.navigation.Route
import com.tenyitamas.kip_knowledgeispower.presentation.shared.BottomNavItem

@Composable
fun bottomNavItems(): List<BottomNavItem>  {
    return listOf(
        BottomNavItem(
            name = stringResource(id = R.string.search),
            route = Route.SEARCH,
            icon = Icons.Default.Search
        ),
        BottomNavItem(
            name = stringResource(id = R.string.saved),
            route = Route.SAVED,
            icon = Icons.Default.Favorite
        ),
        BottomNavItem(
            name = stringResource(id = R.string.settings),
            route = Route.SETTINGS,
            icon = Icons.Default.Settings
        ),
    )
}
